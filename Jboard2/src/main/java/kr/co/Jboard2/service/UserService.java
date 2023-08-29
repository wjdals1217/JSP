package kr.co.Jboard2.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.dao.UserDAO;
import kr.co.Jboard2.dto.UserDTO;

public class UserService {
	
	private static UserService instance = new UserService();
	
	public static UserService getInstance() {
		return instance;
	}
	private UserService() {}
	
	private static String generatedCode;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserDAO dao = UserDAO.getInstance();
	
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}

	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	
	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}
	
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
	
	public int sendCodeByEmail(String receiver) {
		// 인증 코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		generatedCode = ""+code;
		logger.info("code 생성");
		
		// 기본정보
		String sender = "wjdals1217@gmail.com";
		String password = "obnnoffaqsmnjdnj";
		String title = "Jboard2 인증코드 입니다";
		String content ="<h1>인증코드는 "+generatedCode+"</h1>";
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		logger.info("Gmail 서버 설정");
		
		// Gmail STMP 세션 생성
		Session gmailSession = Session.getInstance(props, new Authenticator(){	
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		logger.info("세션 생성");
		
		// 메일 발송
		
		int status = 0;
		Message message = new MimeMessage(gmailSession);
		
		try{
			message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
			status = 1;
			logger.info("메일 발송");
		}catch(Exception e){
			status = 0;
			logger.error("sendCodeByEmail error : "+e.getMessage());
		}
		return status;
	} // sendCodeByEmail end
	
	public int confirmCodeByEmail(String code) {
		logger.info("return....1");
		if(code.equals(generatedCode)) {
			return 1;
		}else {
			logger.info("return....2");
			return 0;
		}
	}
}