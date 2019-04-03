package blog.michaelx.tech;

/**
 * Created by MichaelX on 2018/10/23.
 */

public class ShareConstants {
    // 定义分享平台
    public static final int PLAT_FORM_WEIBO = 0; // 微博
    public static final int PLAT_FORM_WECHAT = 1; // 微信
    public static final int PLAT_FORM_WECHAT_MOMENTS = 2; // 朋友圈
    public static final int PLAT_FORM_TENCHENT_QQ = 3; // qq

    // 定义分享数据类型
    public static final int FEED_DETAIL_TYPE = 0; // feed详情
    public static final int FEED_TYPE = 1; // feed
    public static final int PERSONAL_INFO_TYPE = 2; // 个人信息
    public static final int PERSONAL_NAME_CARD = 3; // 名片
    public static final int PICTURE_TYPE = 4; // 图片分享
    public static final int BITMAP_TYPE = 5; // 位图分享


    // TODO 修改为自己的id和key 清单中也需要修改
    public static final String QQ_APP_ID = "";

    public static final String WX_APP_ID = "";

    public static final String WB_APP_KEY = "";

    public static final String WB_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html"; // 默认

    public static final String WB_SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";


}
