package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Repository.IUserRepository;
import com.arcticnode.crm.Services.IUserService;
import jakarta.persistence.EntityNotFoundException;
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
    public List<UserEntity> findAllBySoftDeleteFalse() {
        return iUserRepository.findAllBySoftDeleteFalse();
    }

    @Override
    public void deleteById(Integer userId) {
        iUserRepository.findById(userId);
        if (userId != 0){
            iUserRepository.deleteById(userId);
        }

    }

    @Override
    public void softDeleteById(Integer userId) {
        iUserRepository.findById(userId).ifPresent(user -> {
            user.setSoftDelete(true);
            iUserRepository.save(user);
        });
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(iUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email)));
    }

    @Override
    public Optional<UserEntity> findByPhone(String phone) {
        return Optional.ofNullable(iUserRepository.findByPhone(phone)
                .orElseThrow(() -> new EntityNotFoundException("User not found with phone: " + phone)));
    }

    @Override
    public Optional<UserEntity> findByIdentifier(String identifier) {
        return Optional.ofNullable(iUserRepository.findByIdentifier(identifier)
                .orElseThrow( () -> new EntityNotFoundException("User not found with identifier: " + identifier)));
    }


}
