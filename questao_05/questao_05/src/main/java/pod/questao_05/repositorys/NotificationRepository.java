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
import pod.questao_05.entitys.Message;
import pod.questao_05.entitys.Notification;
import pod.questao_05.interfaces.Subscriber;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 22:03:46
 */
public class NotificationRepository extends JDBCRepository {

    private OnlineNotifications onlineNotifications;
    
    public NotificationRepository(OnlineNotifications onlineNotifications) {
        this.onlineNotifications = onlineNotifications;
    }

    public void store(Notification n) {
        try {
            conectar();
            String sql = "INSERT INTO notification (uuidFrom, message) values(?, ?)";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            int i = 1;
            statement.setString(i++, n.getMsg().getFrom());
            statement.setString(i++, n.getMsg().getText());
            statement.executeUpdate();
            
        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }
        
        onlineNotifications.store(n.getMsg().getFrom(), n.getSubs());
    }

    public Notification[] listNotifications(String uuid) {
        try {
            conectar();
            String sql = "SELECT * FROM notification WHERE uuidFrom = ?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, uuid);
            ResultSet executeQuery = statement.executeQuery();

            List<Notification> notifications = new ArrayList<>();
            
            while (executeQuery.next()) {
                Notification notification = new Notification();
                Message message = new Message();
                message.setText(executeQuery.getString("message"));
                message.setFrom(executeQuery.getString("uuidFrom"));
                notification.setMsg(message);
                notification.setSubs(onlineNotifications.listSubsFromUUID(uuid));
                notifications.add(notification);
            }

            return notifications.toArray(new Notification[notifications.size()]);

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }

        return new Notification[0];
    }

    public void removeNotifications(String uuid) {
        try {
            conectar();
            String sql = "DELETE FROM notification WHERE uuidFrom = ?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, uuid);
            statement.executeUpdate();
        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }
        
        onlineNotifications.removeNotifications(uuid);
    }

}
