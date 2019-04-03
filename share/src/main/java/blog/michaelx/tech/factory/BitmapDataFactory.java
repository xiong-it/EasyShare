package blog.michaelx.tech.factory;

import android.graphics.Bitmap;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

import java.io.ByteArrayOutputStream;

import blog.michaelx.tech.model.BitmapShareModel;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * 微信&朋友分享数据工厂类
 */
public class BitmapDataFactory extends AbsWechatDataFactory<BitmapShareModel> {

    @Override
    public SendMessageToWX.Req createWechat(BitmapShareModel shareModel) {
        WXMediaMessage msg = new WXMediaMessage();
        WXMediaMessage.IMediaObject imageObj = new WXImageObject();
        byte[] shareData = null;
        try {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            shareModel.shareBitmap().compress(Bitmap.CompressFormat.JPEG, 85, var2);
            shareData = var2.toByteArray();
            var2.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        if (shareData != null) {
            ((WXImageObject) imageObj).imageData = shareData;
        }
        msg.mediaObject = imageObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 好友聊天
        req.scene = WXSceneSession;

        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        return req;
    }

    @Override
    public SendMessageToWX.Req createWechatMoments(BitmapShareModel shareModel) {
        WXMediaMessage msg = new WXMediaMessage();
        WXMediaMessage.IMediaObject imageObj = new WXImageObject();
        byte[] shareData = null;
        try {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            shareModel.shareBitmap().compress(Bitmap.CompressFormat.JPEG, 85, var2);
            shareData = var2.toByteArray();
            var2.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        if (shareData != null) {
            ((WXImageObject) imageObj).imageData = shareData;
        }
        msg.mediaObject = imageObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // 朋友圈
        req.scene = WXSceneTimeline;

        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        return req;
    }
}
