package com.demo.control;

import com.demo.service.ProWordCount;
import com.demo.service.WordCount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controller {
    public Button Bt_1;
    @FXML
    public Label runtime_name;
    @FXML
    public Label runtime;
    @FXML
    public Label codeNum;
    @FXML
    public Label noteNum;
    @FXML
    public Label blankNum;
    @FXML
    private Label charNum;
    @FXML
    private Label wordNum;
    @FXML
    private Label lineNum;
    @FXML
    private Label warn;

    @FXML
    public void open(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("请选择文件，而非目录");
        File file = fileChooser.showOpenDialog(null);
        long start = System.currentTimeMillis();
        if(file==null){
            warn.setText("你没有打开任何文件噢~");
            return;
        }else{

            int C = WordCount.wc("-s", "-c", file.getAbsolutePath());
            int W = WordCount.wc("-s", "-w", file.getAbsolutePath());
            int L = WordCount.wc("-s", "-l", file.getAbsolutePath());
            int[] num = ProWordCount.proCount(file.getAbsolutePath());
            charNum.setText(String.valueOf(C));
            wordNum.setText(String.valueOf(W));
            lineNum.setText(String.valueOf(L));
            codeNum.setText(String.valueOf(num[0]));
            noteNum.setText(String.valueOf(num[1]));
            blankNum.setText(String.valueOf(num[2]));
        }
        long end = System.currentTimeMillis();
        long time = end-start;
        runtime.setText(String.valueOf(time)+"ms");
    }
}
