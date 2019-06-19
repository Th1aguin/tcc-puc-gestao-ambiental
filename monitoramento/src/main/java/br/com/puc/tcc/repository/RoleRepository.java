package br.com.puc.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}