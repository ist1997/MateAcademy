package HomeWork.hw9.Mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MailService<T> implements Consumer<Sendable<T>> {

    private Map<String, List<T>> mailBox;

    public MailService() {
        mailBox = new HashMap<>();
    }

    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }

    @Override
    public void accept(Sendable<T> sendable) {
        List<T> list = mailBox.get(sendable.getTo());
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(sendable.getContent());
        mailBox.put(sendable.getTo(), list);
    }
}
