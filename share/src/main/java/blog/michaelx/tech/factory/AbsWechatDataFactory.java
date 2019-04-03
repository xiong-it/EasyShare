package blog.michaelx.tech.factory;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import blog.michaelx.tech.model.IShareModel;

/**
 * 微信分项数据生产工厂
 *
 * @param <ShareMode>
 */
public abstract class AbsWechatDataFactory<ShareMode extends IShareModel> implements ISharePlatformDataFactory {
    public abstract SendMessageToWX.Req createWechat(ShareMode shareModel);

    public abstract SendMessageToWX.Req createWechatMoments(ShareMode shareModel);
}
