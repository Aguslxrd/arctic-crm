package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.CaseStatus;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Repository.IUserRepository;
import com.arcticnode.crm.Services.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<UserEntity> findAllBySoftDeleteFalse(Pageable pageable) {
        return iUserRepository.findAllBySoftDeleteFalse(pageable);
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

    @Override
    public Page<UserEntity> findAllSoftDeletedUsers(Pageable pageable) {
        List<UserEntity> allUsers = iUserRepository.findAll();

        List<UserEntity> filteredUsers = allUsers.stream()
                .filter(userEntity -> Boolean.TRUE.equals(userEntity.getSoftDelete()))
                .collect(Collectors.toList());

        //indice para el inicio y el final de cada pagina
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()), filteredUsers.size());

        //sublista para la página actual
        List<UserEntity> pagedUsers = filteredUsers.subList(start, end);

        return new PageImpl<>(pagedUsers, pageable, filteredUsers.size());
    }

    @Override
    public Long countAllUsers() {
        return iUserRepository.findAll().stream()
                .filter(user -> user.getSoftDelete() == Boolean.FALSE)
                .count();
    }


}
