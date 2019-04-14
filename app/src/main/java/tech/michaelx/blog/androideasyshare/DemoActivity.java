package tech.michaelx.blog.androideasyshare;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import blog.michaelx.tech.type.ShareContentFactory;
import blog.michaelx.tech.ui.IShareDialog;
import blog.michaelx.tech.callback.OnShareClickListener;
import blog.michaelx.tech.callback.OnShareResultCallback;
import blog.michaelx.tech.model.BitmapShareModel;
import blog.michaelx.tech.platform.ISharePlatform;
import blog.michaelx.tech.type.IShareContent;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        findViewById(R.id.share_clk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
            }
        });
    }

    IShareDialog dialog;

    /**
     * 显示分享弹窗
     */
    private void showShareDialog() {
        dialog = new ShareDialog(this).setOnShareClickListener(new OnShareClickListener() {
            @Override
            public void onShareClick(ISharePlatform platform) {
                dialog.dismiss();
                doShare(platform);
            }
        });
        dialog.show();
    }

    /**
     * 进行分享
     *
     * @param platform 分享目标平台
     */
    private void doShare(ISharePlatform platform) {
        // 1. 分享数据Model实例化组装
        BitmapShareModel model = new BitmapShareModel() {
            @Override
            public Bitmap shareBitmap() {
                return getShareBitmap();
            }
        };

        // 2. 实例化分享数据类型
        IShareContent bitmapShare = ShareContentFactory.newShareContent(platform, model);

        // 3. 将对应数据分享出去
        bitmapShare.share(this, new OnShareResultCallback() {
            @Override
            public void onShareCancel(ISharePlatform platform, IShareContentType type) {
                // 分享取消
            }

            @Override
            public void onShareFailed(ISharePlatform platform, IShareContentType type, int errCode, String errMsg) {
                // 分享失败
            }

            @Override
            public void onShareSuccess(ISharePlatform platform, IShareContentType type) {
                // 分享成功
            }
        });
    }

    /**
     * 获取待分享数据-位图实例
     *
     * @return
     */
    private Bitmap getShareBitmap() {
        Drawable drawable = findViewById(R.id.share_img).getBackground();
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }
}
