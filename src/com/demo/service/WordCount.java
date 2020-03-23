package com.demo.service;

import com.demo.Utils.FileUtil;
import com.demo.control.ControlMain;

import java.io.IOException;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);//用户键入
        String model;
        String subModel;
        String filePath;
        int[] numArray; //用于存储返回的多个参数
        if(scan.hasNext()){
            model = scan.next();
                if(model.equals("-s")){//如果模式为-s
                    subModel = scan.next();
                    filePath = scan.next();
                    wc(model, subModel, filePath);
                }
                else if(model.equals("-x")){//如果模式为-x
                    ControlMain.main(args); //调用图形界面
                }
                else if(model.equals("-w") || model.equals("-c") || model.equals("-l") || model.equals("-a")){
                    filePath = scan.next();
                    numArray = wc(model, filePath);
                    if(model.equals("-a")){//如果模式为-a
                        System.out.println("代码行数："+numArray[0]);
                        System.out.println("注释行数："+numArray[1]);
                        System.out.println("空白行数："+numArray[2]);
                    }
                    else {
                        System.out.println(model+"模式结果为："+numArray[0]);
                    }
                }
                else {
                    System.out.println("参数输入有误，可能输入了不存在的功能");
                }
        }else{
            System.out.println("您未输入任何字符");
        }
    }

    /**
     *单文件处理调用
     * @param model 处理模式：-c，-w，-l，-a
     * @param filePath 文件绝对路径
     * @return 字符/单词/行数，如果为-a模式则返回 代码行数+注释行数+空白行数
     * @throws IOException 文件不存在
     */
    public static int[] wc(String model, String filePath) throws IOException {
        int[] num = new int[3];
        if(model.equals("-a")){
            return ProWordCount.proCount(filePath);
        }
        byte[] fileByte = FileUtil.fileToByte(filePath);
        model = FileUtil.modelTrans(model);
        num[0] = CountImp.valueOf(model).op(fileByte);
        return num;
    }

    /**
     * 批量文件处理调用
     * @param model 批量处理模式：-s
     * @param subModel 选择子模式：-c，-w，-l
     * @param filePath 文件路径
     * @return 字符/单词/行数
     * @throws IOException 文件不存在
     */
     public static void wc(String model, String subModel, String filePath) throws IOException {
        subModel = FileUtil.modelTrans(subModel);
        ProWordCount.batchProCount(subModel, filePath);
    }

}