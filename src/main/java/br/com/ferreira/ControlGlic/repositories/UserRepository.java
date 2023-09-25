package br.com.ferreira.ControlGlic.repositories;

import br.com.ferreira.ControlGlic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    List<User> findAllByIsActive(Boolean isActive);

}
