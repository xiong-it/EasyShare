package blog.michaelx.tech.factory;

import android.os.Bundle;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import blog.michaelx.tech.model.FeedDetailShareModel;
import blog.michaelx.tech.platform.WechatMomentsShare;
import blog.michaelx.tech.utils.WXShareUtil;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * feed详情分享数据生产工厂
 */
public class FeedDetailDataFactory extends AbsPlatformDataFactory<FeedDetailShareModel> {

    @Override
    public Bundle createQQ(FeedDetailShareModel shareModel) {
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareModel.title()); // 标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareModel.subTitle()); // 摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareModel.linkUrl()); // 内容链接地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareModel.coverLocalPath()); // 封面
        return params;
    }

    @Override
    public WeiboMultiMessage createWeibo(FeedDetailShareModel shareModel) {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        TextObject textObject = new TextObject();
        StringBuffer shareBuilder = new StringBuffer();
        shareBuilder.append(shareModel.title());
        shareBuilder.append("（发布在\"源点公社App\"）:");
        shareBuilder.append(shareModel.subTitle());
        textObject.text = shareBuilder.toString();
        ImageObject imageObject = new ImageObject();
        imageObject.imagePath = shareModel.coverLocalPath();
        imageObject.actionUrl = shareModel.linkUrl();
        weiboMessage.textObject = textObject;
        weiboMessage.imageObject = imageObject;
        return weiboMessage;
    }

    @Override
    public SendMessageToWX.Req createWechat(FeedDetailShareModel shareModel) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareModel.linkUrl();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.description = shareModel.subTitle();
        msg.thumbData = WXShareUtil.bmpToByteArray(shareModel.coverBitmap(), true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        msg.title = shareModel.title();
        // 好友聊天
        req.scene = WXSceneSession;

        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        return req;
    }

    @Override
    public SendMessageToWX.Req createWechatMoments(FeedDetailShareModel shareModel) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareModel.linkUrl();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.description = shareModel.subTitle();
        msg.thumbData = WXShareUtil.bmpToByteArray(shareModel.coverBitmap(), true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 防止微信朋友圈分享标题过长
        if (shareModel.subTitle().length() > WechatMomentsShare.MAX_TITLE_LENGTH) {
            msg.title = shareModel.subTitle().substring(0, WechatMomentsShare.MAX_TITLE_LENGTH).concat("…");
        } else {
            msg.title = shareModel.subTitle();
        }
        // 朋友圈
        req.scene = WXSceneTimeline;
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        return req;
    }
}
