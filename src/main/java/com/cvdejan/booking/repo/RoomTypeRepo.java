package com.cvdejan.booking.repo;

import com.cvdejan.booking.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {

    @Query("select rt.maxPersons from RoomType rt where rt.price=?1")
    Optional<Integer> findRoom(Float price);


}