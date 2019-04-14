package blog.michaelx.tech.platform;

import blog.michaelx.tech.ShareConstants;

/**
 * 分享到朋友圈
 */
class WechatMomentsShare extends AbsWechatShare {

    // 朋友圈最大标题长度
    public static final int MAX_TITLE_LENGTH = 30;

    @Override
    public int getPlatform() {
        return ShareConstants.PLAT_FORM_WECHAT_MOMENTS;
    }

}
