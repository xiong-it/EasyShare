package blog.michaelx.tech.platform;

import android.app.Activity;

import com.sina.weibo.sdk.api.WeiboMultiMessage;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.WeiboShareProxyActivity;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.type.AbsShareContentType;

/**
 * 分享到微博
 */
public class WeiboShare implements ISharePlatform<WeiboMultiMessage, AbsShareContentType> {
    private OnShareResultCallback mResultCallback;
    private WeiboMultiMessage mWeiboMultiMessage;
    private AbsShareContentType mShareContentType;

    @Override
    public void share(Activity activity, OnShareResultCallback callback, AbsShareContentType contentTyp,
                      WeiboMultiMessage weiboMultiMessage) {
        if (weiboMultiMessage == null) {
            return;
        }
        mResultCallback = callback;
        mWeiboMultiMessage = weiboMultiMessage;
        mShareContentType = contentTyp;

        WeiboShareProxyActivity.startWeiboShare(activity, this);
//        Intent weiboShare = new Intent(activity, WeiboShareProxyActivity.class);
//        activity.startActivity(weiboShare);
    }

    @Override
    public int getPlatform() {
        return ShareConstants.PLAT_FORM_WEIBO;
    }

    public OnShareResultCallback getCallback() {
        return mResultCallback;
    }

    public WeiboMultiMessage getMessage() {
        return mWeiboMultiMessage;
    }

    public AbsShareContentType getShareContentType() {
        return mShareContentType;
    }
}
