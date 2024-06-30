package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Repository.IUserRepository;
import com.arcticnode.crm.Services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public UserEntity save(UserEntity user) {
        return iUserRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return iUserRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public void deleteById(Integer userId) {
        iUserRepository.findById(userId);
        if (userId != 0){
            iUserRepository.deleteById(userId);
        }

    }

    @Override //to-do
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.empty();
    }
}
