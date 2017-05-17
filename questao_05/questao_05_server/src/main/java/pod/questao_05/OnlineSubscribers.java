package pod.questao_05;

import java.util.HashMap;
import pod.questao_05.interfaces.Subscriber;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/05/2017, 15:27:08
 */
public class OnlineSubscribers {
    
    HashMap<String, Subscriber> subscribers;

    public OnlineSubscribers() {
        subscribers = new HashMap<>();
    }
    
    public void store(String uuid, Subscriber sub) {
        subscribers.put(uuid, sub);
    }

    public Subscriber find(String uuid) {
        return subscribers.get(uuid);
    }
    
}
