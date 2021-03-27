package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {
    //1. request data
    //2. user 생성
    //3. 생성된 데이터 -> userApiResponse return
    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        User user = User.builder().account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now()).build();
        User newUser=userRepository.save(user);


        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        Optional<User> optional = userRepository.findById(id);
        System.out.println(optional);
//
//        return optional.map(user->response(user))
//        .orElseGet(()->Header.ERROR("데이터 없음"));

        return userRepository.findById(id)
                .map(user->response(user))
                .orElseGet(
                        ()->Header.ERROR("데이터없음")
                );

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //data
        UserApiRequest userApiRequest= request.getData();
        //id -> user 데이터를 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        System.out.println(userApiRequest.getAccount());

        return optional.map(user->{
            //3 update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            //4.user Api Response
            return user;
        })
                .map(user->userRepository.save(user)) //update
                .map(user ->response(user)) //userApiResponse
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {

        //id-> repository -> user
        Optional<User> optional = userRepository.findById(id);
        // repo->del
        return optional.map(user->{
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("NODATA"));
        //3. response return

    }

    private Header<UserApiResponse> response(User user){
        //user- > user ApiResponse

        UserApiResponse userApiResponse =UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //암호화 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisteredAt(user.getUnregisteredAt())
                .build();
        //Header + data return
        return Header.OK(userApiResponse);
    }



}
