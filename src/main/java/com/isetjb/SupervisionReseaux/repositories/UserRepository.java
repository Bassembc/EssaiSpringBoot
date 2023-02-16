package com.isetjb.SupervisionReseaux.repositories;


import com.isetjb.SupervisionReseaux.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUserNameAndPassword(String userName, String password);
}
