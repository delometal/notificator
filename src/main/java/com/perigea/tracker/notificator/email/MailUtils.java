package com.perigea.tracker.notificator.email;

import java.io.StringWriter;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.perigea.tracker.commons.dto.AttachmentDto;
import com.perigea.tracker.commons.enums.EmailType;
import com.perigea.tracker.commons.exception.MailException;
import com.perigea.tracker.commons.model.Email;
import com.perigea.tracker.commons.utils.Utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@Component
public class MailUtils {
	
	private static final String ENCONDING = "UTF-8";
    
	public MimeMessage builMessage(JavaMailSender javaMailSender, Email mail) {
		try {
			MimeMessage mailMessage = javaMailSender.createMimeMessage();
			mailMessage.setFrom(new InternetAddress(mail.getFrom()));
			mailMessage.setSubject(mail.getSubject());
			
			// ADD TO EMAIL MESSAGES
			addRecipient(mailMessage, Message.RecipientType.TO, mail.getTo());
			
			// ADD CC EMAIL MESSAGES
			addRecipient(mailMessage, Message.RecipientType.CC, mail.getCc());
			
			// ADD BCC EMAIL MESSAGES
			addRecipient(mailMessage, Message.RecipientType.BCC, mail.getCc());
			
			//ADD REPLY TO
			if(!Utils.isEmpty(mail.getReplayTo())){
				mailMessage.setReplyTo(InternetAddress.parse(mail.getReplayTo(), false));				
			}
			
			//ADD MESSAGE CONTENT
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			addContent(mail, mimeBodyPart);
			
			//ADD MESSAGE TO MULTIPART
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			
			//ADD ATTACHMENTS IF NEEDED
			if(!Utils.isEmpty(mail.getAttachments())) {
				
				for(AttachmentDto attch : mail.getAttachments()) {
					MimeBodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new ByteArrayDataSource(attch.getBArray(), attch.getMIMEType());
					attachmentBodyPart.setDataHandler(new DataHandler(source)); 
					attachmentBodyPart.setFileName(attch.getFilename());
					multipart.addBodyPart(attachmentBodyPart);
					
				}
				
			}
			
			mailMessage.setContent(multipart);
			return mailMessage;
		} catch(Exception e) {
			throw new MailException("MAIL ERROR: "+e.getMessage());
		}
	}

	private void addContent(Email mail, MimeBodyPart mimeBodyPart) throws Exception {
		if(mail.getEmailType() == EmailType.HTML_TEMPLATE_MAIL && Utils.isEmpty(mail.getTemplateName())) {
			throw new Exception("The specified mail was a TEMPLATE MAIL, but no template has been provided");
		} else if(mail.getEmailType() == EmailType.HTML_TEMPLATE_MAIL && !Utils.isEmpty(mail.getTemplateName()) && Utils.isEmpty(mail.getTemplateModel())) {
			throw new Exception("The specified mail was a TEMPLATE MAIL, and a template has been provided, but the template model was empty");
		} else if(mail.getEmailType() == EmailType.TEXT_HTML && Utils.isEmpty(mail.getContent())) {
			throw new Exception("The specified mail was a TEXT_HTML MAIL, but no content has been provided");
		} else if(mail.getEmailType() == EmailType.TEXT_PLAIN && Utils.isEmpty(mail.getContent())) {
			throw new Exception("The specified mail was a TEXT_PLAIN MAIL, but no content has been provided");
		} else if(mail.getEmailType() == EmailType.HTML_TEMPLATE_MAIL && !Utils.isEmpty(mail.getTemplateName()) && !Utils.isEmpty(mail.getTemplateModel())) {
            Configuration configuration = getConfiguration();
			Template template = configuration.getTemplate(mail.getTemplateName());

            //Pass custom param values
            StringWriter out = new StringWriter();
            template.process(mail.getTemplateModel(), out);
            mimeBodyPart.setContent(out.toString(), mail.getEmailType().getContentType());
		} else {
			mimeBodyPart.setContent(mail.getContent(), mail.getEmailType().getContentType());
		}
	}
	
	private void addRecipient(Message mailMessage, Message.RecipientType recipientType, List<String> mails) {
		try {
			Address[] addresses = null;
			if(!Utils.isEmpty(mails)) {
				addresses = new InternetAddress[mails.size()];
				int index = 0;
				for(String to : mails) {
					addresses[index] = new InternetAddress(to);
					++index;
				}
				mailMessage.setRecipients(recipientType, addresses);
			} else {
				if(Message.RecipientType.TO == recipientType) {
					throw new MailException("No TO recipient defined");
				}
			}
		} catch(Exception e) {
			throw new MailException("MAIL ERROR: "+e.getMessage());
		}
	}
	
	private Configuration getConfiguration() {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
		configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setDefaultEncoding(ENCONDING);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
	}
	
}