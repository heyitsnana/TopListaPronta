package com.toplistapronta.app;

public class usuario {

	
	private String login;
    private String senha;
    private boolean isAdmin;

    public usuario(String login, String senha, boolean isAdmin) {
        this.login = login;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


}
