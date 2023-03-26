package com.LocalisFood.LocalisFood.Repository;


import com.LocalisFood.LocalisFood.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}
