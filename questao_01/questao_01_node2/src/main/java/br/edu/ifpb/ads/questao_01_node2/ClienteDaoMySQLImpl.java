package br.edu.ifpb.ads.questao_01_node2;

import br.edu.ifpb.ads.questao_01_shared.ClienteDaoMySQL;
import br.edu.ifpb.ads.questao_01_shared.Cliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 26/04/2017, 00:19:50
 */
public class ClienteDaoMySQLImpl extends UnicastRemoteObject implements ClienteDaoMySQL {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/questao_01_node2";
    static final String USER = "root";
    static final String PASS = "12345";

    public ClienteDaoMySQLImpl() throws RemoteException{
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    @Override
    public List<Cliente> getClientesFromEmpresa(long empresaId) throws RemoteException{
        List<Cliente> clientes = new ArrayList<>();
        try {
            Connection connection = getConnection();
            String sql = "SELECT * FROM cliente c WHERE c.emp_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, empresaId);
            ResultSet executeQuery = ps.executeQuery();

            while (executeQuery.next()) {
                Cliente cliente = fillClient(executeQuery);
                clientes.add(cliente);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoMySQLImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;
    }

    @Override
    public boolean salvarCliente(Cliente cliente, long emp_id) throws RemoteException{
        System.out.println("Saving in mysql: " + cliente.toString());
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO cliente(id, nome, emp_id) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, cliente.getId());
            ps.setString(i++, cliente.getNome());
            ps.setLong(i++, emp_id);

            return ps.executeUpdate() != 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoMySQLImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private Cliente fillClient(ResultSet executeQuery) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(executeQuery.getLong("id"));
        cliente.setNome(executeQuery.getString("nome"));
        return cliente;
    }

}
