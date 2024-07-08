package com.riwi.LibrosYa.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.LibrosYa.api.dto.request.UserRequest;
import com.riwi.LibrosYa.api.dto.response.UserResponse;
import com.riwi.LibrosYa.domain.model.User;
import com.riwi.LibrosYa.domain.repositories.UserRepository;
import com.riwi.LibrosYa.infrastructure.abstracts.IUserService;
import com.riwi.LibrosYa.infrastructure.mappers.UserMapper;
import com.riwi.LibrosYa.infrastructure.persistence.UserEntity;
import com.riwi.LibrosYa.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public Page<UserResponse> getAll(int size, int page) {
        if (page < 0) {
        page = 0;
        }
        Pageable pageable = PageRequest.of(page, size);

       return this.userRepository.findAll(pageable).map(user -> userMapper.userToUserResponse(userMapper.userEntityToUser(user)));
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userMapper.userEntityToUser(findUser(id));
        UserResponse userResponse = userMapper.userToUserResponse(user);

        
        return userResponse;
    }

    private UserEntity findUser(Long id) {
    return this.userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("users"));
    }

    @Override
    public UserResponse create(UserRequest request) {
        User user = userMapper.userRequestToUser(request);

        return userMapper.userToUserResponse(userMapper.userEntityToUser(this.userRepository.save(userMapper.userToUserEntity(user))));
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = userMapper.userEntityToUser(findUser(id));

        user = userMapper.userRequestToUser(request);

        return userMapper.userToUserResponse(userMapper.userEntityToUser(this.userRepository.save(userMapper.userToUserEntity(user))));
    }

    @Override
    public void delete(Long id) {
        UserEntity userEntity = findUser(id);

        this.userRepository.delete(userEntity);
    }
    
}
