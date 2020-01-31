//package com.learn.restapi.service;
//
//import com.learn.restapi.LearnRestAPIApplication;
//import com.learn.restapi.dto.UserReqDTO;
//import com.learn.restapi.dto.UserRespDTO;
//import com.learn.restapi.entity.User;
//import com.learn.restapi.exception.DataNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = LearnRestAPIApplication.class)
//@Slf4j
//public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void findAll() {
//        List<User> userRespDTOTest = userService.findAll();
//        assertThat(userRespDTOTest.size()).isNotNull();
//        for (int i = 0; i < userRespDTOTest.size(); i++) {
//        }
//    }
//
//    @Test
//    public void findById() {
//
//        Integer id = 3;
//        User user = userService.findById(id);
//
//        assertThat(user)
//                .hasFieldOrProperty("username")
//                .hasFieldOrProperty("saldoUser");
//    }
//
//    @Test
//    public void create() {
//        UserReqDTO userReqDTO = new UserReqDTO();
//
//        String username = "roro";
//        Integer saldoUser = 1000000;
//
//        userReqDTO.setUsername(username);
//        userReqDTO.setSaldoUser(saldoUser);
//
//        UserRespDTO userRespDTO = userService.create(userReqDTO);
//        assertThat(userRespDTO.getUsername()).isEqualTo(username);
//        assertThat(userRespDTO.getSaldoUser()).isEqualTo(saldoUser);
//
//    }
//
//    @Test
//    public void update(){
//
//        UserReqDTO userReqDTO = new UserReqDTO();
//
//        Integer id = 2;
//        String username = "wonpil";
//        Integer saldoUser = 2000000;
//
//        userReqDTO.setUsername(username);
//        userReqDTO.setSaldoUser(saldoUser);
//
//        UserRespDTO userRespDTO = userService.update(id, userReqDTO);
//        assertThat(userRespDTO.getUsername()).isEqualTo(username);
//        assertThat(userRespDTO.getSaldoUser()).isEqualTo(saldoUser);
//    }
//
//    @Test
//    public void delete(){
//        Integer id = 1;
//        List<User> users = userService.findAll();
//        userService.delete(id);
//
//        List<User> userServiceAll = userService.findAll();
//        assertThat(users.size())
//                .isGreaterThan(userServiceAll.size());
//    }
//
//    @Test (expected = DataNotFoundException.class)
//    public void failedFindById(){
//
//        Integer id = 100;
//        User user = userService.findById(id);
//        assertThat(user.getIdUser()).isNotEqualTo(id);
//    }
//
//}
