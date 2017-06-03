package com.bp.footballcommunity.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by BP on 2017/6/3.
 */
public class BitmapUtils {
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 根据Resources压缩图片
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 若要对图片进行压缩，必须先设置Option的inJustDecodeBounds为true
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(res, resId, options);
        //options.inSampleSize是图片的压缩比
        //原来大小是100 100，options.inSampleSize为1，则不变，options.inSampleSize为2，则压缩成50 50。
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        //获取到inSampleSize值之后，重新设置options.inJustDecodeBounds为false，不能修改option
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(res, resId, options);
        return src;
    }

    /**
     * 根据地址压缩图片
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFd(String pathName, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 若要对图片进行压缩，必须先设置Option的inJustDecodeBounds为true
        options.inJustDecodeBounds = true;
        //通过decodeFile获取到照片的高度和宽度，存进一个图片地址即可
//        BitmapFactory.decodeFile(pathName, options);
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        //获取到inSampleSize值之后，重新设置options.inJustDecodeBounds为false，不能修改option
//        options.inJustDecodeBounds = false;
        //用BitmapFactory中的decodeFile方法即可获取到压缩后的照片，这样在加载图片时就可以避免OOM的出现了。
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return src;
    }
}
