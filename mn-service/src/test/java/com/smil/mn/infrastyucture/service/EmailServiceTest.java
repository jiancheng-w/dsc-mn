package com.smil.mn.infrastyucture.service;

import com.smil.mn.api.dto.MailDto;
import com.smil.mn.infrastructure.service.EmailService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        MailDto mailDto = new MailDto();
        mailDto.setTitle("测试邮件发送");
        mailDto.setContent("邮件通知服务内容");
        mailDto.setMailTo("18814883049@163.com");
        //mailDto.setCc("764699493@qq.com");
        Boolean email = emailService.sendEmail(mailDto);
        System.out.println(email);
    }
}