package com.afirma.test.bookings.repository;

import com.afirma.test.bookings.repository.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    Integer countByNumber(String number);
    Optional<Room> findById(Long id);
}
