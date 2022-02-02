package com.perigea.tracker.notificator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perigea.tracker.commons.model.Email;
import com.perigea.tracker.notificator.email.EmailSender;

@RestController
public class NotificationController {
	
	@Autowired
	private EmailSender sender;
	
	@PostMapping(path = "send_email") 
	public ResponseEntity<String> sendEmail(@RequestBody Email email) {
		sender.sendEmail(email);
		return new ResponseEntity<>("Email mandata", HttpStatus.OK);
	}
}
