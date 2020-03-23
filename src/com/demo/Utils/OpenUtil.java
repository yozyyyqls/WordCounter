package com.demo.Utils;

import javafx.stage.FileChooser;

import java.io.File;

public class OpenUtil {
    public static File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("请选择文件，而非目录");
        File file = fileChooser.showOpenDialog(null);
        return file;
    }
}
