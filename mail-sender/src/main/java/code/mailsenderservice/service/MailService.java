package code.mailsenderservice.service;

import code.mailsenderservice.utils.EmailRequest;

public interface MailService {
    void send(MailMessageRequest request);
    void greeting(EmailRequest request);
    void sendCodeToUpdatePassword(EmailRequest request);
    void sendActivationCode(EmailRequest request);
}

