package pod.questao_05.abstracts;



import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pod.questao_05.entitys.Message;
import pod.questao_05.interfaces.Subscriber;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 21:55:52
 */
public abstract class ChatClient extends UnicastRemoteObject implements Subscriber, Serializable {
    
    public ChatClient() throws RemoteException {
    }
    
    public abstract void sendMessage(Message msg) throws RemoteException ;

}
