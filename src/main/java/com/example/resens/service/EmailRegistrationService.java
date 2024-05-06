package com.example.resens.service;

import com.example.resens.model.Matiere;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailRegistrationService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void send(
            String to,
            String username,
            String templateName,
            String confirmationUrl
    ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("choeurproject@gmail.com");
        helper.setTo(to);
        helper.setSubject("Welcome to Find A Teacher App");

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }


    public void sendToTeacher(String mail, String firstName, String email, String detailEnseigant, Integer phoneNumber, Set<Matiere> matieres, String templateName, String confirmTeacherUrl)throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", firstName);
        properties.put("email", email);
        properties.put("detailEnseigant", detailEnseigant);
        properties.put("phoneNumber", phoneNumber);
        properties.put("matieres", matieres);

        properties.put("confirmationUrl", confirmTeacherUrl);
        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("choeurproject@gmail.com");
        helper.setTo(mail);
        helper.setSubject("Welcome to Find A Teacher App");

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }
}
