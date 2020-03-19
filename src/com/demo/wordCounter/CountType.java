package com.demo.wordCounter;

public enum CountType implements CountOperation {
    OP_C{
        @Override
        public int op(byte[] fileByte) {
            if(fileByte==null) return 0;    //文件为空
            String fileStr = new String(fileByte);
            String pattern = "\\S";
            return Tools.searchStr(pattern, fileStr);
        }
    },

    OP_W{
        @Override
        public int op(byte[] fileByte){
            if(fileByte==null) return 0;
            String fileStr = new String(fileByte);
            String REGEX = "[a-zA-Z]{2,}\\W";
            return Tools.searchStr(REGEX, fileStr);
        }
    },

    OP_L{
        @Override
        public int op(byte[] fileByte){
            if(fileByte==null) return 0;
            String REGEX = "\\n";
            String fileStr = new String(fileByte);
            return Tools.searchStr(REGEX, fileStr)+1;//最后一行通常无回车
        }
    };
}
