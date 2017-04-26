package br.edu.ifpb.ads.questao_01_shared;

import br.edu.ifpb.ads.questao_01_shared.Cliente;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 26/04/2017, 00:08:23
 */
public interface ClienteDaoMySQL extends Remote{

    public List<Cliente> getClientesFromEmpresa(long empresaId) throws RemoteException;

    public boolean salvarCliente(Cliente cliente, long id) throws RemoteException;
}
