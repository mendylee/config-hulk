package com.xiangrikui.hulk.web.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建时间：2017年6月1日
 * <p>修改时间：2017年6月1日
 * <p>类说明：全局异常处理类
 * 
 * @author jerry
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    public static final String DEFAULT_ERROR_VIEW = "error";
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
    
}
