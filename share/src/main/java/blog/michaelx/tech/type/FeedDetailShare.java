package blog.michaelx.tech.type;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.factory.AbsPlatformDataFactory;
import blog.michaelx.tech.factory.FeedDetailDataFactory;
import blog.michaelx.tech.model.IShareModel;
import blog.michaelx.tech.platform.ISharePlatform;

/**
 * feed详情分享
 */
public class FeedDetailShare extends AbsShareContentType {

    public FeedDetailShare(ISharePlatform share, IShareModel shareModel) {
        super(share, shareModel);
    }

    @Override
    public AbsPlatformDataFactory getFactory() {
        return new FeedDetailDataFactory();
    }

    @Override
    public int getType() {
        return ShareConstants.FEED_DETAIL_TYPE;
    }

}
