package br.com.ferreira.ControlGlic.controllers;

import br.com.ferreira.ControlGlic.dtos.CreateUserRequestDto;
import br.com.ferreira.ControlGlic.dtos.UpdateUserRequestDto;
import br.com.ferreira.ControlGlic.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public String createUser(@RequestBody CreateUserRequestDto userRequestDto) {
        System.out.println(userRequestDto.name());
        userService.createUser(userRequestDto);
        return "Create user!";
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public String updateUser(@PathVariable String id, @RequestBody UpdateUserRequestDto userRequestDto) {
        System.out.println(id);
        userService.updateUser(userRequestDto, id);
        return "Update user!";
    }

    @GetMapping
    public String getAll() {
        return "Get All users!";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable String id) {
        return "Get user by id: " + id;
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public String deleteUser(@PathVariable String id) {
        return "Delete user by id: " + id;
    }

}
