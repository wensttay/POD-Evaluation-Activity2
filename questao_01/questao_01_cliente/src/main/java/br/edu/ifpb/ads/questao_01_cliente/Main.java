package br.edu.ifpb.ads.questao_01_cliente;

import br.edu.ifpb.ads.questao_01_shared.Cliente;
import br.edu.ifpb.ads.questao_01_shared.ClienteDaoMySQL;
import br.edu.ifpb.ads.questao_01_shared.Configs;
import br.edu.ifpb.ads.questao_01_shared.Empresa;
import br.edu.ifpb.ads.questao_01_shared.EmpresaDaoPostgreSQL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 26/04/2017, 00:36:45
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Inciando Client ...");
            System.out.println("Instanciando uma impresa e seus empregados ...");
            Empresa empresa = new Empresa(0, "Hero-Code", "Melhor empresa de ADS que você respeita");
            empresa.addCliente(new Cliente(1, "Pedro"));
            empresa.addCliente(new Cliente(2, "Victor"));
            empresa.addCliente(new Cliente(3, "Wensttay"));
            
            System.out.println("Salvando a Empresa e seus Clientes ...");
//            SalvarEmpresa(empresa);
            
            System.out.println("Recuperando Empresa salva e seus Clientes ...");
            Empresa empresaRecueprada = ObterEmpresa(empresa.getId());
            System.out.println(empresaRecueprada);
            
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void SalvarClientesDaEmpresa(Empresa empresa) throws NotBoundException, RemoteException {
        System.out.println("Obtendo Conecção com Node2 ...");
        Registry r2 = LocateRegistry.getRegistry(Configs.LOCALHOST_IP, Configs.NODE_2_PORT);
        ClienteDaoMySQL node2 = (ClienteDaoMySQL) r2.lookup(Configs.NODE_2_NAME);
        for (Cliente cliente : empresa.getClientes()) {
            node2.salvarCliente(cliente, empresa.getId());
        }
    }

    private static void SalvarEmpresa(Empresa empresa) throws RemoteException, NotBoundException {
        System.out.println("Obtendo Conecção com Node1 ...");
        Registry r1 = LocateRegistry.getRegistry(Configs.LOCALHOST_IP, Configs.NODE_1_PORT);
        EmpresaDaoPostgreSQL node1 = (EmpresaDaoPostgreSQL) r1.lookup(Configs.NODE_1_NAME);
        node1.salvarEmpresa(empresa);
        SalvarClientesDaEmpresa(empresa);
    }

    private static Empresa ObterEmpresa(long id) throws RemoteException, NotBoundException {
        System.out.println("Obtendo Conecção com Node1 ...");
        Registry r1 = LocateRegistry.getRegistry(Configs.LOCALHOST_IP, Configs.NODE_1_PORT);
        EmpresaDaoPostgreSQL node1 = (EmpresaDaoPostgreSQL) r1.lookup(Configs.NODE_1_NAME);
        Empresa empresaFromId = node1.getEmpresaFromId(id);
        
        System.out.println("Obtendo Conecção com Node2 ...");
        Registry r2 = LocateRegistry.getRegistry(Configs.LOCALHOST_IP, Configs.NODE_2_PORT);
        ClienteDaoMySQL node2 = (ClienteDaoMySQL) r2.lookup(Configs.NODE_2_NAME);
        List<Cliente> clientesFromEmpresa = node2.getClientesFromEmpresa(id);
        empresaFromId.setClientes(clientesFromEmpresa);
        
        return empresaFromId;
    }
    
}
