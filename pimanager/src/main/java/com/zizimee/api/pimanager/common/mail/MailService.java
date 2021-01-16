package com.zizimee.api.pimanager.common.mail;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    public final JavaMailSender mailSender;

    public void send(MimeMessage message) {
        mailSender.send(message);
    }

    public MimeMessage createSignUpMessage(Enterprise enterprise) throws MessagingException {
        String url = "<a href='http://localhost:8080/enterprise/verify-email-token?token=" + enterprise.getEmailVerifyingToken()
                + "&email=" + enterprise.getEmail() + "'> 인증</a>";

        MimeMessage mailMessage = mailSender.createMimeMessage();
        mailMessage.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(enterprise.getEmail())});
        mailMessage.setSubject("회원 가입 인증 이메일", "UTF-8");
        mailMessage.setText(url, "UTF-8", "html");

        return mailMessage;
    }
}
