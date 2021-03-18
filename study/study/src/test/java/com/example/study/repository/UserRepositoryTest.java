package com.example.study.repository;



import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.Optional;


//@SpringBootTest
public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();

        user.setAccount("T3");
        user.setEmail("t3@gmail.com");
        user.setPhoneNumber("010-7777-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");


        User newUser = userRepository.save(user);
        System.out.println(newUser);



    }

    @Test
    public void read(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            System.out.println("user :"+selectUser);
            System.out.println("email : "+ selectUser.getEmail());
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("PPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update Met");

            userRepository.save(selectUser);

        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);

        }); 
        Optional<User> deluser = userRepository.findById(3L);

        assertFalse(deluser.isPresent());

    }
}
