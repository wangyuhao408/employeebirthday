import org.junit.Assert;
import org.junit.Test;

public class MessageTest {
    @Test
    public void readTxtFileTest() {
        Message message = new Message();
        String result = message.readTxtFile("../employee_records.txt");
        System.out.println(result);
        Assert.assertNotNull(result);
    }
}
