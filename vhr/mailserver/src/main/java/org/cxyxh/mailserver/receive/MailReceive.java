package org.cxyxh.mailserver.receive;

import org.cxyxh.vhr.model.Employee;
import org.cxyxh.vhr.model.Hr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author ： cxyxh
 * @date : 2022/9/17 23:26
 * @describetion :
 */
@Component
public class MailReceive {

	public static final Logger logger = LoggerFactory.getLogger(MailReceive.class);

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	MailProperties mailProperties;

	@Autowired
	TemplateEngine templateEngine;

	@RabbitListener(queues = "javaboy.mail.welcome")
	public void handler(Employee employee){
		// 收到消息，发送邮件
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);

		try {
			helper.setTo(employee.getEmail());
			helper.setFrom(mailProperties.getUsername());
			helper.setSubject("入职欢迎");
			helper.setSentDate(new Date());
			Context context = new Context();
			context.setVariable("name",employee.getName());
			context.setVariable("posName",employee.getPosition().getName());
			context.setVariable("joblevelName", employee.getJobLevel().getName());
			context.setVariable("departmentName", employee.getDepartment().getName());
			String mail = templateEngine.process("mail", context);
			helper.setText(mail,true);

			mailSender.send(msg);
			logger.info("邮件发送成功");
			//logger.info(msgId + ":邮件发送成功");
		} catch (MessagingException e) {
			e.printStackTrace();
			logger.error("邮件发送失败：" + e.getMessage());
		}
	}

}
