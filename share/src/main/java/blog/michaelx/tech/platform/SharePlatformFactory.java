package blog.michaelx.tech.platform;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import blog.michaelx.tech.ShareConstants;

import static blog.michaelx.tech.ShareConstants.PLAT_FORM_TENCHENT_QQ;
import static blog.michaelx.tech.ShareConstants.PLAT_FORM_WECHAT;
import static blog.michaelx.tech.ShareConstants.PLAT_FORM_WECHAT_MOMENTS;
import static blog.michaelx.tech.ShareConstants.PLAT_FORM_WEIBO;

/**
 * 分享平台对象生产工厂
 *
 * @author MichaelX
 * @version 1.1
 * @since 2019/4/14
 */
public class SharePlatformFactory {

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({PLAT_FORM_WECHAT, PLAT_FORM_WECHAT_MOMENTS,
            PLAT_FORM_TENCHENT_QQ, PLAT_FORM_WEIBO})
    public @interface PlatformType {}

    public static ISharePlatform getSharePlatform(@PlatformType int platformType) {
        ISharePlatform platform = null;
        if (platformType == PLAT_FORM_WECHAT) {
            platform = new WechatShare();
        } else if (platformType == PLAT_FORM_WECHAT_MOMENTS) {
            platform = new WechatMomentsShare();
        } else if (platformType == PLAT_FORM_TENCHENT_QQ) {
            platform = new TenchentQQShare();
        } else if (platformType == PLAT_FORM_WEIBO) {
            platform = new WeiboShare();
        } else {
            throw new IllegalArgumentException("platformType = " + platformType + " is not supported");
        }
        return platform;
    }
}
