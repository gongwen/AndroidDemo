package gw.com.code.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by GongWen on 16/10/24.
 */

public class FileUtil {
    public static String read(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFile(f);
            }
        } else {
            try {
                file.delete();
            } catch (Exception e) {
            }
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isEmptyFile(File file) {
        return file == null || file.length() == 0 || !file.canRead();
    }

    public static long getFileSize(File file) {
        long length = 0;
        if (file == null || !file.exists())
            return length;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                length += getFileSize(f);
            }
        } else {
            length += file.length();
        }
        return length;
    }
}
