package com.LocalisFood.LocalisFood.Service;

import com.LocalisFood.LocalisFood.Exceptions.SpringLocalisFoodException;
import com.LocalisFood.LocalisFood.Model.User;
import com.LocalisFood.LocalisFood.Model.UserRole;
import com.LocalisFood.LocalisFood.Repository.UserRepository;
import com.LocalisFood.LocalisFood.Security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;


@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    public String signup(User User) {
        if (!userRepository.existsByUsername(User.getUsername())) {
            User.setPassword(passwordEncoder.encode(User.getPassword()));
            userRepository.save(User);
            User.setUserRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_CLIENT)));
            return jwtProvider.createToken(User.getUsername(), User.getUserRoles());
        } else {
            throw new SpringLocalisFoodException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtProvider.getUsername(jwtProvider.resolveToken(req)));
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new SpringLocalisFoodException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getSubject());

    }

    public String  login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtProvider.createToken(username, userRepository.findByUsername(username).getUserRoles());
        } catch (AuthenticationException e) {
            throw new SpringLocalisFoodException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
    public String refresh(String username) {
        return jwtProvider.createToken(username, userRepository.findByUsername(username).getUserRoles());
    }
}