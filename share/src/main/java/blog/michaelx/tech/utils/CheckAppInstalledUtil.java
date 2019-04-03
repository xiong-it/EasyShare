package blog.michaelx.tech.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import blog.michaelx.tech.ShareConstants;

/**
 * 检测分享的应用是否已安装
 * <p>
 */

public class CheckAppInstalledUtil {

    /**
     * 检查是否安装微信
     */
    public static boolean isWechatInstalled(Context context) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, ShareConstants.WX_APP_ID);
        return api.isWXAppInstalled(); // 微信SDK所提供的方法
//        return isWXAppInstalledd("com.tencent.mm", context);
    }


    /**
     * 检查是否安装QQ
     */
    public static boolean isQQInstalled(Context context) {
        return isAppInstalled("com.tencent.mobileqq", context);
    }

    public static boolean isAppInstalled(String pkgName, Context context) {
//        com.tencent.mm
        final PackageManager packageManager = context.getPackageManager(); // 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0); // 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(pkgName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
