package com.demo.control;

import com.demo.wordCounter.WordCount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;

public class Controller {
    public Button Bt_1;
    @FXML
    private Label charN;
    @FXML
    private Label wordN;
    @FXML
    private Label lineN;
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
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("打开源文件");
        File file = directoryChooser.showDialog(null);
        if(file==null){
            warn.setText("你没有打开任何文件噢~");
            return;
        }else{
            int C = WordCount.wc("-s", "-c", file.getAbsolutePath());
            int W = WordCount.wc("-s", "-w", file.getAbsolutePath());
            int L = WordCount.wc("-s", "-l", file.getAbsolutePath());
            charNum.setText(String.valueOf(C));
            wordNum.setText(String.valueOf(W));
            lineNum.setText(String.valueOf(L));
        }
    }
}
