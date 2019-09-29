package com.kedacom.dao;

import com.kedacom.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kedacom on 2019/8/2.
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer>{

}
