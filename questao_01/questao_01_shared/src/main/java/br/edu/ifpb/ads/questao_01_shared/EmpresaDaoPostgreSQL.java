/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.questao_01_shared;

import br.edu.ifpb.ads.questao_01_shared.Empresa;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 26/04/2017, 00:18:01
 */
public interface EmpresaDaoPostgreSQL extends Remote{

    public boolean salvarEmpresa(Empresa empresa) throws RemoteException;

    public Empresa getEmpresaFromId(long empresaId) throws RemoteException;
}
