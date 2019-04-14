package blog.michaelx.tech.type;

import blog.michaelx.tech.model.BitmapShareModel;
import blog.michaelx.tech.model.FeedDetailShareModel;
import blog.michaelx.tech.model.IShareModel;
import blog.michaelx.tech.model.PictureShareModel;
import blog.michaelx.tech.platform.ISharePlatform;

/**
 * 分享内容工厂
 *
 * @author MichaelX
 * @version 1.1
 * @since 2019/4/14
 */
public class ShareContentFactory {

    public static IShareContent newShareContent(ISharePlatform platform, IShareModel model) {
        IShareContent content = null;
        if (model == null || platform == null) {
            throw new NullPointerException("platform or model can not null");
        }
        if (model instanceof BitmapShareModel) {
            return new BitmapShare(platform, (BitmapShareModel) model);
        } else if (model instanceof PictureShareModel) {
            return new PictureShare(platform, (PictureShareModel) model);
        }
        return content;
    }
}
