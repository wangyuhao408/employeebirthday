import com.sun.mail.util.MailSSLSocketFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.swing.text.html.HTML.Attribute.CONTENT;
import static sun.security.x509.X509CertInfo.SUBJECT;


public class Message {
    public static void sendBirthGreeting(String text){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        Arrays.stream(text.split("\r\n")).forEach( s -> {
                if (s.split(",")[2].split("/")[1].equals(df.format(date).split("-"))) {
                        sendEMail(s.split(",")[0], s.split(",")[3]);
                }
        });
    }

    private static void sendEMail(String recipient, String Email){
            String host = "smtp.qq.com";
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getDefaultInstance(properties,new Authenticator(){
                public PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("1197856100@qq.com", "obipoqwiggvygffc"); //发件人邮件用户名、密码
                }
            });

            try{
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("1197856100@qq.com"));
                message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(Email));
                message.setSubject("happy birthday");
                message.setText("happy birthday, Dear" + recipient);

                // 发送消息
                Transport.send(message);
                System.out.println("发送短信成功");
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
                    text.append("找不到指定文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
            return text.toString();
        }
}
