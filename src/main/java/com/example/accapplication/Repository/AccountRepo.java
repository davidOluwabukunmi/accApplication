package com.example.accapplication.Repository;

import com.example.accapplication.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepo extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByPhone (String phone);
}
