package br.edu.ifpb.ads.client;

import br.edu.ifpb.ads.questao_02_shared.Configs;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.ifpb.ads.questao_02_shared.RmiNodeContract;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 00:49:26
 */
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Inciando Client ...");

            System.out.println("Obtendo Conecção com Node1 ...");
            Registry r1 = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_1_PORT);
            RmiNodeContract node1 = (RmiNodeContract) r1.lookup(Configs.NODE_1_NAME);

            System.out.println("Obtendo Conecção com Node2 ...");
            Registry r2 = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
            RmiNodeContract node2 = (RmiNodeContract) r2.lookup(Configs.NODE_2_NAME);

            System.out.println("Obtendo Conecção com Node3 ...");
            Registry r3 = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_3_PORT);
            RmiNodeContract node3 = (RmiNodeContract) r3.lookup(Configs.NODE_3_NAME);

            System.out.println("Efetuando Testes ...");
            System.out.println("Test de Soma(1,9) >> Node1: " + node1.sum(1, 9));
            System.out.println("Test de Soma(1,10) >> Node2: " + node2.sum(1, 10));
            System.out.println("Test de Subtracao(10,5) >> Node3 : " + node3.diff(10, 5));

            System.out.println("Test de Subtracao(20,5) >> Node1: " + node1.diff(20, 5));
            try{
            System.out.println("Test de Subtracao(30,5) >> Node2: " + node2.diff(30, 5));
            }catch(RemoteException e){
                System.out.println("Ação impossivel para esse node (" + e.getMessage() + ")");
            }
            System.out.println("Test de Soma(40,5) >> Node3: " + node3.sum(40, 5));

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
