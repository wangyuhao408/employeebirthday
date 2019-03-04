import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Message message = new Message();
        MailService mailService = new MailService();
        String text = message.readTxtFile("C:\\Users\\jumbo\\Desktop\\employee birthday\\src\\main\\resources\\employee_records.txt");
        Arrays.stream(text.split("\n")).forEach(s -> {
            if (s.split(",")[2].split("/")[1].equals(simpleDateFormat.format(date).split("-")[0])) {
                try {
                    mailService.sendMail(s.split(",")[0], s.split(",")[3]);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

