/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod.questao_05.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 21:50:32
 */
public interface Topic extends Remote {

    public void register(String uuid, Subscriber sub) throws RemoteException;
}
