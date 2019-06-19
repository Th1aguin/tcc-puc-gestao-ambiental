package br.com.puc.tcc.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}