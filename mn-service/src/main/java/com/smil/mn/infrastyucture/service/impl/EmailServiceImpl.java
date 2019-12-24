package com.smil.mn.infrastyucture.service.impl;

import com.smil.mn.api.dto.MailDto;
import com.smil.mn.infrastyucture.service.EmailService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * 发送方电子邮件地址.
     */
    @Value("${smil.mail.from}")
    private String from;

    /**
     * 发送的主机地址（需要开通pop3服务）.
     */
    @Value("${smil.mail.host}")
    private String host;

    /**
     * 发送的端口号.
     */
    @Value("${smil.mail.port}")
    private Integer port;

    /**
     * 发送方电子邮件密码.
     */
    @Value("${smil.mail.password}")
    private String password;

    /**
     * 发送方昵称.
     */
    @Value("${smil.mail.nickname}")
    private String nickname;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean sendHtmlEmail(MailDto mail) {
        String content = null;
        // 如果有占位符则进行替换
        if (MapUtils.isNotEmpty(mail.getPlaceholder())) {
            StringSubstitutor stringSubstitutor = new StringSubstitutor(mail.getPlaceholder());
            content = stringSubstitutor.replace(mail.getContent());
        } else {
            content = mail.getContent();
        }

        HtmlEmail email = new HtmlEmail();
        try {
            email.setCharset("UTF-8");
            email.setStartTLSEnabled(Boolean.TRUE);
            email.setHostName(host);
            for (String str : mail.getMailToList()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, password);
            email.setSubject(mail.getTitle());
            email.setMsg(content);
            email.send();
            LOG.info("{} 发送邮件至 {}, 邮件内容:{}{}", from, StringUtils.join(mail.getMailToList(), ","), System.lineSeparator(), content);
            return true;
        } catch (EmailException e) {
            LOG.error(from + "发送邮件到" + StringUtils.join(mail.getMailToList(), ",") + "失败", e);
            return false;
        }
    }

}
