package pod.questao_05;

import pod.questao_05.abstracts.TopicManager;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 22:25:30
 */
public class AppServidor {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        final TopicManager manager = new ChatServer();
        
        Registry registry = LocateRegistry.createRegistry(10999);
        registry.bind("_ChatServer_", manager);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    manager.notifySubscribers();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }, 1000, 10000);
    }

}
