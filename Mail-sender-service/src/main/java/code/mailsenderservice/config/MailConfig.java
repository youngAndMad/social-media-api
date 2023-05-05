package code.mailsenderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private Integer port;

    @Value("${password}")
    private String password;

    @Value("${protocol}")
    private String protocol;

    @Value("${protocolType}")
    private String protocolType;

    @Value("${mailDebug}")
    private String mailDebug;

    @Value("${danekerscode}")
    private String danekerscode;

    @Value("${enable}")
    private String enable;

    @Bean
    public JavaMailSender getMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername("kkraken2005@gmail.com");
        mailSender.setPassword(this.password);

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty(this.protocol, protocolType);
        properties.setProperty(mailDebug, danekerscode);
        properties.put(enable, danekerscode);

        return mailSender;
    }

}