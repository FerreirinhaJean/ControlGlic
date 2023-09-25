package br.com.ferreira.ControlGlic.services;

import br.com.ferreira.ControlGlic.dtos.user.CreateUserRequestDto;
import br.com.ferreira.ControlGlic.dtos.user.GetUsersResponseDto;
import br.com.ferreira.ControlGlic.dtos.user.UpdateUserRequestDto;
import br.com.ferreira.ControlGlic.entities.User;
import br.com.ferreira.ControlGlic.entities.exceptions.ValidationException;
import br.com.ferreira.ControlGlic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    public List<GetUsersResponseDto> getAllUsers() {
        List<GetUsersResponseDto> allUsersResponseDtos = new ArrayList<>();
        List<User> allUsers = userRepository.findAllByIsActive(true);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (User user : allUsers) {
            allUsersResponseDtos.add(new GetUsersResponseDto(
                    user.getUuid(),
                    user.getName(),
                    user.getEmail(),
                    simpleDateFormat.format(user.getBirthDate())
            ));
        }

        return allUsersResponseDtos;
    }

    public GetUsersResponseDto getUserById(String uuid) {
        User user = userRepository.getReferenceById(uuid);
        if (!user.getActive())
            throw new ValidationException("User has been deactivated");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return new GetUsersResponseDto(user.getUuid(), user.getName(), user.getEmail(), simpleDateFormat.format(user.getBirthDate()));
    }

    public void deactivateUser(String uuid) {
        User user = userRepository.getReferenceById(uuid);
        if (!user.getActive())
            throw new ValidationException("User has been deactivated");

        user.setActive(false);
    }

    public User updateUser(UpdateUserRequestDto userRequestDto, String id) {
        User user = userRepository.getReferenceById(id);
        user.setName(userRequestDto.name());
        user.setEmail(userRequestDto.email());
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            user.setBirthDate(simpleDateFormat.parse(userRequestDto.birthDate()));
        } catch (ParseException parseException) {
            throw new RuntimeException("Error converting date");
        }

        return user;
    }


}
