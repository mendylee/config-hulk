package com.xiangrikui.hulk.core.exception;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：自定义zookeeper序列化异常
 * 
 * @author jerry
 * @version 1.0
 */
public class ZkSerializerException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7855388498223354452L;

    public ZkSerializerException(){
        super();
    }
    
    public ZkSerializerException(String message){
        super(message);
    }
    
    public ZkSerializerException(String message,Throwable cause){
        super(message,cause);
    }
    
    public ZkSerializerException(Throwable cause){
        super(cause);
    }
}
