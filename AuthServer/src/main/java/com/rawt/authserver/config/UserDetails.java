package com.rawt.authserver.config;

import com.rawt.authserver.model.ShopUser;
import com.rawt.authserver.repos.ShopUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetails implements UserDetailsService {

    private final ShopUserRepository shopUserRepository;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopUser user = shopUserRepository.findByEmail(username);
        if(user != null){
            Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
            user.getRoles().forEach(role->grantedAuthoritySet.add(new SimpleGrantedAuthority(role)));
            return new User(user.getEmail(),user.getPassword(),grantedAuthoritySet);
        }
        else throw new UsernameNotFoundException("User with username: " + username + " not found");
    }
}
