package com.xiangrikui.hulk.web.common;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.xiangrikui.hulk.web.service.MailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 创建时间：2017年5月27日
 * <p>修改时间：2017年5月27日
 * <p>类说明：邮件发送Helper工具类
 * 
 * @author jerry
 * @version 1.0
 */
@Service
public class MailHelper {


    @Autowired
    private MailService mailService;
    
    @Autowired
    Configuration configuration;
    
    /**
     * 发送邮件线程
     */
    private Executor executor = Executors.newFixedThreadPool(5);
    
    
    /**
     * 异步发送配置更新邮件
     * @param to    收件人
     * @param data  邮件内容
     * @throws Exception
     */
    public void asyncSendMail(String to,Map<String, Object> data)throws Exception{
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    sendConfigUpdateMail(to,data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    
    public void  sendConfigUpdateMail(String to,Map<String, Object> data) throws Exception{
        
        String subject = "配置变更通知";
        
        Template t = configuration.getTemplate("mail.ftl"); // freeMarker template
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, data);

        mailService.sendHtmlMail(to, subject, content);
        
    }
    
}
