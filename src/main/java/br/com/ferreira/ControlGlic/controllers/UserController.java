package br.com.ferreira.ControlGlic.controllers;

import br.com.ferreira.ControlGlic.dtos.user.CreateUserRequestDto;
import br.com.ferreira.ControlGlic.dtos.user.CreateUserResponseDto;
import br.com.ferreira.ControlGlic.dtos.user.UpdateUserRequestDto;
import br.com.ferreira.ControlGlic.entities.User;
import br.com.ferreira.ControlGlic.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid CreateUserRequestDto userRequestDto, UriComponentsBuilder uriBuilder) {
        User user = userService.createUser(userRequestDto);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getUuid()).toUri();

        return ResponseEntity.created(uri).body(new CreateUserResponseDto(user));
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public String updateUser(@PathVariable String id, @RequestBody UpdateUserRequestDto userRequestDto) {
        System.out.println(id);
        userService.updateUser(userRequestDto, id);
        return "Update user!";
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable String id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }

}
