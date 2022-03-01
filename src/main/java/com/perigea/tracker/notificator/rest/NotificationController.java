package com.perigea.tracker.notificator.rest;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perigea.tracker.commons.dto.ResponseDto;
import com.perigea.tracker.commons.model.Email;
import com.perigea.tracker.notificator.email.EmailSender;

@RestController
public class NotificationController {

	@Autowired
	private EmailSender emailClient;

	@Autowired
	private Logger logger;
	
	@Autowired
	private ThreadPoolExecutor threadExecutor;
	
	@PostMapping(path = "send_email")
	public ResponseEntity<ResponseDto<String>> sendEmail(@RequestBody Email email) {
		
		threadExecutor.execute(()->{
			logger.info(email.toString());
			emailClient.mandaNotifica(email);
		});
//		Thread controller = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				logger.info(email.toString());
//				emailClient.mandaNotifica(email);
//				try {
//					Thread.sleep(5000);
//					logger.info("Thread interrupted");
//				} catch (InterruptedException e) {
//					return;
//				}
//			}
//
//		});
//		controller.run();

		ResponseDto<String> genericDto = ResponseDto.<String>builder().data("Email mandata con successo").build();
		return ResponseEntity.ok(genericDto);

	}
}
