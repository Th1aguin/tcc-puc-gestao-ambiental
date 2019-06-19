package br.com.puc.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.puc.tcc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}