package com.ccl.bspatch;

import android.content.Context;

/**
 * Created by ccl on 2019/11/12.
 * bspatch增量更新合成
 */
public class XBspatchUtil {
    static {
        System.loadLibrary("xbspatch");
    }

    /**
     * 使用apk当前版本apk与差分包合成
     *
     * @param context 旧apk地址
     * @param patchPath 补丁包地址
     * @param newApkPatch 合成新apk地址
     */
    public static void getNewApk(Context context, String patchPath, String newApkPatch) {
        synchronized (XBspatchUtil.class) {
            try {
                String oldApkPath = context.getApplicationInfo().sourceDir;
                bsPatch(oldApkPath, patchPath, newApkPatch);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 调用bspatch合成apk
     *
     * @param oldApkPath  旧apk地址
     * @param patchPath   补丁包地址
     * @param newApkPatch 合成新apk地址
     */
    public static native void bsPatch(String oldApkPath, String patchPath, String newApkPatch);
}
