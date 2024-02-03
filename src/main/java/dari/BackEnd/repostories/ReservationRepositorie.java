package dari.BackEnd.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dari.BackEnd.entites.Reservation;

@Repository
public interface ReservationRepositorie extends JpaRepository<Reservation, Integer> {

}
