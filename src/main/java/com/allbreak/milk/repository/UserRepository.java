package com.allbreak.milk.repository;

import com.allbreak.milk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 17:06
 * @Version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByAccount(String account);

    User findByNickName(String nickName);
}
