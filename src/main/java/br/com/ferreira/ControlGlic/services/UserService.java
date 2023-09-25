package br.com.ferreira.ControlGlic.services;

import br.com.ferreira.ControlGlic.dtos.user.CreateUserRequestDto;
import br.com.ferreira.ControlGlic.dtos.user.UpdateUserRequestDto;
import br.com.ferreira.ControlGlic.entities.User;
import br.com.ferreira.ControlGlic.entities.exceptions.ValidationException;
import br.com.ferreira.ControlGlic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new ValidationException("User with the email provided already exists");

        return userRepository.save(user);
    }

    public User updateUser(UpdateUserRequestDto userRequestDto, String id) {
        User user = userRepository.findById(id).get();
        //Verificar diferente de nulo

        user.setName(userRequestDto.name());
        return userRepository.save(user);
    }


}
