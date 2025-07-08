package com.toplistapronta.app;
package TopLista;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String login;
    private String senha;
    private boolean admin;
    private StatusConta status = StatusConta.ATIVA;

   
    private List<Item> itensComprados = new ArrayList<>();

    public enum StatusConta { ATIVA, DESATIVADA, EXCLUIDA }

    public Usuario(String login, String senha, boolean admin) {
        this.login = login;
        this.senha = senha;
        this.admin = admin;
    }

    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public boolean isAdmin() { return admin; }
    public StatusConta getStatus() { return status; }
    public void setStatus(StatusConta status) { this.status = status; }

    public List<Item> getItensComprados() { return itensComprados; }
    public void addItemComprado(Item item) { itensComprados.add(item); }

    @Override
    public String toString() {
        return login + (admin ? " (Admin)" : "");
    }
}
