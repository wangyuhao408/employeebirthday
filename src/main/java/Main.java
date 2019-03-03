public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        String text = message.readTxtFile("../employee_records.txt");
        message.sendBirthGreeting(text);
    }
}

