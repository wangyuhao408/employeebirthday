import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import sun.plugin2.message.transport.Transport;
import sun.reflect.annotation.ExceptionProxy;

import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;


public class Message {
    public static void sendBirthGreeting(String filePath) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder text = readTxtFile(filePath);
        Arrays.stream(text.toString().split("\r\n")).forEach( s -> {
            if (s.split(",")[2].equals(df.format(new Date()))) {
                try {
                    sendEMail(s.split(",")[0], s.split(",")[4]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void sendEMail(String recipient, String Email) throws Exception {
        String to = "abcd@gmail.com";
// 发件人电子邮箱
        String from = "web@gmail.com";
// 指定发送邮件的主机为 localhost
        String host = "localhost";
// 获取系统属性
        Properties properties = System.getProperties();
// 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
// 获取默认session对象
        Session session = Session.getDefaultInstance(properties);
// 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);
// Set From: 头部头字段
        message.setFrom(new InternetAddress(from));
// Set To: 头部头字段
        message.addRecipient(javax.mail.Message.RecipientType.TO, new  InternetAddress(to));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
// Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");
// 设置消息体
            message.setText("This is actual message");
// 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }


    public static StringBuilder readTxtFile(String filePath){
            StringBuilder text = new StringBuilder();
            try {
                String encoding = "GBK";
                File file = new File(filePath);
                if (file.isFile() && file.exists()) { //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(file), encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt;
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        text.append(lineTxt);
                    }
                    read.close();
                } else {
                    text.append("找不到指定文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
            return text;
        }
}
