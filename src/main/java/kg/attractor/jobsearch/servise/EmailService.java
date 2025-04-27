package kg.attractor.jobsearch.servise;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendEmail(String to, String code) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(emailFrom, "Job Search");
            helper.setTo(to);

            String subject = "Job Search reset password code";
            String content = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body>\n" +
                    "<p>Здравствуйте,</p>\n" +
                    "\n" +
                    "<p>Вы запросили сброс пароля на сайте JobSearch. (:DDDD)</p>\n" +
                    "\n" +
                    "<p>Ваш код для сброса пароля: <strong>" + code + "</strong></p>\n" +
                    "\n" +
                    "<p>Если вы не запрашивали сброс, просто проигнорируйте это письмо.</p>\n" +
                    "\n" +
                    "<p>С уважением,<br>Команда JobSearch(:D)</p>\n" +
                    "</body>\n" +
                    "</html>";

            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
