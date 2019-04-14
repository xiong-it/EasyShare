package blog.michaelx.tech.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import blog.michaelx.tech.callback.OnShareClickListener;
import blog.michaelx.tech.platform.ISharePlatform;
import blog.michaelx.tech.platform.SharePlatformFactory;

import static blog.michaelx.tech.ShareConstants.PLAT_FORM_TENCHENT_QQ;
import static blog.michaelx.tech.ShareConstants.PLAT_FORM_WECHAT;
import static blog.michaelx.tech.ShareConstants.PLAT_FORM_WECHAT_MOMENTS;
import static blog.michaelx.tech.ShareConstants.PLAT_FORM_WEIBO;

/**
 * 分享弹窗抽象类
 *
 * @author MichaelX
 * @version 1.1
 * @since 2019/4/14
 */
public abstract class AbsShareDialog extends Dialog implements IShareDialog {
    private OnShareClickListener mListener;

    public AbsShareDialog(Context context) {
        super(context);
    }

    public AbsShareDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AbsShareDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public <T extends IShareDialog> T setOnShareClickListener(OnShareClickListener listener) {
        mListener = listener;
        return (T) this;
    }

    /**
     * 点击了分享至微信
     */
    protected void onClickWechat() {
        if (mListener != null) {
            mListener.onShareClick(SharePlatformFactory.getSharePlatform(PLAT_FORM_WECHAT));
        }
    }

    /**
     * 分享至朋友圈
     */
    protected void onClickWechatMoments() {
        if (mListener != null) {
            mListener.onShareClick(SharePlatformFactory.getSharePlatform(PLAT_FORM_WECHAT_MOMENTS));
        }
    }

    /**
     * 分享至qq
     */
    protected void onClickQQ() {
        if (mListener != null) {
            mListener.onShareClick(SharePlatformFactory.getSharePlatform(PLAT_FORM_TENCHENT_QQ));
        }
    }

    /**
     * 分享至微博
     */
    protected void onClickWeibo() {
        if (mListener != null) {
            mListener.onShareClick(SharePlatformFactory.getSharePlatform(PLAT_FORM_WEIBO));
        }
    }

}
