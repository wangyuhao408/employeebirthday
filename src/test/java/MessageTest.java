import org.junit.Assert;
import org.junit.Test;

public class MessageTest {
    Message message = new Message();

    @Test
    public void readTxtFileTest() {
        String result = message.readTxtFile("C:\\Users\\jumbo\\Desktop\\employee birthday\\src\\main\\resources\\employee_records.txt");
        System.out.println(result);
        Assert.assertNotNull(result);
    }
    @Test
    public void sendBirthGreetingTest() {
        message.sendBirthGreeting("Doe,john,1982/10/08,john.doe@foobar.com");
    }

}
