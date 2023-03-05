package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Location;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {


    //@Query("select l.id FROM Location as l WHERE l.lat = :lat and l.lon = :lot")
    Optional<Location> findByLatAndLon(float lat, float lot);
}
