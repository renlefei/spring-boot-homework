package com.tw.homework.springbootuserservice.service;

import com.tw.homework.springbootuserservice.FeignService.EmailService;
import com.tw.homework.springbootuserservice.model.User;
import com.tw.homework.springbootuserservice.model.UserQuery;
import com.tw.homework.springbootuserservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private EmailService emailService;

    public void save(User user){
        Date date = new Date();
        user.setCreatedAt(date);
        userRepository.save(user);
    }

    public void delete(String id){
        userRepository.deleteById(UUID.fromString(id));
    }

    public void update(User user){
        Optional<User> optional = userRepository.findById(user.getId());
        if(optional.isPresent()){
            optional.get().setName(user.getName());
            optional.get().setAge(user.getAge());
            optional.get().setUpdatedAt(new Date());
            userRepository.save(optional.get());
        }
    }

    public User get(String id){
        Optional<User> optional = userRepository.findById(UUID.fromString(id));
        String email = emailService.getUserEmail(id);
        if(optional.isPresent()){
            optional.get().setEmail(email);
            return optional.get();
        }
        return null;
    }

    public Page<User> findUsers(Integer page, Integer size){
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        users.forEach(user -> user.setEmail(emailService.getUserEmail(user.getId().toString())));
        return users;
    }

    public Page<User> findUsersBy(Integer page, Integer size, UserQuery userQuery){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.equal(root.get("name").as(String.class), userQuery.getName());
                Predicate p2 = criteriaBuilder.equal(root.get("age").as(Integer.class), userQuery.getAge());
                query.where(criteriaBuilder.and(p1,p2));
                return query.getRestriction();
            }
        }, pageable);

        userPage.forEach(user -> user.setEmail(emailService.getUserEmail(user.getId().toString())));
        return userPage;
    }
}
