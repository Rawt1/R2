package com.rawt.authserver.repos;

import com.rawt.authserver.model.ShopUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopUserRepository extends MongoRepository<ShopUser, String> {

    ShopUser findByEmail(String email);
}
