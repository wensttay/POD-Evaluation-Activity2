package pod.questao_05_client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import pod.questao_05.entitys.Message;
import pod.questao_05.interfaces.Publisher;
import pod.questao_05.interfaces.Topic;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 22:31:59
 */
public class AppCliente {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        String uuid = "wensttay@gmail.com";
        
        System.out.println("Logado como: " + uuid);
        Registry registry = LocateRegistry.getRegistry(10999);
        
        Topic topic = (Topic) registry.lookup("_ChatServer_");
        Publisher publisher = (Publisher) registry.lookup("_ChatServer_");
        
        ChatClientImpl client = new ChatClientImpl(publisher);
        client.setUUID(uuid);
        topic.register(uuid, client);
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {            
            String text = scanner.nextLine();
            
            Message message = new Message();
            message.setFrom(uuid);
            message.setText(text);
            
            client.sendMessage(message);
        }
    }
}
