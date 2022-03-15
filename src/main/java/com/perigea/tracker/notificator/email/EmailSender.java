package com.perigea.tracker.notificator.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.perigea.tracker.commons.model.Email;
import com.perigea.tracker.notificator.generic.NotificaInterface;

@Service
public class EmailSender implements NotificaInterface<Email> {
	
	@Autowired
	private Logger logger;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MailUtils mailUtils;
	
	@Override
	public void sendNotification(Email email) {
		try {
			MimeMessage mimeMessage = mailUtils.builMessage(javaMailSender, email);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.error("error sending notification: " + e.getMessage());
		}
	}
	
}