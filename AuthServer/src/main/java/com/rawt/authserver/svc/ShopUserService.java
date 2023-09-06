package com.rawt.authserver.svc;

import com.rawt.authserver.model.ShopUser;
import com.rawt.authserver.repos.ShopUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopUserService {

    private final ShopUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void save(ShopUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public ShopUser findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
