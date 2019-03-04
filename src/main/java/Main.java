public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        String text = message.readTxtFile("C:\\Users\\jumbo\\Desktop\\employee birthday\\src\\main\\resources\\employee_records.txt");
        message.sendBirthGreeting(text);
    }
}

