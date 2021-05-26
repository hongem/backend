package com.nuce.service_gara.service.serviceImpl.ExportAndSendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendEmail {

    private JavaMailSender emailSender;

    @Autowired
    public SendEmail(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail() {

        String to = "hong90662@nuce.edu.vn";
        String subject = "email subject";
        String text = "email text";

        MimeMessage message = emailSender.createMimeMessage();
        FileSystemResource file = new FileSystemResource(new File("E:\\Report\\employees.xls"));

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("employees.xls", file);
//                message.saveChanges();
        } catch (Exception ex) {
            System.out.println("Error");
        }


        emailSender.send(message);
    }
}