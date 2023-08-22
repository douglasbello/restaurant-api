package br.com.douglasbello.restaurant.services.impl;

import java.util.UUID;

import br.com.douglasbello.restaurant.model.dtos.AdminDTO.UsernamePasswordDTO;
import br.com.douglasbello.restaurant.model.dtos.AuthenticationTokenDTO;
import br.com.douglasbello.restaurant.model.entities.Admin;
import br.com.douglasbello.restaurant.repository.AdminRepository;
import br.com.douglasbello.restaurant.security.TokenService;
import br.com.douglasbello.restaurant.services.AbstractService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends AbstractService<Admin> implements UserDetailsService {

    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AdminServiceImpl(AdminRepository repository, @Lazy AuthenticationManager authenticationManager, @Lazy TokenService tokenService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    protected JpaRepository<Admin, UUID> getRepository() {
        return repository;
    }

    @Override
    public void updateData(Admin old, Admin newObj) {
        old.setUsername(newObj.getUsername());
        old.setPassword(newObj.getPassword());
    }

    public AuthenticationTokenDTO login(UsernamePasswordDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Admin) auth.getPrincipal());
        return new AuthenticationTokenDTO(token);
    }

    public void updateDataUsingDTO(Admin old, UsernamePasswordDTO dto) {
        old.setUsername(dto.username());
        old.setPassword(passwordEncoder.encode(dto.password()));
    }

    public Admin updateUsingDTO(UUID id, UsernamePasswordDTO dto) {
        Admin old = findById(id);
        updateDataUsingDTO(old, dto);
        return getRepository().save(old);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
