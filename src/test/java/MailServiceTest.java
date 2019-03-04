import org.junit.Test;

import java.security.GeneralSecurityException;

public class MailServiceTest {
    @Test
    public void sendMailTest() throws GeneralSecurityException {
        MailService mailService = new MailService();
        mailService.sendMail("1197856100@qq.com", "wyh");
    }
}
