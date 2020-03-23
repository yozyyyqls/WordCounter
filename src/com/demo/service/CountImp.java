package com.demo.service;

import com.demo.Utils.FileUtil;

public enum CountImp implements Count {
    OP_C{
        @Override
        public int op(byte[] fileByte) {
            if(fileByte==null) return 0;    //文件为空
            String fileStr = new String(fileByte);
            String pattern = "\\S";
            return FileUtil.searchStr(pattern, fileStr);
        }
    },

    OP_W{
        @Override
        public int op(byte[] fileByte){
            if(fileByte==null) return 0;
            String fileStr = new String(fileByte);
            String REGEX = "[a-zA-Z]+\\W";
            return FileUtil.searchStr(REGEX, fileStr);
        }
    },

    OP_L{
        @Override
        public int op(byte[] fileByte){
            if(fileByte==null) return 0;
            String REGEX = "\\n";
            String fileStr = new String(fileByte);
            return FileUtil.searchStr(REGEX, fileStr)+1;//最后一行通常无回车
        }
    };
}
