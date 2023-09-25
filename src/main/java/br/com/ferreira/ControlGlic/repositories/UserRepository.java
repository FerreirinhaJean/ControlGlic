package br.com.ferreira.ControlGlic.repositories;

import br.com.ferreira.ControlGlic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

}
