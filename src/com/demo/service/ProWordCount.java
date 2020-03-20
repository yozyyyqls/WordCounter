package com.demo.service;

import com.demo.Utils.Tools;

import java.io.*;

public class ProWordCount {

    /**
     * 批量处理文件
     * @param subModel 选择的处理模式
     * @param filePath 文件绝对路径
     * @return Nun 字符/单词/行数
     * @throws IOException 文件不存在
     */
    public static int batchCount(String subModel, String filePath) throws IOException {
        File file = new File(filePath);
        int Num=0;
        if(file.isFile()){
            byte[] fileByte = Tools.fileToByte(filePath);
            Num = CountImp.valueOf(subModel).op(fileByte);
        }else{
            if(file.isDirectory()){
                File[] fileList = file.listFiles();
                if(fileList==null) return 0; //如果该目录下无文件
                for (File value : fileList) {
                    Num += batchCount(subModel, value.getAbsolutePath());
                }
            }else{
                //输入文件路径无效
                return 0;
            }
        }
        return Num;
    }

    /**
     *计算文件代码行数、注释行数、空白行数
     * @param filePath 文件路径
     * @return num[0]为代码行数，num[1]为注释行数，num[2]为空白行数
     * @throws IOException 文件不存在
     */
    public static int[] proCount(String filePath) throws IOException {
        int[] num = new int[3];
        File file = new File(filePath);
        if(!file.exists()) {
            System.out.println("文件路径出错");
            return num; //文件不存在
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int codeLineNum=0;
        int noteLineNum=0;
        int blankLineNum=0;
        String fileLine;
        while ((fileLine=bufferedReader.readLine())!=null){
            fileLine = fileLine.trim();
            //计算注释行
            if(fileLine.startsWith("/") || fileLine.startsWith("*")){
                noteLineNum++;
            }
            //计算空行
            else if(fileLine.trim().isEmpty() || fileLine.trim().length()==1){
                blankLineNum++;
            }
            else {
                codeLineNum++;
            }
        }
        bufferedReader.close();
        num[0] = codeLineNum;
        num[1] = noteLineNum;
        num[2] = blankLineNum;
        return num;
    }
}
