package com.cvdejan.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String roomTypeName;

    @Column(nullable = false)
    Float price=0f;

    @Column(nullable = false)
    Float vat=0f;

    @Column(nullable = false)
    Integer maxPersons=0;

    @Column(nullable = false)
    LocalTime checkIn=LocalTime.now();

    @Column(nullable = false)
    LocalTime checkOut=LocalTime.now();

    //Booking mapping
    @JsonIgnore
    @OneToMany(mappedBy = "roomType", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Booking> bookings;

}