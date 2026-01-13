package br.com.Portifolio.Room.Reservation.repository;

import br.com.Portifolio.Room.Reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(String cpf);

    User findByCpf(String cpf);
}
