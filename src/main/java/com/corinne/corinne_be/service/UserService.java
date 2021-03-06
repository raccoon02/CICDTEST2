package com.corinne.corinne_be.service;





import com.corinne.corinne_be.dto.MsgReponseDto;
import com.corinne.corinne_be.dto.user_dto.*;
import com.corinne.corinne_be.model.User;

import com.corinne.corinne_be.repository.UserRepository;

import com.corinne.corinne_be.security.UserDetailsImpl;

import com.corinne.corinne_be.utils.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final Validator validator;

    //회원정보 조희
    public UserInfoResponesDto UserInfo(UserDetailsImpl userDetails){

        return new UserInfoResponesDto(userDetails);
    }

    /**
     * 회원정보 수정
     * @param userDetails
     * @param userRequestdto
     * @return MsgReponseDto
     */
    @Transactional
    public MsgReponseDto InfoUpdate(UserDetailsImpl userDetails, UserRequestdto userRequestdto){
        Long userId = userDetails.getUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        userRequestdto.setUserEmail(userDetails.getUsername());
        try{
            validator.userValidate(userRequestdto);
        }catch (IllegalArgumentException e){
            String msg = e.getMessage();
            return new MsgReponseDto(HttpStatus.BAD_REQUEST, msg);
        }
        user.infoUpdate(userRequestdto);
        return new MsgReponseDto(HttpStatus.OK,null);
    }

}
