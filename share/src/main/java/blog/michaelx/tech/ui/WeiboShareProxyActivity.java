package blog.michaelx.tech.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.platform.ISharePlatform;
import blog.michaelx.tech.platform.WeiboShare;
import blog.michaelx.tech.type.AbsShareContentType;

/**
 * 微博分享代理界面：用于代替实际分享Activity
 *
 * @author MichaelX
 * @date 2018/10/25
 */
public class WeiboShareProxyActivity extends Activity implements WbShareCallback {

    private WbShareHandler mWbShareHandler;
    private OnShareResultCallback mShareResultCallback;
    private AbsShareContentType mShareContentType;
    private ISharePlatform mSharePlatform;
    private static Reference<WeiboShare> mWeiboShareRef;

    public static void startWeiboShare(Activity activity, WeiboShare share) {
        if (activity == null || share == null) {
            return;
        }
        mWeiboShareRef = new WeakReference<>(share);
        activity.startActivity(new Intent(activity, WeiboShareProxyActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO 尚未调试通过
        WbSdk.install(this.getApplicationContext(), new AuthInfo(this.getApplicationContext(), ShareConstants.WB_APP_KEY, ShareConstants.WB_REDIRECT_URL, ShareConstants.WB_SCOPE));
        mShareContentType = null;
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();// = (WeiboMultiMessage) getIntent().getSerializableExtra(EXTRA_WEIBO_MSG);
        mSharePlatform = mWeiboShareRef.get();
        if (mSharePlatform instanceof WeiboShare) {
            mShareResultCallback = ((WeiboShare) mSharePlatform).getCallback();
            weiboMultiMessage = ((WeiboShare) mSharePlatform).getMessage();
            mShareContentType = ((WeiboShare) mSharePlatform).getShareContentType();
        } else {
            onWbShareFail();
        }

        // 初始化WbShareHandler，并注册应用到微博
        if (mWbShareHandler == null) {
            mWbShareHandler = new WbShareHandler(this);
            mWbShareHandler.registerApp();
        }
        mWbShareHandler.shareMessage(weiboMultiMessage, false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWbShareHandler.doResultIntent(intent, this);
    }

    @Override
    public void onWbShareSuccess() {
        if (mShareResultCallback != null && mSharePlatform != null) {
            mShareResultCallback.onShareSuccess(mSharePlatform, mShareContentType);
        }
        finish();
    }

    @Override
    public void onWbShareCancel() {
        if (mShareResultCallback != null && mSharePlatform != null) {
            mShareResultCallback.onShareCancel(mSharePlatform, mShareContentType);
        }
        finish();
    }

    @Override
    public void onWbShareFail() {
        if (mShareResultCallback != null && mSharePlatform != null) {
            mShareResultCallback.onShareFailed(mSharePlatform, mShareContentType, 0, "fail");
        }
        finish();
    }
}
