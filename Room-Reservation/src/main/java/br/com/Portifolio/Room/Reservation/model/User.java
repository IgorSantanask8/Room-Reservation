package br.com.Portifolio.Room.Reservation.model;

import br.com.Portifolio.Room.Reservation.dto.UserDto;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //Name of the user
    private String cpf;// id of the user in BR
    private Integer age; //The age, only adults can make reservations
    private String cityOrigin; //adress is important
    private Integer escorts; //how many people the user can take

    @OneToOne
    private Reservation reservation;

    public User(){}

    public User(UserDto dto){ //Use Data transfer Object
        this.name = dto.name();
        this.cpf = dto.cpf();
        this.age = dto.age();
        this.cityOrigin = dto.cityOrigin();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCityOrigin() {
        return cityOrigin;
    }

    public void setCityOrigin(String cityOrigin) {
        this.cityOrigin = cityOrigin;
    }

    public Integer getEscorts() {
        return escorts;
    }

    public void setEscorts(Integer escorts) {
        this.escorts = escorts;
    }
}
