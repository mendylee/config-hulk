package com.xiangrikui.hulk.core.exception;

/**
 * 创建时间：2017年3月7日
 * <p>修改时间：2017年3月7日
 * <p>类说明：自定义zookeeper异常实现类
 * 
 * @author jerry
 * @version 1.0
 */
public class ZkException extends RuntimeException {

    public ZkException(){
        super();
    }
    
    public ZkException(String message){
        super(message);
    }
    
    public ZkException(String message,Throwable cause){
        super(message,cause);
    }
    
    public ZkException(Throwable cause){
        super(cause);
    }
}
