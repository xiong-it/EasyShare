package blog.michaelx.tech.type;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.factory.BitmapDataFactory;
import blog.michaelx.tech.factory.ISharePlatformDataFactory;
import blog.michaelx.tech.model.BitmapShareModel;
import blog.michaelx.tech.platform.ISharePlatform;

/**
 * 位图分享（到微信）
 */
public class BitmapShare implements IShareContentType<BitmapDataFactory> {
    protected ISharePlatform mSharePlatform;
    private SendMessageToWX.Req shareData;

    public BitmapShare(ISharePlatform share, BitmapShareModel shareModel) {
        this.mSharePlatform = share;
        buildShareData(shareModel);
    }

    private void buildShareData(BitmapShareModel shareModel) {
        BitmapDataFactory factory = (BitmapDataFactory) getFactory();
        SendMessageToWX.Req req = factory.createWechat(shareModel);
        SendMessageToWX.Req momentsReq = factory.createWechatMoments(shareModel);
        shareData = getPlatformData(req, momentsReq);
    }

    @Override
    public <Factory> ISharePlatformDataFactory getFactory() {
        return new BitmapDataFactory();
    }

    @Override
    public int getType() {
        return ShareConstants.BITMAP_TYPE;
    }

    @Override
    public void share(Activity activity, OnShareResultCallback callback) {
        if (mSharePlatform == null) {
            return;
        }
        mSharePlatform.share(activity, callback, this, shareData);
    }

    protected SendMessageToWX.Req getPlatformData(SendMessageToWX.Req req,
                                                  SendMessageToWX.Req momentsReq) {
        if (mSharePlatform.getPlatform() == ShareConstants.PLAT_FORM_WECHAT) {
            return req;
        }
        return momentsReq;
    }
}
