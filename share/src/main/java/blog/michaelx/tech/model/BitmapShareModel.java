package blog.michaelx.tech.model;

import android.graphics.Bitmap;

/**
 * Bitmap分享抽象类
 * <p>
 *     可用作二维码分享等场景
 * </p>
 */
public abstract class BitmapShareModel implements IShareModel {
    public abstract Bitmap shareBitmap();
}
