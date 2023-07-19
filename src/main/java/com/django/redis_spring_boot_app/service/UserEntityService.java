package com.django.redis_spring_boot_app.service;

import com.django.redis_spring_boot_app.domain.UserEntity;
import com.django.redis_spring_boot_app.model.UserEntityDTO;
import com.django.redis_spring_boot_app.repos.UserEntityRepository;
import com.django.redis_spring_boot_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityService(final UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public List<UserEntityDTO> findAll() {
        final List<UserEntity> userEntitys = userEntityRepository.findAll(Sort.by("id"));
        return userEntitys.stream()
                .map(userEntity -> mapToDTO(userEntity, new UserEntityDTO()))
                .toList();
    }

    public UserEntityDTO get(final Long id) {
        return userEntityRepository.findById(id)
                .map(userEntity -> mapToDTO(userEntity, new UserEntityDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserEntityDTO userEntityDTO) {
        final UserEntity userEntity = new UserEntity();
        mapToEntity(userEntityDTO, userEntity);
        return userEntityRepository.save(userEntity).getId();
    }

    public void update(final Long id, final UserEntityDTO userEntityDTO) {
        final UserEntity userEntity = userEntityRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userEntityDTO, userEntity);
        userEntityRepository.save(userEntity);
    }

    public void delete(final Long id) {
        userEntityRepository.deleteById(id);
    }

    private UserEntityDTO mapToDTO(final UserEntity userEntity, final UserEntityDTO userEntityDTO) {
        userEntityDTO.setId(userEntity.getId());
        userEntityDTO.setName(userEntity.getName());
        userEntityDTO.setFollowers(userEntity.getFollowers());
        return userEntityDTO;
    }

    private UserEntity mapToEntity(final UserEntityDTO userEntityDTO, final UserEntity userEntity) {
        userEntity.setName(userEntityDTO.getName());
        userEntity.setFollowers(userEntityDTO.getFollowers());
        return userEntity;
    }

}
