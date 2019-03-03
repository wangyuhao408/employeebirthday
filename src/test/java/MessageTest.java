import org.junit.Assert;
import org.junit.Test;

public class MessageTest {
    @Test
    public void readTxtFileTest() {
        Message message = new Message();
        StringBuilder result = message.readTxtFile("../employee_records.txt");
        Assert.assertNotNull(result);
    }
}
