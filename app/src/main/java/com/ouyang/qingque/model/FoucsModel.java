package com.ouyang.qingque.model;

import java.io.Serializable;

/**
 * Created by fengyun on 2018/3/27.
 */

public class FoucsModel implements Serializable {
    private int result;
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public DataList getData() {
        return data;
    }
    public void setData(DataList data) {
        this.data = data;
    }
    //模仿Data
    private DataList data;
}
