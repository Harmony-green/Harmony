//package com.nowon.cho.email;
//
//import java.util.Properties;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class EmailConfig {
//
//	@Bean
//    public JavaMailSender javaMailService() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//
//        javaMailSender.setHost("smtp.gmail.com"); // 메인 도메인 서버 주소 => 정확히는 smtp 서버 주소
//        javaMailSender.setUsername("scn0707074"); // 네이버 아이디
//        javaMailSender.setPassword("whdudwls0012@"); // 네이버 비밀번호
//
//        javaMailSender.setPort(587); // 메일 인증서버 포트
//
//        javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보 가져오기
//
//        return javaMailSender;
//    }
//    
//    private Properties getMailProperties() {
//        Properties properties = new Properties();
//        properties.put("mail.transport.protocol", "smtp");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.debug", "true");
//        return properties;
//    }
//}
