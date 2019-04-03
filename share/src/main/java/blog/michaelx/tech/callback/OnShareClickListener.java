package blog.michaelx.tech.callback;

import blog.michaelx.tech.platform.ISharePlatform;

/**
 * 分享弹窗：平台被点击监听
 */
public interface OnShareClickListener {
    /**
     * 分享点击回调
     *
     * @param platform 被点击的平台
     * @see blog.michaelx.tech.ShareConstants#PLAT_FORM_WECHAT
     */
    void onShareClick(ISharePlatform platform);
}
