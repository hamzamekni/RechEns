package com.example.resens.service;

import com.example.resens.model.Adress;
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
import java.util.List;
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


    public void sendToTeacher(String mail, String firstName, String email, String detailEnseigant,
                              Integer phoneNumber, String templateName,
                              String confirmTeacherUrl, Long teacherId, List<String> uploadedUrls, String deleteTeacherUrl)throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );
        Map<String, Object> properties = new HashMap<>();
        properties.put("teacherId",teacherId);
        properties.put("username", firstName);
        properties.put("email", email);
        properties.put("detailEnseigant", detailEnseigant);
        properties.put("phoneNumber", phoneNumber);
        properties.put("deleteTeacherUrl",deleteTeacherUrl);
        properties.put("confirmationUrl", confirmTeacherUrl);
        properties.put("uploadedUrls",uploadedUrls);
        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("choeurproject@gmail.com");
        helper.setTo(mail);
        helper.setSubject("Welcome to Find A Teacher App");

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }

    public void sendDemandeToTeacher(String mail, String firstName, String email, String etudiantMail,
                                     Integer phoneNumber, String s, Long teacherId) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );
        Map<String, Object> properties = new HashMap<>();
        properties.put("teacherId",teacherId);
        properties.put("etudiant_username", firstName);
        properties.put("email", email);
        properties.put("etudiant_email", etudiantMail);
        properties.put("etudiant_phoneNumber", phoneNumber);
        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("choeurproject@gmail.com");
        helper.setTo(mail);
        helper.setSubject("Welcome to Find A Teacher App");

        String template = templateEngine.process(s, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }

    public void send(String mail, String titreDemande, String detailDemande,
                     String firstName, String firstName1, String matiere, String adress, String templateName, String confirmationUrl) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );
        Map<String, Object> properties = new HashMap<>();
        properties.put("titreDemande", titreDemande);
        properties.put("detailDemande", detailDemande);
        properties.put("etudiantFirstName", firstName);
        properties.put("teacherFirstName", firstName1);
        properties.put("matiere", matiere);
        properties.put("adress", adress);
        properties.put("confirmationUrl", confirmationUrl);
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
