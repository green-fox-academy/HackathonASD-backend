package com.greenfox.hackathon.service;

import com.greenfox.hackathon.exception.MailException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@AllArgsConstructor
@Slf4j
public class MailService {

  private final JavaMailSender mailSender;

  private final TemplateEngine templateEngine;

  private String build(String url) {
    Context context = new Context();
    context.setVariable("activationUrl", url);
    return templateEngine.process("mailTemplate", context);
  }

  @Async
 public void sendActivationMail(String email, String url) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setTo(email);
      messageHelper.setSubject("Webshop Account Activation");
      messageHelper.setText(build(url), true);
    };
    try {
      mailSender.send(messagePreparator);
      log.info("Activation email sent!!");
    } catch (org.springframework.mail.MailException e) {
      log.error("Exception occurred when sending mail", e);
      throw new MailException("Exception occurred when sending mail to " + email, e);
    }
  }

}

