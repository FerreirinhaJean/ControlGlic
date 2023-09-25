package br.com.ferreira.ControlGlic.entities;

import br.com.ferreira.ControlGlic.dtos.user.CreateUserRequestDto;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    private String name;
    private String email;
    private Date birthDate;
    //    private String password;
    private Date updateAt;
    private Boolean isActive;

    public User() {

    }

    public User(String name, String email, Date birthDate, Boolean isActive) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
//        this.password = password;
        this.isActive = isActive;
    }

    public User(CreateUserRequestDto userRequestDto) {
        try {
            this.name = userRequestDto.name();
            this.email = userRequestDto.email();
            this.isActive = true;
//            this.password = userRequestDto.password();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            this.birthDate = simpleDateFormat.parse(userRequestDto.birthDate());
        } catch (ParseException parseException) {
            throw new RuntimeException("Error converting date");
        } catch (Exception exception) {
            throw new RuntimeException("Error creating new user.\n" + exception.getMessage());
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
