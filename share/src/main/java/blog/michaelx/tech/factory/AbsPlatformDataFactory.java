package blog.michaelx.tech.factory;

import android.os.Bundle;

import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import blog.michaelx.tech.model.IShareModel;

/**
 * 分享数据生成抽象工厂
 */
public abstract class AbsPlatformDataFactory<ShareMode extends IShareModel> implements ISharePlatformDataFactory {
    public abstract Bundle createQQ(ShareMode shareModel);

    public abstract WeiboMultiMessage createWeibo(ShareMode shareModel);

    public abstract SendMessageToWX.Req createWechat(ShareMode shareModel);

    public abstract SendMessageToWX.Req createWechatMoments(ShareMode shareModel);
}
