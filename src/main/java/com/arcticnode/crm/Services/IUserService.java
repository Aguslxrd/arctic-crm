package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public UserEntity save(UserEntity user);
    @Transactional(readOnly = true)
    public UserEntity findById(Integer id);
    @Transactional(readOnly = true)
    public List<UserEntity> findAll();
    public void deleteById(Integer newsId);

    public Optional<UserEntity> findByEmail(String email);

    //find by dni/ci/rut/etc...
}
