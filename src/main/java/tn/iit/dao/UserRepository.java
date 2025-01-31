package tn.iit.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.iit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
