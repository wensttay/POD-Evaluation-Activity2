package pod.questao_05.repositorys;

import java.io.IOException;
import java.net.URISyntaxException;
import pod.questao_05.interfaces.Subscriber;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pod.questao_05.OnlineSubscribers;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 22:03:59
 */
public class SubscriberRepository extends JDBCRepository {
    
    private OnlineSubscribers onlineSubscribers;

    public SubscriberRepository(OnlineSubscribers onlineSubscribers) {
        this.onlineSubscribers = onlineSubscribers;
    }

    public void store(String uuid, Subscriber sub) {
        try {
            conectar();
            String sql = "INSERT INTO users(uuid) values (?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, uuid);
            ps.executeUpdate();

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            desconectar();
        }
        
        onlineSubscribers.store(uuid, sub);
    }

    public Subscriber find(String uuid) {
        return onlineSubscribers.find(uuid);
    }

    public String[] listUUIDs() {
        List<String> uuids = new ArrayList<>();
        try {
            conectar();
            String sql = "SELECT * FROM users";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                uuids.add(rs.getString("uuid"));
            }
            
        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            
        } finally {
            desconectar();
        }

        return uuids.toArray(new String[uuids.size()]);
    }
    
}
