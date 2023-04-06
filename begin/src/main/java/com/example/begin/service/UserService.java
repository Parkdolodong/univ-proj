package com.example.begin.service;

import com.example.begin.dto.UserDto;
import com.example.begin.entity.User;
import com.example.begin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto findUserInfo(String userId) {
        var user = userRepository.findByUserId(userId);

        if(user.isPresent()){
            return entityToDto(user.get());
        }

        return new UserDto();
    }

    public UserDto findUserInfoByIdAndPw(UserDto userDto) {
        var user = userRepository.
                findByUserIdAndUserPw(userDto.getUserId(), userDto.getUserPw());
        if(user.isPresent()) {
            return entityToDto(userRepository.
                    findByUserIdAndUserPw(userDto.getUserId(), userDto.getUserPw()).get());
        }
        return null;

    }

    public UserDto registerUser(UserDto dto) {
        User user = null;
        if(dto.getIdx() == null) user = userRepository.save(dtoToEntity(dto));
        else{
            var entity = userRepository.findById(dto.getIdx()).get();
            entity.setUserId(dto.getUserId());
            entity.setUserPw(dto.getUserPw());
            entity.setAddr(dto.getAddr());
            entity.setNick(dto.getNick());
            user = userRepository.save(entity);
        }

        return entityToDto(user);
    }

    public List<UserDto> finalAllUser() {
        var users = userRepository.findAll();
        List<UserDto> dtoUsers = new ArrayList<>();
        users.forEach(u->{
            dtoUsers.add(entityToDto(u));
        });

        return dtoUsers;
    }

    private UserDto entityToDto(User user) {
        var dto = UserDto.builder().userId(user.getUserId())
                .addr(user.getAddr())
                .userPw(user.getUserPw())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .idx(user.getIdx())
                .nick(user.getNick())
                .build();
        return dto;
    }

    private User dtoToEntity(UserDto userDto) {
        var dto = User.builder().userId(userDto.getUserId())
                .addr(userDto.getAddr())
                .userPw(userDto.getUserPw())
                .nick(userDto.getNick())
                .build();
        return dto;
    }


}
