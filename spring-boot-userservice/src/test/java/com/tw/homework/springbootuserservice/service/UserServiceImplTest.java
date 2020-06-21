package com.tw.homework.springbootuserservice.service;

import com.tw.homework.springbootuserservice.FeignService.EmailService;
import com.tw.homework.springbootuserservice.model.User;
import com.tw.homework.springbootuserservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImplTest {
    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private EmailService emailService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("user1");
        user.setAge(12);
        userService.save(user);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void deleteTest() {
        String userId = "848c46af-53cc-4582-a01a-2d8c530624ec";
        userService.delete(userId);
        verify(userRepository,times(1)).deleteById(UUID.fromString(userId));
    }

    @Test
    public void updateTest() {
        String userId = "848c46af-53cc-4582-a01a-2d8c530624ec";
        User user = new User();
        user.setId(UUID.fromString(userId));
        user.setName("user1");
        user.setAge(12);
        when(userRepository.findById(UUID
                .fromString(userId))).thenReturn(Optional.of(user));
        userService.update(user);
        verify(userRepository,times(1)).findById(UUID.fromString(userId));
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void getTest(){
        String userId = "848c46af-53cc-4582-a01a-2d8c530624ec";
        User user = new User();
        user.setId(UUID.fromString(userId));
        user.setName("user1");
        user.setAge(12);
        when(userRepository.findById(UUID
                .fromString(userId))).thenReturn(Optional.of(user));
        when(emailService.getUserEmail(userId)).thenReturn("848c46af-53cc-4582-a01a-2d8c530624ec@rest.local");

        String email = userService.get(userId).getEmail();
        assertEquals("848c46af-53cc-4582-a01a-2d8c530624ec@rest.local", email);
    }

    @Test
    public void findUsersTest(){

        String user1Id = "848c46af-53cc-4582-a01a-2d8c530624ec";
        User user1 = new User();
        user1.setId(UUID.fromString(user1Id));
        user1.setName("user1");
        user1.setAge(12);

        String user2Id = "748c46af-53cc-4582-a01a-2d8c530624ec";
        User user2 = new User();
        user2.setId(UUID.fromString(user2Id));
        user2.setName("user2");
        user2.setAge(21);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        Page<User> users= new PageImpl(list);

        Pageable pageRequest = PageRequest.of(0,1);
        when(userRepository.findAll(pageRequest)).thenReturn(users);
        when(emailService.getUserEmail("848c46af-53cc-4582-a01a-2d8c530624ec")).thenReturn("848c46af-53cc-4582-a01a-2d8c530624ec@rest.local");
        when(emailService.getUserEmail("748c46af-53cc-4582-a01a-2d8c530624ec")).thenReturn("748c46af-53cc-4582-a01a-2d8c530624ec@rest.local");

        Page<User> usersResult = userService.findUsers(0, 1);

        assertEquals(2, usersResult.getSize());
    }
}