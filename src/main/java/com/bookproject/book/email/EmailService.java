package com.bookproject.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.*;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    // don't make user to wait and used @Async and
    // need to enable @EnableAsync in main
    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException {
        String templateName;
        if(emailTemplate==null){
            templateName = "confirm-email";
        }else{
            templateName = emailTemplate.name();
        }

        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                UTF_8.name()
        );
        Map<String, Object> properties=new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activationCode", activationCode);

        Context context=new Context();
        context.setVariables(properties);
        helper.setFrom("test@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String template=templateEngine.process(templateName, context);  // it will point to templates/activate_account.html
        helper.setText(template, true);
        mailSender.send(mimeMessage);
    }
}
