package br.edu.ifpb.ads.questao_01_shared;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 26/04/2017, 00:11:32
 */
public class Empresa implements Serializable{
    private long id;
    private String nome;
    private String descricao;
    private List<Cliente> clientes;

    public Empresa() {
        this.clientes = new ArrayList<>();
    }

    public Empresa(long id, String nome, String descricao) {
        this.clientes = new ArrayList<>();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public boolean addCliente(Cliente cliente){
        return this.clientes.add(cliente);
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", clientes=" + clientes + '}';
    }
    
    
}
