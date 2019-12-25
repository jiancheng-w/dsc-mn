package com.smil.mn.infrastructure.service.impl;

import com.smil.mn.api.dto.MailDto;
import com.smil.mn.infrastructure.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * 发送邮件地址
     */
    @Value("${smil.mail.from}")
    private String FROM;

    /**
     * 发件人昵称
     */
    @Value("${smil.mail.nickname}")
    private String PERSONAL;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public Boolean sendEmail(MailDto mail) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(FROM,PERSONAL);
            //添加收件人
            if (StringUtils.isNotBlank(mail.getMailTo()) && mail.getMailTo().contains(";")){
                String[] mailTo = mail.getMailTo().split(";");
                mimeMessageHelper.setTo(mailTo);
            }else {
                mimeMessageHelper.setTo(mail.getMailTo());
            }
            //添加抄送人
            if (StringUtils.isNotBlank(mail.getCc()) && mail.getCc().contains(";")){
                String[] cc = mail.getCc().split(";");
                mimeMessageHelper.setCc(cc);
            }else if (StringUtils.isNotBlank(mail.getCc())){
                mimeMessageHelper.setCc(mail.getCc());
            }
            mimeMessageHelper.setSubject(mail.getTitle());
            mimeMessageHelper.setText(mail.getContent(), true);
            javaMailSender.send(mimeMailMessage);
            return Boolean.TRUE;
        } catch (Exception e) {
            logger.error("send email failed:{}", e.getMessage());
            return Boolean.FALSE;
        }
    }

}
