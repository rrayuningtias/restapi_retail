package com.learn.restapi.service;

import com.learn.restapi.dto.UserReqDTO;
import com.learn.restapi.dto.UserRespDTO;
import com.learn.restapi.entity.User;
import com.learn.restapi.entity.Warehouse;
import com.learn.restapi.exception.DataNotFoundException;
import com.learn.restapi.repository.UserRepository;
import com.learn.restapi.util.AES;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//untuk menandakan kalo userService ada dikelas service
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AES aes;

    public List<UserRespDTO> findAll() throws UnsupportedEncodingException {

        List<User> listUser = userRepository.findAll();

        List<UserRespDTO> listUserDTO = new ArrayList<>();
        for ( User user : listUser ) {
            UserRespDTO userRespDTO = new UserRespDTO();
            userRespDTO.setUsername(user.getUsername());
            userRespDTO.setSaldoUser(user.getSaldoUser());
            userRespDTO.setId(user.getIdUser());
            userRespDTO.setIdEncrypt(URLEncoder.encode(aes.encrypt(user.getIdUser().toString()), StandardCharsets.UTF_8.toString()));

            String a = aes.encrypt(user.getIdUser().toString());
            System.out.println("raw: "+ user.getIdUser().toString());
            System.out.println("encrypt: "+a);
            System.out.println("decrypt: "+aes.decrypt(a));
            listUserDTO.add(userRespDTO);
        }
        return listUserDTO;
    }

    public UserRespDTO findById(Integer id) throws UnsupportedEncodingException {

        User user = userRepository.findByIdUser(id);
        UserRespDTO userRespDTO = new UserRespDTO();
        userRespDTO.setIdEncrypt(URLEncoder.encode(aes.encrypt(user.getIdUser().toString()), StandardCharsets.UTF_8.toString()));
        userRespDTO.setId(user.getIdUser());
        userRespDTO.setUsername(user.getUsername());
        userRespDTO.setSaldoUser(user.getSaldoUser());

        return userRespDTO;
    }

    public UserRespDTO create(UserReqDTO userReqDTO) {
        User user = new User();
        user.setUsername(userReqDTO.getUsername());
        user.setSaldoUser(userReqDTO.getSaldoUser());

        userRepository.save(user);

        UserRespDTO userRespDTO = new UserRespDTO();
        userRespDTO.setId(user.getIdUser());
        userRespDTO.setUsername(user.getUsername());
        userRespDTO.setSaldoUser(user.getSaldoUser());

        return userRespDTO;
    }

    public UserRespDTO update (Integer id, UserReqDTO userReqDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("Data tidak ditemukan");

        User user = new User();
        user.setIdUser(id);
        user.setUsername(userReqDTO.getUsername());
        user.setSaldoUser(userReqDTO.getSaldoUser());

        UserRespDTO userRespDTO = new UserRespDTO();
        userRespDTO.setId(id);
        userRespDTO.setUsername(user.getUsername());
        userRespDTO.setSaldoUser(user.getSaldoUser());

        userRepository.save(user);
        return userRespDTO;
    }

    public void delete (Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new DataNotFoundException("ID Tidak ditemukan");
        }
        userRepository.deleteById(id);
    }

    public void save (User user) {
        userRepository.save(user);
    }

}
