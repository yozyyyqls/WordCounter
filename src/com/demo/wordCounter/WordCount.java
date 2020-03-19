package com.demo.wordCounter;

import com.demo.control.ControlMain;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) throws Exception {
        //System.out.println(WordCount.wc("-s","-l", "C:\\Users\\15191\\Desktop\\imageTest\\WordCounter.java"));
        Scanner scan = new Scanner(System.in);
        String model = "";
        String subModel = "";
        String filePath = "";
        int num=0;
        if(scan.hasNext()){
            model = scan.next();
                if(model.equals("-s")){
                    subModel = scan.next();
                    filePath = scan.next();
                    num = wc(model, subModel, filePath);
                }
                else if(model.equals("-x")){
                    ControlMain.main(args); //调用图形界面
                }
                else{
                    filePath = scan.next();
                    num = wc(model, filePath);
                }
            System.out.println(num);
        }else{
            System.out.println("您未输入任何字符");
        }
    }

    /**
     *单处理调用
     * @param model 处理模式：-c，-w，-l
     * @param filePath 文件绝对路径
     * @return 字符/单词/行数
     * @throws IOException 文件不存在
     */
    private static int wc(String model, String filePath) throws IOException {
        byte[] fileByte = Tools.fileToByte(filePath);
        model = Tools.modelTransf(model);
        return CountType.valueOf(model).op(fileByte);
    }

    /**
     * 批量处理调用
     * @param model 批量处理模式：-s
     * @param subModel 选择子模式：-c，-w，-l
     * @param filePath 文件路径
     * @return 字符/单词/行数
     * @throws IOException 文件不存在
     */
     public static int wc(String model, String subModel, String filePath) throws IOException {
        subModel = Tools.modelTransf(subModel);
        return WordCount.batchCount(subModel, filePath);
    }

    /**
     * 批量处理文件
     * @param subModel 选择的处理模式
     * @param filePath 文件绝对路径
     * @return Nun 字符/单词/行数
     * @throws IOException 文件不存在
     */
    private static int batchCount(String subModel, String filePath) throws IOException {
        File file = new File(filePath);
        int Num=0;
        if(file.isFile()){
            byte[] fileByte = Tools.fileToByte(filePath);
            Num = CountType.valueOf(subModel).op(fileByte);
        }else{
            if(file.isDirectory()){
                File[] fileList = file.listFiles();
                if(fileList==null) return 0; //如果该目录下无文件
                for (File value : fileList) {
                    Num += WordCount.batchCount(subModel, value.getAbsolutePath());
                }
            }else{
                //输入文件路径无效
                return 0;
            }
        }
        return Num;
    }

}