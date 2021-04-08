package com.cisdi.qingzhu.webview.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * base64转换工具
 *
 * @author lh
 */
public class Base64Util {

    /**
     * bitmap转换为base64字符串
     *
     * @param bitmap bitmap
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * file转换为base64字符串
     */
    public static String fileToBase64(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        File temp = new File(filePath);
        if (!temp.exists()) {
            return null;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(temp);
            byte[] buffer = new byte[2048];
            baos = new ByteArrayOutputStream();
            int bytesRead;
            try {
                while ((bytesRead = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
            } catch (IOException var9) {
                var9.printStackTrace();
            }
            byte[] bytes = baos.toByteArray();
            return Base64.encodeToString(bytes, Base64.NO_WRAP);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                    baos = null;
                }
                if (fis != null) {
                    fis.close();
                    fis = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
