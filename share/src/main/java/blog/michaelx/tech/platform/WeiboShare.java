package blog.michaelx.tech.platform;

import android.app.Activity;

import com.sina.weibo.sdk.api.WeiboMultiMessage;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.type.AbsShareContentType;
import blog.michaelx.tech.ui.WeiboShareProxyActivity;

/**
 * 分享到微博
 * <p>
 *     ISharePlatform实现类本来无需公开，只需要被SharePlatformFactory使用，但是：
 *     fixme：由于WeiboShare需要被外部包中的WeiboShareProxyActivity引用，所有只好使用public修饰了。
 * </p>
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
