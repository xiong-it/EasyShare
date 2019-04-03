package blog.michaelx.tech.factory;

import android.os.Bundle;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

import blog.michaelx.tech.model.PictureShareModel;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * 图片分享生成工厂
 */
public class PictureDataFactory extends AbsPlatformDataFactory<PictureShareModel> {

    @Override
    public Bundle createQQ(PictureShareModel shareModel) {
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, shareModel.sharePicturePath());
        return params;
    }

    @Override
    public WeiboMultiMessage createWeibo(PictureShareModel shareModel) {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        ImageObject imageObject = new ImageObject();
        imageObject.imagePath = shareModel.sharePicturePath();
        weiboMessage.imageObject = imageObject;
        return weiboMessage;
    }

    @Override
    public SendMessageToWX.Req createWechat(PictureShareModel shareModel) {
        WXMediaMessage msg = new WXMediaMessage();
        WXMediaMessage.IMediaObject imageObj = new WXImageObject();
        ((WXImageObject) imageObj).imagePath = shareModel.sharePicturePath();
        msg.mediaObject = imageObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 好友聊天
        req.scene = WXSceneSession;

        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        return req;
    }

    @Override
    public SendMessageToWX.Req createWechatMoments(PictureShareModel shareModel) {
        WXMediaMessage msg = new WXMediaMessage();
        WXMediaMessage.IMediaObject imageObj = new WXImageObject();
        ((WXImageObject) imageObj).imagePath = shareModel.sharePicturePath();
        msg.mediaObject = imageObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 朋友圈
        req.scene = WXSceneTimeline;

        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        return req;
    }
}
