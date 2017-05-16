/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod.questao_05.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pod.questao_05.entitys.Message;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 21:51:47
 */
public interface Subscriber extends Remote {
    
    void update(Message msg) throws RemoteException;
    
    String getUUID() throws RemoteException;
}
