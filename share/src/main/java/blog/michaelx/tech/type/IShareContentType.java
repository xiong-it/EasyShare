package blog.michaelx.tech.type;

import android.app.Activity;

import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.factory.ISharePlatformDataFactory;
import blog.michaelx.tech.ShareConstants;

public interface IShareContentType<Factory extends ISharePlatformDataFactory> {

    /**
     * 子类实现数据工厂类生成
     *
     * @param <Factory>
     * @return 数据工厂类实例
     */
    <Factory> ISharePlatformDataFactory getFactory();

    /**
     * @return 返回分享类型
     * @see ShareConstants#FEED_DETAIL_TYPE
     * @see ShareConstants#PERSONAL_INFO_TYPE
     */
    int getType();

    /**
     * 进行分享
     *
     * @param activity
     * @param callback
     */
    void share(Activity activity, OnShareResultCallback callback);
}
