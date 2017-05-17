package pod.questao_05;

import java.util.HashMap;
import pod.questao_05.interfaces.Subscriber;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/05/2017, 15:43:44
 */
public class OnlineNotifications {

    private HashMap<String, Subscriber[]> notifications;

    public OnlineNotifications() {
        notifications = new HashMap<>();
    }

    public void store(String from, Subscriber[] subscribers) {
        notifications.put(from, subscribers);
    }

    public Subscriber[] listSubsFromUUID(String from) {
        Subscriber[] get = notifications.get(from);
        if (get == null) {
            get = new Subscriber[0];
        }
        return get;
    }

    public void removeNotifications(String uuid) {
        notifications.remove(uuid);
    }

}
