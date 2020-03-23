package com.demo.control;

import com.demo.Utils.OpenUtil;
import com.demo.Utils.TimeUtil;
import com.demo.service.ProWordCount;
import com.demo.service.WordCount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

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
    public Label path;
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
        File file = OpenUtil.chooseFile();
        long start = System.currentTimeMillis();
        if(file==null){
            warn.setText("你没有打开任何文件噢~");
            return;
        }else{
            path.setText(file.getAbsolutePath());
            int[] num;

            num = WordCount.wc("-c", file.getAbsolutePath());
            charNum.setText(String.valueOf(num[0]));
            num = WordCount.wc("-w", file.getAbsolutePath());
            wordNum.setText(String.valueOf(num[0]));
            num = WordCount.wc("-l", file.getAbsolutePath());
            lineNum.setText(String.valueOf(num[0]));

            num = ProWordCount.proCount(file.getAbsolutePath());
            codeNum.setText(String.valueOf(num[0]));
            noteNum.setText(String.valueOf(num[1]));
            blankNum.setText(String.valueOf(num[2]));
        }
        long runTime = TimeUtil.getRunTime(start);
        runtime.setText(runTime +"ms");
    }
}
