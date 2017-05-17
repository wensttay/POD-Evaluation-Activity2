package pod.questao_05.repositorys;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/05/2017, 00:03:45
 */
public class JDBCRepository {
    
    public static final String PROPERTIES_PATH_DEFAULT = "/banco/banco.properties";
    private String user;
    private String url;
    private String password;
    private String driver;
    private Connection connection;
    
    public void conectar() throws URISyntaxException, IOException, SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        Properties prop = new Properties();
        prop.load(new FileInputStream(getClass().getResource(PROPERTIES_PATH_DEFAULT).toURI().getPath()));

        user = prop.getProperty("user");
        url = prop.getProperty("url");
        password = prop.getProperty("password");
        driver = prop.getProperty("driver");

        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
    }
    
    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
