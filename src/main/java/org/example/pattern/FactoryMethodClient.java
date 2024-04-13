package org.example.pattern;
abstract class Message {
    String text;

    @Override
    public String toString() {
        return text;
    }
}

class WhatsAppMessage extends Message {
    public WhatsAppMessage(String text) {
        this.text = text;
    }
}
class Email extends Message {
    public Email(String text) {
        this.text = "Dear \n\t" + text + "\nThank & Regards";
    }
}
class MessageFactory {
    public static Message getMessage(String type) {
        if (type.equals("whatsAppMessage")) {
            return new WhatsAppMessage("Our Big sale started");
        } else if (type.equals("email")) {
            return new Email("Our Big sale started");
        } else {
            throw new IllegalArgumentException("Unknown message type: " + type);
        }
    }
}
public class FactoryMethodClient {
    public static void main(String[] args) {
        Message email = MessageFactory.getMessage("email");
        System.out.println(email);
    }
}
