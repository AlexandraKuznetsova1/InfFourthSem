package com.example.infsecondsemsemesterwork.util;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class EmailUtil {
	private final JavaMailSender mailSender;

	private final Configuration freemarkerConfiguration;

	@Value("${spring.mail.username}")
	private String from;

	public void sendMail(String to, String subject, String templateName, Map<String, String> data) {
		Template emailTemplate;
		String mailText;
		try {
			emailTemplate = freemarkerConfiguration.getTemplate(templateName);
			mailText = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, data);
		} catch (IOException | TemplateException e) {
			throw new IllegalArgumentException(e);
		}

		String finalMailText = mailText;
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setSubject(subject);
			messageHelper.setText(Objects.requireNonNull(finalMailText), true);
			messageHelper.setTo(to);
			messageHelper.setFrom(from);
			messageHelper.addInline("logo.png", new ClassPathResource("static/SignUpWelcome.jpg"));
		};

		new Thread(() -> mailSender.send(preparator)).start();
	}
}
