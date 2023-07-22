package com.django.redis_spring_boot_app.mappers;

import com.django.redis_spring_boot_app.domain.UserEntity;
import com.django.redis_spring_boot_app.model.UserEntityDTO;
import org.mapstruct.Mapper;

@Mapper(uses={UserMapper.class})
public interface UserMapper {

    UserEntityDTO mapToUserEntityDto(UserEntity userEntity);
    UserEntity mapToUserEntity(UserEntityDTO userEntityDTO);
}
