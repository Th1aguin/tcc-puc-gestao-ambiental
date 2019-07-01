package br.com.puc.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Role;
import br.com.puc.tcc.model.User;
import br.com.puc.tcc.repository.RoleRepository;
import br.com.puc.tcc.repository.UserRepository;
import br.com.puc.tcc.service.exception.ServiceException;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public List<User> listar() {
		return userRepository.findAll();
	}
    
    public List<Role> listarRoles() {
		return roleRepository.findAll();
	}
    
    public void deletar(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("O Usuario não pôde ser encontrado.");
		}
	}
}