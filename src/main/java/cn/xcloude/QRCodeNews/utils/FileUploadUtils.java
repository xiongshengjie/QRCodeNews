package cn.xcloude.QRCodeNews.utils;

import java.util.Calendar;
import java.util.UUID;

public class FileUploadUtils {
    /**
     * 真实文件名
     *
     * @param fileName
     * @return
     */
    public static String subFileName(String fileName) {
        //获取文件名
        int index = fileName.lastIndexOf("\\");
        if (index == -1) {
            return fileName;
        }
        return fileName.substring(index + 1);
    }

    public static String generateRandonFileName(String fileName) {
        // 获得扩展名
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            String ext = fileName.substring(index);
            return UUID.randomUUID().toString() + ext;
        }
        return UUID.randomUUID().toString();
    }

    // 根据日期生成二级目录
    public static String generateRandomDir() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = (calendar.get(Calendar.MONTH) + 1);
        int day = calendar.get(Calendar.DATE);

        return "/" + year + "/" + month + "/" + day;
    }

    // 获得随机UUID文件名
    public static String generateRandonFileNameJpg() {
        return UUID.randomUUID().toString() + ".jpg";
    }

}
