package br.com.puc.tcc.service;

import br.com.puc.tcc.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}