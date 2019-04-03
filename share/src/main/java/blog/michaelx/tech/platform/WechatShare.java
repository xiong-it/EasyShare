package blog.michaelx.tech.platform;

import blog.michaelx.tech.ShareConstants;

/**
 * 分享到微信
 */
public class WechatShare extends AbsWechatShare {

    @Override
    public int getPlatform() {
        return ShareConstants.PLAT_FORM_WECHAT;
    }
}
