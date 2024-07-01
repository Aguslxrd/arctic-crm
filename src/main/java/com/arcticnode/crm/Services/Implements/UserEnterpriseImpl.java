package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import com.arcticnode.crm.Repository.IUserEnterpriseRepository;
import com.arcticnode.crm.Services.IUserEnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEnterpriseImpl implements IUserEnterpriseService {

    @Autowired
    private IUserEnterpriseRepository iUserEnterpriseRepository;


    @Override
    public UserEnterpriseEntity save(UserEnterpriseEntity userEnterpriseEntity) {
        return iUserEnterpriseRepository.save(userEnterpriseEntity);
    }

    @Override
    public List<UserEnterpriseEntity> findByIdUserId(Integer id) {
        return iUserEnterpriseRepository.findByIdUserId(id);
    }

    @Override
    public List<UserEnterpriseEntity> findByIdEnterpriseId(Integer enterpriseId) {
        return iUserEnterpriseRepository.findByIdEnterpriseId(enterpriseId);
    }

    @Override
    public void deleteByIdUserIdAndIdEnterpriseId(Integer userId, Integer enterpriseId) {
        iUserEnterpriseRepository.deleteByIdUserIdAndIdEnterpriseId(userId, enterpriseId);
    }

    @Override
    public boolean existsByIdUserIdAndIdEnterpriseId(Integer userId, Integer enterpriseId) {
        return iUserEnterpriseRepository.existsByIdUserIdAndIdEnterpriseId(userId, enterpriseId);
    }
}
