package blog.michaelx.tech.callback;

import blog.michaelx.tech.platform.ISharePlatform;
import blog.michaelx.tech.type.IShareContent;

/**
 * 分享结果回调
 */
public interface OnShareResultCallback {
    /**
     * 分享取消
     *
     * @param platform 分享的平台
     * @param type     分享的内容类型
     * @see blog.michaelx.tech.ShareConstants#PLAT_FORM_WECHAT etc.
     * @see blog.michaelx.tech.ShareConstants#BITMAP_TYPE etc.
     */
    void onShareCancel(ISharePlatform platform, IShareContent type);

    /**
     * 分享失败
     *
     * @param platform 分享的平台
     * @param type     分享的内容类型
     * @param errCode  失败错误码
     * @param errMsg   失败原因
     * @see blog.michaelx.tech.ShareConstants#PLAT_FORM_WECHAT etc.
     * @see blog.michaelx.tech.ShareConstants#BITMAP_TYPE etc.
     */
    void onShareFailed(ISharePlatform platform, IShareContent type, int errCode, String errMsg);

    /**
     * 分享成功
     *
     * @param platform 分享的平台
     * @param type     分享的内容类型
     * @see blog.michaelx.tech.ShareConstants#PLAT_FORM_WECHAT etc.
     * @see blog.michaelx.tech.ShareConstants#BITMAP_TYPE etc.
     */
    void onShareSuccess(ISharePlatform platform, IShareContent type);
}
