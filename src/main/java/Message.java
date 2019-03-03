import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import sun.plugin2.message.transport.Transport;

import javax.jms.Session;
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

            }
        });
    }
    public static void sendEMail(String sender, String recipient) {
        String to = "abcd@gmail.com";
// �����˵�������
        String from = "web@gmail.com";
// ָ�������ʼ�������Ϊ localhost
        String host = "localhost";
// ��ȡϵͳ����
        Properties properties = System.getProperties();
// �����ʼ�������
        properties.setProperty("mail.smtp.host", host);
// ��ȡĬ��session����
        Session session = Session.getDefaultInstance(properties);
        try{
// ����Ĭ�ϵ� MimeMessage ����
            MimeMessage message = new MimeMessage(session);
// Set From: ͷ��ͷ�ֶ�
            message.setFrom(new InternetAddress(from));
// Set To: ͷ��ͷ�ֶ�
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
// Set Subject: ͷ��ͷ�ֶ�
            message.setSubject("This is the Subject Line!");
// ������Ϣ��
            message.setText("This is actual message");
// ������Ϣ
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
    }


    public static StringBuilder readTxtFile(String filePath) {
        StringBuilder text = new StringBuilder();
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//���ǵ������ʽ
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while((lineTxt = bufferedReader.readLine()) != null){
                    text.append(lineTxt);
                }
                read.close();
            }else{
                text.append("�Ҳ���ָ���ļ�");
            }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }
        return text;
    }
}