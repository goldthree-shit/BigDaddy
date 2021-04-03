package com.hx.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileNameUtils {
    public static String getFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // 如果是获取的含有路径的文件名，那么截取掉多余的，只剩下文件名和后缀名
        if (fileName.indexOf("\\") > 0) {
            int index = fileName.lastIndexOf("\\");
            fileName = fileName.substring(index + 1);
        }
        // 当文件有后缀名时
        if (fileName.indexOf(".") >= 0) {
            // split()中放正则表达式; 转义字符"\\."代表 "."
            String[] fileNameSplitArrays = fileName.split("\\.");
            // 加上random戳,防止附件重名覆盖原文件
            fileName = "";
            int length = fileNameSplitArrays.length;
            for (int i = 0; i < length - 1; i++) {
                fileName += fileNameSplitArrays[i];
                if(i < length - 2) {
                    fileName += '.';
                }
            }
            fileName +=  (int) (Math.random() * 100000) + "." + fileNameSplitArrays[length-1];
        }
        // 当文件无后缀名时(如C盘下的hosts文件就没有后缀名)
        if (fileName.indexOf(".") < 0) {
            fileName = fileName + (int) (Math.random() * 100000);
        }
        return fileName;
    }
}
