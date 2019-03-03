import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class Message {
    public static void sendBirthGreeting(String filePath){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        String text = readTxtFile(filePath);
        System.out.println((text.split(",")[0]));
        System.out.println(text.split(",")[0].split(",")[2]);
        Arrays.stream(text.split("\r\n")).forEach( s -> {
            try {
                if (df.parse(s.split(",")[2]).equals(df.format(date))) {
                    try {
                        sendEMail(s.split(",")[0], s.split(",")[3]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private static void sendEMail(String recipient, String Email) throws Exception {
            // �ռ��˵�������
            String to = Email;

            // �����˵�������
            String from = "1197856100@qq.com";

            // ָ�������ʼ�������Ϊ localhost
            String host = "smtp.163.com";

            // ��ȡϵͳ����
            Properties properties = System.getProperties();

            // �����ʼ�������
            properties.setProperty("mail.smtp.host", host);

            properties.setProperty("mail.smtp.auth", "true");
            MyAuthenticator myauth = new MyAuthenticator(from, "���Լ�����������");

            // ��ȡĬ��session����
            Session session = Session.getDefaultInstance(properties, myauth);

            try{
                // ����Ĭ�ϵ� MimeMessage ����
                MimeMessage message = new MimeMessage(session);

                // Set From: ͷ��ͷ�ֶ�
                message.setFrom(new InternetAddress(from));

                // Set To: ͷ��ͷ�ֶ�
                message.addRecipient(javax.mail.Message.RecipientType.TO,
                        new InternetAddress(to));

                // Set Subject: ͷ��ͷ�ֶ�
                message.setSubject("happy birthday!");

                // ������Ϣ��
                message.setText("happy birthday, dear" + recipient);

                // ������Ϣ
                javax.mail.Transport.send(message);
                System.out.println("Sent message successfully....");
            }catch (MessagingException mex) {
                mex.printStackTrace();
            }
    }


    public static String readTxtFile(String filePath){
            StringBuilder text = new StringBuilder();
            try {
                String encoding = "GBK";
                File file = new File(filePath);
                if (file.isFile() && file.exists()) {
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(file), encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt;
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        text.append(lineTxt);
                    }
                    read.close();
                }    else {
                    text.append("�Ҳ���ָ���ļ�");
                }
            } catch (Exception e) {
                System.out.println("��ȡ�ļ����ݳ���");
                e.printStackTrace();
            }
            return text.toString();
        }
}
