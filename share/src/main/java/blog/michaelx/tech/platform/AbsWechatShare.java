package blog.michaelx.tech.platform;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.type.IShareContent;
import blog.michaelx.tech.utils.CheckAppInstalledUtil;
import blog.michaelx.tech.wxapi.WXEntryActivity;

/**
 * 微信和朋友圈分享基类
 *
 * @author MichaelX
 * @date 2018/10/25
 *
 */
abstract class AbsWechatShare implements ISharePlatform<SendMessageToWX.Req, IShareContent> {

    private OnShareResultCallback mShareResultCallback;
    private IShareContent mShareContentType;

    @Override
    public void share(Activity activity, OnShareResultCallback callback,
                      IShareContent contentTyp, SendMessageToWX.Req req) {
        if (req == null) {
            return;
        }
        if (!CheckAppInstalledUtil.isWechatInstalled(activity)) {
            mShareResultCallback.onShareFailed(this, mShareContentType,
                    ERR_CODE_WX_NOT_INSTALL, ERR_MSG_WX_NOT_INSTALL);
        }
        mShareResultCallback = callback;
        mShareContentType = contentTyp;
        registerResultReceiver(activity);
        IWXAPI wxApi; // 第三方app和微信通信的openapi接口
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        wxApi = WXAPIFactory.createWXAPI(activity, ShareConstants.WX_APP_ID, true);
        // 将应用的appId注册到微信
        wxApi.registerApp(ShareConstants.WX_APP_ID);
        wxApi.sendReq(req);
    }

    private void registerResultReceiver(Context context) {
        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        broadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    int resultCode = intent.getIntExtra(WXEntryActivity.EXTRA_SHARE_ERROR_CODE, 0);
                    String resultMsg = intent.getStringExtra(WXEntryActivity.EXTRA_SHARE_ERROR_CODE);

                    switch (resultCode) {
                        case BaseResp.ErrCode.ERR_OK:
                            if (mShareResultCallback != null) {
                                mShareResultCallback.onShareSuccess(AbsWechatShare.this, mShareContentType);
                            }
                            break;
                        case BaseResp.ErrCode.ERR_USER_CANCEL:
                            if (mShareResultCallback != null) {
                                mShareResultCallback.onShareCancel(AbsWechatShare.this, mShareContentType);
                            }
                            break;
                        default:
                            if (mShareResultCallback != null) {
                                mShareResultCallback.onShareFailed(AbsWechatShare.this, mShareContentType,
                                        resultCode, resultMsg);
                            }
                            break;
                    }
                }
                // 接受完结果后注销监听
                unRegisterResultReceiver();
            }

            private void unRegisterResultReceiver() {
                broadcastManager.unregisterReceiver(this);
            }
        }, new IntentFilter(WXEntryActivity.ACTION_WX_SHARE_RESULT));
    }
}
