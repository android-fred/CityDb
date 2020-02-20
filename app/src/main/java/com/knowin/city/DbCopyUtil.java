package com.knowin.city;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DbCopyUtil {

    static void copyDb(Context ctx, String dbName) {
        File filesDir = ctx.getDataDir();// 获取data/data/xxxapp目录的文件夹
        File dbpath = new File(filesDir.getPath()+"/databases");
        if(!dbpath.exists()) {
            dbpath.mkdir();
        }
        System.out.println("filesDir:" + filesDir.getAbsolutePath());
        File outFile = new File(dbpath, dbName);

        if (outFile.exists()) {//确保只复制一次
            Log.e("DbCopyUtil", String.format("数据库%s已经存在,无需拷贝!", dbName));
            return;
        }

        FileOutputStream out = null;
        InputStream in = null;
        try {
            out = new FileOutputStream(outFile);
            in = ctx.getAssets().open(dbName);

            int len = 0;
            byte[] buffer = new byte[1024];

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("拷贝数据库" + dbName + "成功!");
    }
}
