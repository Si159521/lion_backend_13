package com.example.iocexam.repository;

import com.example.iocexam.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository("aaa")
public class UserDaoImpl implements UserDao{
    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void addUser(User user) {
        System.out.println(user+"의 정보가 잘 저장되었습니다.");
    }

    public Optional<User> getOptionalUser(){
        //실제메서드에서는 DB에서 꺼내오든 데이터를 얻어올꺼다.
        User user = null;

        return Optional.of(user);
    }
}
