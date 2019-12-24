package com.proda5.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proda5.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
