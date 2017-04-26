package br.edu.ifpb.ads.questao_01_node1;

import br.edu.ifpb.ads.questao_01_shared.EmpresaDaoPostgreSQL;
import br.edu.ifpb.ads.questao_01_shared.Empresa;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 26/04/2017, 00:19:50
 */
public class EmpresaDaoPostgreSQLImpl extends UnicastRemoteObject implements EmpresaDaoPostgreSQL {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/questao_01_node1";
    static final String USER = "postgres";
    static final String PASS = "12345";

    public EmpresaDaoPostgreSQLImpl() throws RemoteException{
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    @Override
    public boolean salvarEmpresa(Empresa empresa) throws RemoteException{
        System.out.println("Saving in mysqldao: " + empresa.toString());
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO empresa(id, nome, descricao) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, empresa.getId());
            ps.setString(i++, empresa.getNome());
            ps.setString(i++, empresa.getDescricao());

            return ps.executeUpdate() != 0;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDaoPostgreSQLImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public Empresa getEmpresaFromId(long empresaId) throws RemoteException{

        Empresa empresa = new Empresa();
        try {
            Connection connection = getConnection();
            String sql = "SELECT * FROM empresa e WHERE e.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, empresaId);
            ResultSet executeQuery = ps.executeQuery();

            if (executeQuery.next()) {
                empresa.setId(executeQuery.getLong("id"));
                empresa.setNome(executeQuery.getString("nome"));
                empresa.setDescricao(executeQuery.getString("descricao"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDaoPostgreSQLImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empresa;
    }

}
