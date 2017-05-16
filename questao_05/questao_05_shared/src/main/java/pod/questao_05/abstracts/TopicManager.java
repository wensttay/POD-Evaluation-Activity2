package pod.questao_05.abstracts;

import pod.questao_05.interfaces.Publisher;
import pod.questao_05.interfaces.Topic;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 21:57:50
 */
public abstract class TopicManager extends UnicastRemoteObject implements Publisher, Topic {

    public TopicManager() throws RemoteException {
    }
    
    public abstract void notifySubscribers() throws RemoteException;
    
}
