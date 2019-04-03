package blog.michaelx.tech.model;

import android.graphics.Bitmap;

/**
 * @author MichaelX
 * @date 2018/10/25
 *
 */
public abstract class FeedDetailShareModel implements IShareModel {

    /**
     * 标题
     *
     * @return
     */
    public abstract String title();

    /**
     * 概要描述
     *
     * @return
     */
    public abstract String subTitle();

    /**
     * feed详情对应的url
     *
     * @return
     */
    public abstract String linkUrl();

    /**
     * 要分享的封面图本地路径
     *
     * @return
     */
    public abstract String coverLocalPath();

    public abstract Bitmap coverBitmap();

}
