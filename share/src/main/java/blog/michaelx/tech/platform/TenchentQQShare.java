package blog.michaelx.tech.platform;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import blog.michaelx.tech.ShareConstants;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.type.AbsShareContentType;

/**
 * 分享到qq
 */
class TenchentQQShare implements ISharePlatform<Bundle, AbsShareContentType> {

    private OnShareResultCallback mShareResultCallback;
    private AbsShareContentType mShareContentType;

    IUiListener mIUiListener = new IUiListener() {

        @Override
        public void onComplete(Object o) {
            if (mShareResultCallback != null) {
                mShareResultCallback.onShareSuccess(TenchentQQShare.this, mShareContentType);
            }
        }

        @Override
        public void onError(UiError uiError) {
            if (mShareResultCallback != null) {
                mShareResultCallback.onShareFailed(TenchentQQShare.this, mShareContentType,
                        uiError.errorCode, uiError.errorMessage);
            }
        }

        @Override
        public void onCancel() {
            if (mShareResultCallback != null) {
                mShareResultCallback.onShareCancel(TenchentQQShare.this, mShareContentType);
            }
        }
    };

    @Override
    public void share(Activity activity, OnShareResultCallback callback,
                      AbsShareContentType contentTyp, Bundle data) {
        mShareContentType = contentTyp;
        mShareResultCallback = callback;
        Tencent tencent = Tencent.createInstance(ShareConstants.QQ_APP_ID, activity.getApplicationContext());
        tencent.shareToQQ(activity, data, mIUiListener);
    }

    @Override
    public int getPlatform() {
        return ShareConstants.PLAT_FORM_TENCHENT_QQ;
    }
}
