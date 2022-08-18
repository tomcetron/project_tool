package com.proma.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.proma.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
	
	

}
