package blog.michaelx.tech.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import blog.michaelx.tech.ShareConstants;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    public static final String EXTRA_SHARE_ERROR_CODE = "com.fountain.social_share_err_code";
    public static final String EXTRA_SHARE_ERROR_MSG = "com.fountain.social_share_err_msg";
    public static final String ACTION_WX_SHARE_RESULT = "com.fountain.social_share_result";
    private IWXAPI mWxApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        mWxApi = WXAPIFactory.createWXAPI(this, ShareConstants.WX_APP_ID, true);
        // 注意：
        // 第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，
        // 则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            mWxApi.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWxApi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        // 发出请求
    }

    @Override
    public void onResp(BaseResp baseResp) {
        sendCallbackBroadcast(baseResp.errCode, baseResp.errStr);
        finish();
    }

    private void sendCallbackBroadcast(int errCode, String errMsg) {
        Intent result = new Intent();
        result.setAction(ACTION_WX_SHARE_RESULT);
        result.putExtra(EXTRA_SHARE_ERROR_CODE, errCode);
        result.putExtra(EXTRA_SHARE_ERROR_MSG, errMsg);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(result);
    }
}
