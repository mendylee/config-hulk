package com.xiangrikui.hulk.core.exception;

/**
 * 创建时间：2017年4月11日
 * <p>修改时间：2017年4月11日
 * <p>类说明：Hulk配置中心异常类
 * 
 * @author jerry
 * @version 1.0
 */
public class HulkException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = -736464596052859412L;

    public HulkException(){
        super();
    }
    
    public HulkException(String message){
        super(message);
    }
    
    public HulkException(String message,Throwable cause){
        super(message,cause);
    }
    
    public HulkException(Throwable cause){
        super(cause);
    }
}
