package pod.questao_05;

import pod.questao_05.abstracts.TopicManager;
import pod.questao_05.entitys.Message;
import pod.questao_05.interfaces.Subscriber;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import pod.questao_05.entitys.Notification;
import pod.questao_05.repositorys.NotificationRepository;
import pod.questao_05.repositorys.SubscriberRepository;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 22:13:04
 */
public class ChatServer extends TopicManager {

    private final NotificationRepository notificationRepository;
    private final SubscriberRepository subscriberRepository;
    private final OnlineSubscribers onlineSubscribers;
//    private final OnlineNotifications onlineNotifications;

    public ChatServer() throws RemoteException {
        super();
//        this.onlineNotifications = new OnlineNotifications();
//        this.notificationRepository = new NotificationRepository(onlineNotifications);

        this.onlineSubscribers = new OnlineSubscribers();
        this.notificationRepository = new NotificationRepository(onlineSubscribers);
        this.subscriberRepository = new SubscriberRepository(onlineSubscribers);
    }

    @Override
    public void publish(Message msg) throws RemoteException {
        System.out.println("Publicando: " + msg.toString());
       
        String[] listUUIDs = subscriberRepository.listUUIDs();

        for (String uuid : listUUIDs) {
            if (!msg.getFrom().equals(uuid)) {
                msg.setTo(uuid);
                notificationRepository.store(new Notification(msg));
            }
        }
    }

    @Override
    public void register(String uuid, Subscriber sub) throws RemoteException {
        System.out.println("Registrando: " + uuid);
        subscriberRepository.store(uuid, sub);
    }

    @Override
    public void notifySubscribers() throws RemoteException {
        System.out.println("Efetuando todas notificações ...");

        String[] uuids = subscriberRepository.listUUIDs();
        
        for (String uuid : uuids) {
            
            Notification[] listNotifications = notificationRepository.listNotifications(uuid);
            for (Notification notific : listNotifications) {
                System.out.println("Notificação !! > " + notific.getMsg().getText());
                
                Subscriber[] subs = notific.getSubs();
                for (Subscriber sub : subs) {
                    boolean updated = true;
                    try {
                        if (sub != null) {
                            sub.update(notific.getMsg()); 
                        //
                        // Se acontecer exeção aqui ele volta depois de tratar?
                        //
                        }
                    } catch (RemoteException ex) {
                        updated = false;
                    }
                    
                    if(updated){
                        notificationRepository.removeNotifications(notific.getId());
                    }
                }

//                if (notAlerteds.isEmpty()) {
//                    notificationRepository.removeNotifications(uuid);
//                } else {
//                    System.out.println("Foram encontrados subscribes "
//                            + "desconectados para essa notificação");
//
//                    Subscriber[] notAlertedArray = notAlerteds.toArray(new Subscriber[notAlerteds.size()]);
//                    onlineNotifications.store(uuid, notAlertedArray);
//                }
            }
            
        }
    }

}
