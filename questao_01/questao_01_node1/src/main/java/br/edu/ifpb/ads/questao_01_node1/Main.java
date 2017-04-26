package br.edu.ifpb.ads.questao_01_node1;

import br.edu.ifpb.ads.questao_01_shared.Configs;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 26/04/2017, 00:29:33
 */
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Inciando Node1 ...");
            System.setProperty("java.rmi.server.hostname", Configs.LOCALHOST_IP);
            Registry registry = LocateRegistry.createRegistry(Configs.NODE_1_PORT);

            System.out.println("Disponibilizando Node1 ...");
            registry.bind(Configs.NODE_1_NAME, new EmpresaDaoPostgreSQLImpl());

        } catch (RemoteException | AlreadyBoundException ex) {
            ex.printStackTrace();
        }
    }
}
