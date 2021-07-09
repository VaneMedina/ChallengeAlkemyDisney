package com.company.challengealkemy.Servicies;

import com.company.challengealkemy.Dto.LoginDto;
import com.company.challengealkemy.Enum.Rol;
import com.company.challengealkemy.Model.Role;
import com.company.challengealkemy.Model.User;
import com.company.challengealkemy.Repositories.RoleRepository;
import com.company.challengealkemy.Repositories.UserRepository;
import com.company.challengealkemy.Security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@NoArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;


    public User registerUser(User user){
        Role role = new Role();
        role.setNameRol(Rol.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.save(role);
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return User.build(user);
    }

    public String login(LoginDto loginDto) {
        return jwtProvider.generateToken((User) loadUserByUsername(loginDto.getEmail()));
    }
}
