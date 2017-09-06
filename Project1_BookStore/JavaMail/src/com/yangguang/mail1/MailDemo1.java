package com.yangguang.mail1;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailDemo1 {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();//key value:配置参数。真正发送邮件时再配送
		
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.debug", "true");
		
		Session session = Session.getInstance(props);//发送邮件时使用的换件配置
		MimeMessage message = new MimeMessage(session);//创建一个邮件Message对象
		
		//设置邮件的头部
		InternetAddress address = new InternetAddress("songyangguang@163.com");
		message.setFrom(address);
		message.setRecipients(Message.RecipientType.TO,"372752655@qq.com");
		message.setSubject("This is the second message");
		
		//设置正文
		message.setContent("<h1>Hello<h1>", "text/html");
		
		//设置文本部分
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("aaa<img src='cid:im'/>aaa","text/html");
		
		//设置图片部分
		MimeBodyPart imagePart = new MimeBodyPart();
		imagePart.setContentID("im");
		
		//把磁盘上的图片加到part中：这里使用到了JAF框架
		DataHandler dHandler = new DataHandler(new FileDataSource("src/1.jpg"));			
		imagePart.setDataHandler(dHandler);
		
		//将每个part部分结合起来，组合成MultiPart->Message
		MimeMultipart mimeMultipart  = new MimeMultipart();
		mimeMultipart.addBodyPart(textPart);
		mimeMultipart.addBodyPart(imagePart);
		mimeMultipart.setSubType("related");//有关系的
		
		message.setContent(mimeMultipart);
		message.saveChanges();
		//发送邮件
		Transport transport = session.getTransport();
		transport.connect("songyangguang", "syg668899");
		transport.sendMessage(message, message.getAllRecipients());
		
		//保存邮件
		//message.writeTo(new FileOutputStream("d:/1.eml"));
	}

}
