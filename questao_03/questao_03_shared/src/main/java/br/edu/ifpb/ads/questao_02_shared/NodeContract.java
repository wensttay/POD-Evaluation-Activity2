package br.edu.ifpb.ads.questao_02_shared;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 23:43:59
 */
public interface NodeContract{

    public String sum(int x, int y);

    public String diff(int x, int y);

}
