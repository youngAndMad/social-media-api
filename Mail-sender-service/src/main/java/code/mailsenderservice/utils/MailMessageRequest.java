package code.mailsenderservice.utils;

public record MailMessageRequest(String to, String subject, String message) {
}
