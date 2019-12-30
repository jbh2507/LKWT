package com.chiroro.lkwt_boot.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import com.chiroro.lkwt_boot.domain.Lecture;
import com.chiroro.lkwt_boot.domain.Role;
import com.chiroro.lkwt_boot.domain.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * UserRepository
 */
@SpringBootTest
@Slf4j
public class UserRepositoryTests {

    @Setter(onMethod_ = @Autowired)
    private UserRepository repo;

    @Autowired
    private LectureRepository lecRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void isExist(){
        assertNotNull(repo);
    }

    @Test
    @Transactional
    @Commit
    public void insertTest(){
        User user = new User();
        String userId = "tester";
        Set<Role> rolesSet = new HashSet<>();
        
        user.setUsername(userId);
        user.setPassword(encoder.encode(userId));
        user.setAuthorities(rolesSet);

        Lecture lecture = lecRepo.getOne(1L);
        user.setLecture(lecture);
        
        repo.save(user);
    }
    
}