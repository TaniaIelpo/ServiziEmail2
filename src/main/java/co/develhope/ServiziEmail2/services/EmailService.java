package co.develhope.ServiziEmail2.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Tania Ielpo
 */

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    public void sendTo(String email, String title, String text) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        if (text==null) text=""; //you could also choose not to send the mail
        try{
            helper.setTo(email);
            helper.setSubject(title);
            String message="<h1>This is the message</h1>"+
                    "<h2>This is an image</h2>"+
                    "<img src='https://www.start2impact.it/wp-content/uploads/2020/10/programmazione-front-end-1024x683.jpg'>"+
                    "<h3>"+text+"</h3>";
            helper.setText(message,true);

        }catch (MessagingException me){
            me.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
