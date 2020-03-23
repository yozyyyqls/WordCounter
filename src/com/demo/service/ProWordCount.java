package com.demo.service;

import com.demo.Utils.FileUtil;

import java.io.*;

public class ProWordCount {

    /**
     * 批量处理文件
     * @param subModel 选择的处理模式
     * @param filePath 文件绝对路径
     * @throws IOException 文件不存在
     */
    public static void batchProCount(String subModel, String filePath) throws IOException {
        File file = new File(filePath);
        int num=0;
        if(file.isFile()){//如果是文件
            byte[] fileByte = FileUtil.fileToByte(filePath);
            num = CountImp.valueOf(subModel).op(fileByte);//根据模式的不同自动分辨使用哪个函数
            System.out.println("文件路径："+file.getAbsolutePath());
            System.out.println("计算结果："+ num +"\n");
        }else if(file.isDirectory()){//如果是目录
                File[] fileList = file.listFiles();
                if(fileList==null) {//如果该目录下无文件
                    System.out.println("文件不存在");
                    return;
                }
                for (File value : fileList) {
                    batchProCount(subModel, value.getAbsolutePath()); //递归处理
                }
            }
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
            System.out.println("文件不存在");
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
            //计算代码行
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
