package com.demo.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tools {
    /**
     * 获取文件的二进制信息
     * @param filePath 文件路径
     * @return fileByte[] 存储文件二进制数据数组
     * @throws IOException 文件不存在
     */
    public static byte[] fileToByte(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("输入文件路径不存在");
            return null;
        }
        FileInputStream fis = new FileInputStream(file);
        //文件为空文件
        if(fis.available()==0){
            return null;
        }
        //不为空文件
        byte[] fileByte = new byte[fis.available()];
        fis.read(fileByte);
        fis.close();
        return fileByte;
    }

    /**
     * 字符串匹配
     * @param REGEX 需要查找字符串
     * @param INPUT 被查找的字符串
     * @return num 查找到的数量
     */
    public static int searchStr(String REGEX, String INPUT){
        int num = 0;
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);
        while(m.find()){
            num++;
        }
        return num;
    }

    /**
     * 模式名称转换
     * @param model 用户输入的模式名称
     * @return 转换后的名称
     */
    public static String modelTransf(String model) {
        switch (model) {
            case "-c":
                return "OP_C";
            case "-w":
                return "OP_W";
            case "-l":
                return "OP_L";
            case "-s":
                return "OP_S";
            default:
                System.out.println("输入的模式名称有误，请重新输入");
                return model;
        }
    }
}
