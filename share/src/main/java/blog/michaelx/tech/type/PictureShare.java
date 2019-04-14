package blog.michaelx.tech.type;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.factory.AbsPlatformDataFactory;
import blog.michaelx.tech.factory.PictureDataFactory;
import blog.michaelx.tech.model.PictureShareModel;
import blog.michaelx.tech.platform.ISharePlatform;

/**
 * 分享图片
 *
 * @author MichaelX
 * @date 2018/11/12
 *
 */
class PictureShare extends AbsShareContentType<PictureShareModel> {

    public PictureShare(ISharePlatform share, PictureShareModel shareModel) {
        super(share, shareModel);
    }

    @Override
    public AbsPlatformDataFactory getFactory() {
        return new PictureDataFactory();
    }

    @Override
    public int getType() {
        return ShareConstants.PICTURE_TYPE;
    }
}
