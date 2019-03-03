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
            // 收件人电子邮箱
            String to = Email;

            // 发件人电子邮箱
            String from = "1197856100@qq.com";

            // 指定发送邮件的主机为 localhost
            String host = "smtp.163.com";

            // 获取系统属性
            Properties properties = System.getProperties();

            // 设置邮件服务器
            properties.setProperty("mail.smtp.host", host);

            properties.setProperty("mail.smtp.auth", "true");
            MyAuthenticator myauth = new MyAuthenticator(from, "你自己的信箱密码");

            // 获取默认session对象
            Session session = Session.getDefaultInstance(properties, myauth);

            try{
                // 创建默认的 MimeMessage 对象
                MimeMessage message = new MimeMessage(session);

                // Set From: 头部头字段
                message.setFrom(new InternetAddress(from));

                // Set To: 头部头字段
                message.addRecipient(javax.mail.Message.RecipientType.TO,
                        new InternetAddress(to));

                // Set Subject: 头部头字段
                message.setSubject("happy birthday!");

                // 设置消息体
                message.setText("happy birthday, dear" + recipient);

                // 发送消息
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
                    text.append("找不到指定文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
            return text.toString();
        }
}
