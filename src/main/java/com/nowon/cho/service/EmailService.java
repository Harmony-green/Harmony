package com.nowon.cho.service;

import javax.mail.internet.MimeMessage;

public interface EmailService {

	MimeMessage createMail(String mail);
	
	int sendEmail(String userId);

}