package util;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;
import java.util.ResourceBundle;

public class EmailHelper {

//    private static final ResourceBundle bundle = ResourceBundle.getBundle("mail");
//    private static final String sendFrom = bundle.getString("email.from");
//    private static final String username = bundle.getString("username");
//    private static final String password = bundle.getString("password");
//    private static final String host = bundle.getString("email.host");


    @Test
    public  void  tsetemail()throws Exception{
        String content ="Hi,There! This is your CheckCode just for our register! Please DONT tell anyone!" +
                "CheckCode : "+ UUIDUtils.getCode();



        //参数分别为接收者邮箱、title、内容body
        EmailHelper.sendEmail("994343302@qq.com", "Intelligent Cultivation", content);
    }



    public static void sendEmail(String someone, String subject, String content){
        content ="Hi,There! This is your CheckCode just for our register! Please DONT tell anyone!" +
                "CheckCode : "+ content;


        Properties props = new Properties();
        props.setProperty("mail.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1158414287@qq.com","audbfoprjapzhjfi");
            }
        };
        Session session = Session.getDefaultInstance(props, authenticator);
        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("1158414287@qq.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(someone));
            //message.setRecipients(RecipientType.TO,InternetAddress.parse("测试的接收的邮件多个以逗号隔开"));
            try {
                message.setSubject(subject);
                message.setContent(content,"text/html;charset=utf-8");
                Transport.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
