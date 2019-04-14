package blog.michaelx.tech.ui;

import blog.michaelx.tech.callback.OnShareClickListener;

/**
 * 分享弹窗接口
 * <p>
 *     在定义分享弹窗时，需要实现该接口
 * </p>
 */
public interface IShareDialog {

    <T extends IShareDialog> T setOnShareClickListener(OnShareClickListener listener);

    void show();

    void dismiss();
}
