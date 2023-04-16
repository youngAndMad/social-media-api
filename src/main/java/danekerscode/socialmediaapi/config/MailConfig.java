package danekerscode.socialmediaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("kkraken2005@gmail.com");
        mailSender.setPassword("jnscksmpwoimkstl");

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.debug", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
