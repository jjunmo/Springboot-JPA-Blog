package com.junmo.blog.controller.api;

import com.junmo.blog.dto.ResponseDto;
import com.junmo.blog.model.RoleType;
import com.junmo.blog.model.User;
import com.junmo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController 호출");
        user.setRole(RoleType.USER);
        int result=userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }



}
