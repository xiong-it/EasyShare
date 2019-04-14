package blog.michaelx.tech.type;

import android.app.Activity;
import android.os.Bundle;

import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.factory.AbsPlatformDataFactory;
import blog.michaelx.tech.model.IShareModel;
import blog.michaelx.tech.platform.ISharePlatform;

/**
 * 分享内容抽象基类
 */
public abstract class AbsShareContentType<Model extends IShareModel> implements IShareContent<AbsPlatformDataFactory> {
    protected ISharePlatform mSharePlatform;
    private Object shareData;

    public AbsShareContentType(ISharePlatform share, Model shareModel) {
        this.mSharePlatform = share;
        buildShareData(shareModel);
    }

    private void buildShareData(Model shareModel) {
        AbsPlatformDataFactory factory = (AbsPlatformDataFactory) getFactory();
        Bundle bundle = factory.createQQ(shareModel);
        WeiboMultiMessage msg = factory.createWeibo(shareModel);
        SendMessageToWX.Req req = factory.createWechat(shareModel);
        SendMessageToWX.Req momentsReq = factory.createWechatMoments(shareModel);
        shareData = getPlatformData(bundle, msg, req, momentsReq);
    }

    @Override
    public void share(Activity activity, OnShareResultCallback callback) {
        if (mSharePlatform == null) {
            return;
        }
        mSharePlatform.share(activity, callback, this, shareData);
    }

    protected Object getPlatformData(Bundle qqData, WeiboMultiMessage msg, SendMessageToWX.Req req,
                                     SendMessageToWX.Req momentsReq) {
        if (mSharePlatform.getPlatform() == ShareConstants.PLAT_FORM_TENCHENT_QQ) {
            return qqData;
        } else if (mSharePlatform.getPlatform() == ShareConstants.PLAT_FORM_WECHAT) {
            return req;
        } else if (mSharePlatform.getPlatform() == ShareConstants.PLAT_FORM_WEIBO) {
            return msg;
        }
        return momentsReq;
    }

}
