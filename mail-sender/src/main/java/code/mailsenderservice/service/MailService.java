package code.mailsenderservice.service;

import code.mailsenderservice.utils.EmailRequest;

public interface MailService {
    void send(EmailRequest request);
}

