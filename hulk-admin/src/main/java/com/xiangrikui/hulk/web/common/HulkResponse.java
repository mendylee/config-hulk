package com.xiangrikui.hulk.web.common;

import java.util.List;

/**
 * 创建时间：2017年5月4日
 * <p>修改时间：2017年5月4日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkResponse<T> {

    
    private int pageIndex;
    
    private boolean rel;
    
    private String msg;
    
    private List<T> result;
    
    private long count;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    
    
    
}
