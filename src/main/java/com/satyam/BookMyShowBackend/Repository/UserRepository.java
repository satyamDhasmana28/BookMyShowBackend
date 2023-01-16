package com.satyam.BookMyShowBackend.Repository;

import com.satyam.BookMyShowBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
