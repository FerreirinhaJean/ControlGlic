package br.com.ferreira.ControlGlic.services;

import br.com.ferreira.ControlGlic.dtos.CreateUserRequestDto;
import br.com.ferreira.ControlGlic.dtos.UpdateUserRequestDto;
import br.com.ferreira.ControlGlic.entities.User;
import br.com.ferreira.ControlGlic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User convertedUser(CreateUserRequestDto userRequestDto) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            var user = new User(
                    userRequestDto.name(),
                    userRequestDto.email(),
                    simpleDateFormat.parse(userRequestDto.birthDate()),
                    userRequestDto.password(),
                    true
            );
            return user;
        } catch (ParseException e) {
            throw new RuntimeException("Error for parse birthDate!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User createUser(CreateUserRequestDto userRequestDto) {
        User user = convertedUser(userRequestDto);
        return userRepository.save(user);
    }

    public User updateUser(UpdateUserRequestDto userRequestDto, String id) {
        User user = userRepository.findById(id).get();
        //Verificar diferente de nulo

        user.setName(userRequestDto.name());
        return userRepository.save(user);
    }


}
