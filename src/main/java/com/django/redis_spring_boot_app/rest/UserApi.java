package com.django.redis_spring_boot_app.rest;

import com.django.redis_spring_boot_app.model.UserEntityDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="user")
public interface UserApi {

    @GetMapping
    public ResponseEntity<List<UserEntityDTO>> getAllUserEntitys();

    @Cacheable(value="users", key="#id", unless = "#result.followers < 12000")
    @GetMapping("/{id}")
     ResponseEntity<UserEntityDTO> getUserEntity(@PathVariable(name = "id") final Long id);

    @PostMapping
    @ApiResponse(responseCode = "201")
    ResponseEntity<Long> createUserEntity(@RequestBody @Valid final UserEntityDTO userEntityDTO);

    @CachePut(value = "users", key ="#userEntityDTO.id")
    @PutMapping("/{id}")
    ResponseEntity<Long> updateUserEntity(@PathVariable(name = "id") final Long id, @RequestBody @Valid final UserEntityDTO userEntityDTO);

    @CacheEvict(value = "users", allEntries = true)
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    ResponseEntity<Void> deleteUserEntity(@PathVariable(name = "id") final Long id);
}
