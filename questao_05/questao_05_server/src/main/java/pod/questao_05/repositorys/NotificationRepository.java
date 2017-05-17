package pod.questao_05.repositorys;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pod.questao_05.OnlineNotifications;
import pod.questao_05.OnlineSubscribers;
import pod.questao_05.entitys.Message;
import pod.questao_05.entitys.Notification;
import pod.questao_05.interfaces.Subscriber;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 22:03:46
 */
public class NotificationRepository extends JDBCRepository {

//    private OnlineNotifications onlineNotifications;
//    public NotificationRepository(OnlineNotifications onlineNotifications) {
//        this.onlineNotifications = onlineNotifications;
//    }
    private OnlineSubscribers onlineSubscribers;

    public NotificationRepository(OnlineSubscribers onlineSubscribers) {
        this.onlineSubscribers = onlineSubscribers;
    }

    public void store(Notification n) {

        try {
            conectar();
            Subscriber[] subs = n.getSubs();

//            for (Subscriber sub : subs) {
                String sql = "INSERT INTO notification (uuidFrom, message, uuidTo) values(?, ?, ?)";
                PreparedStatement statement = getConnection().prepareStatement(sql);
                int i = 1;
                statement.setString(i++, n.getMsg().getFrom());
                statement.setString(i++, n.getMsg().getText());
                statement.setString(i++, n.getMsg().getTo());
                statement.executeUpdate();
//            }

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }

//        onlineNotifications.store(n.getMsg().getFrom(), n.getSubs());
    }

    public Notification[] listNotifications(String uuid) {
        try {
            conectar();
            String sql = "SELECT * FROM notification WHERE uuidFrom = ?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, uuid);
            ResultSet executeQuery = statement.executeQuery();

            HashMap<String, Notification> hashMap = new HashMap<>();
            HashMap<String, List<Subscriber>> hashSubs = new HashMap<>();

            while (executeQuery.next()) {
                Notification notification = new Notification();
                notification.setId(executeQuery.getLong("id"));

                Message message = new Message();
                message.setText(executeQuery.getString("message"));
                message.setFrom(executeQuery.getString("uuidFrom"));
                notification.setMsg(message);

//                notifications.add(notification);
                String to = executeQuery.getString("uuidTo");
                Subscriber sub = onlineSubscribers.find(to);
                String text = message.getText();

                if (sub != null) {
                    if (hashSubs.containsKey(text)) {
                        hashSubs.get(text).add(sub);
                    } else {
                        hashMap.put(text, notification);
                        hashSubs.put(text, new ArrayList<>());
                        hashSubs.get(text).add(sub);
                    }
                }
            }

            List<Notification> notifications = new ArrayList<>();
            for (String s : hashMap.keySet()) {
                Notification notifc = hashMap.get(s);
                List<Subscriber> subss = hashSubs.get(s);

                notifc.setSubs(subss.toArray(new Subscriber[subss.size()]));
                notifications.add(notifc);
            }

            return notifications.toArray(new Notification[notifications.size()]);

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }

        return new Notification[0];
    }

//    public void removeNotifications(String id) {
    public void removeNotifications(Long id) {
        try {

            conectar();
            String sql = "DELETE FROM notification WHERE id = ?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
//            statement.setString(1, id);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }

//        onlineNotifications.removeNotifications(id);
    }

    public void removeNotifications(String id) {

        try {
            conectar();
            String sql = "DELETE FROM notification WHERE uuidFrom = ?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }
        
//        onlineNotifications.removeNotifications(id);
    }

}
