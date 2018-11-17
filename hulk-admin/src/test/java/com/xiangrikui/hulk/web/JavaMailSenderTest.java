package com.xiangrikui.hulk.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.xiangrikui.hulk.web.service.MailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 创建时间：2017年5月26日
 * <p>修改时间：2017年5月26日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={HulkAdminApplication.class})
public class JavaMailSenderTest {
    
    @Autowired
    Configuration configuration; //freeMarker configuration
    
    @Autowired
    MailService mailService;
    
    @Test
    public void sendHtmlMailUsingFreeMarker() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("time", new Date());
        model.put("appName", "hulk-example");
        model.put("envName", "dev");
        model.put("version", "1.0.0");
        model.put("changeKey", "redis.url");
        model.put("beforeValue", "3000");
        model.put("afterValue", "5000");
        Template t = configuration.getTemplate("mail.ftl"); // freeMarker template
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        mailService.sendHtmlMail("zhubin@xiangrikui.com", "[配置中心]配置变更通知", content);
    }
    
    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mall@xiangrikui.com");
        message.setTo("zhubin@xiangrikui.com");
        message.setSubject("主题：[配置中心]配置变更通知");
        message.setText("<html><body></body></html>");
       // mailSender.send(message);
    }
}
