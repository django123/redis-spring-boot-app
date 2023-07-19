package com.django.redis_spring_boot_app.rest;

import com.django.redis_spring_boot_app.model.UserEntityDTO;
import com.django.redis_spring_boot_app.service.UserEntityService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/userEntitys", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserEntityResource {

    private final UserEntityService userEntityService;

    public UserEntityResource(final UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntityDTO>> getAllUserEntitys() {
        return ResponseEntity.ok(userEntityService.findAll());
    }

    @Cacheable(value="users", key="#id", unless = "#result.followers < 12000")
    @GetMapping("/{id}")
    public ResponseEntity<UserEntityDTO> getUserEntity(@PathVariable(name = "id") final Long id) {
        log.info("getting with ID {}.", id);
        return ResponseEntity.ok(userEntityService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUserEntity(
            @RequestBody @Valid final UserEntityDTO userEntityDTO) {
        final Long createdId = userEntityService.create(userEntityDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @CachePut(value = "users", key ="#userEntityDTO.id")
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserEntity(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final UserEntityDTO userEntityDTO) {
        userEntityService.update(id, userEntityDTO);
        return ResponseEntity.ok(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUserEntity(@PathVariable(name = "id") final Long id) {
        userEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
