package pod.questao_05_client;

import pod.questao_05.abstracts.ChatClient;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import pod.questao_05.entitys.Message;
import pod.questao_05.interfaces.Publisher;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 22:38:12
 */
public class ChatClientImpl extends ChatClient {

    private String UUID;
    private Publisher publisher;

    public ChatClientImpl(Publisher publisher) throws RemoteException {
        super();
        this.publisher = publisher;
    }

    @Override
    public void update(Message msg) throws RemoteException {
        System.out.println(msg.toString());
    }

    @Override
    public void sendMessage(Message msg) throws RemoteException {
        publisher.publish(msg);
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String getUUID() throws RemoteException {
        return this.UUID;
    }

}
