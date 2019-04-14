package blog.michaelx.tech.platform;

import android.app.Activity;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.type.IShareContent;

/**
 * 分享平台抽象接口
 *
 * @param <Data>             分享平台对应的接收数据
 *                           比如QQ对应Bundle
 * @param <ShareContentType> 分享数据类型
 */
public interface ISharePlatform<Data, ShareContentType extends IShareContent> {
    int ERR_CODE_WX_NOT_INSTALL = -100;
    String ERR_MSG_WX_NOT_INSTALL = "微信未安装或者版本过低";

    /**
     * 分享
     *
     * @param activity   Activity实例
     * @param callback   分享结果毁掉
     * @param contentTyp 分享类型
     * @param msg        分享数据
     */
    void share(Activity activity, OnShareResultCallback callback, ShareContentType contentTyp, Data msg);


    /**
     * @return 返回分享平台类型
     * @see ShareConstants#PLAT_FORM_WECHAT
     * @see ShareConstants#PLAT_FORM_TENCHENT_QQ
     * @see ShareConstants#PLAT_FORM_WECHAT_MOMENTS
     * @see ShareConstants#PLAT_FORM_WEIBO
     */
    int getPlatform();
}
