package com.demo.service;

import com.demo.Utils.Tools;
import com.demo.control.ControlMain;

import java.io.IOException;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String model;
        String subModel;
        String filePath;
        int num;
        int[] numArray;
        if(scan.hasNext()){
            model = scan.next();
                if(model.equals("-s")){
                    subModel = scan.next();
                    filePath = scan.next();
                    num = wc(model, subModel, filePath);
                    System.out.println(subModel+"结果:"+num);
                }
                else if(model.equals("-x")){
                    ControlMain.main(args); //调用图形界面
                }
                else{
                    filePath = scan.next();
                    numArray = wc(model, filePath);
                    if(model.equals("-a")){
                        System.out.println("代码行数："+numArray[0]);
                        System.out.println("注释行数："+numArray[1]);
                        System.out.println("空白行数："+numArray[2]);
                    }
                    else {
                        System.out.println(model+"结果："+numArray[0]);
                    }
                }
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
    private static int[] wc(String model, String filePath) throws IOException {
        int[] num = new int[3];
        if(model.equals("-a")){
            return ProWordCount.proCount(filePath);
        }
        byte[] fileByte = Tools.fileToByte(filePath);
        model = Tools.modelTransf(model);
        num[0] = CountImp.valueOf(model).op(fileByte);
        return num;
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
        return ProWordCount.batchCount(subModel, filePath);
    }

}