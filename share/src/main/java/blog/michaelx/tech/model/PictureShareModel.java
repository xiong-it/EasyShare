package blog.michaelx.tech.model;

/**
 * 图片分享
 *
 * @author MichaelX
 * @date 2018/11/12
 * <p>
 * 可用作二维码分享等场景
 * </p>
 */
public abstract class PictureShareModel implements IShareModel {
    public abstract String sharePicturePath();
}
