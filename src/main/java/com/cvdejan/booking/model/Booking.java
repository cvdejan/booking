package com.cvdejan.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    LocalDateTime checkIn=LocalDateTime.now();
    @Column(nullable = false)
    LocalDateTime checkOut=LocalDateTime.now();
    @Column(nullable = false)
    Integer adults=0;
    @Column(nullable = false)
    Integer children=0;
    @Column(nullable = false)
    String customerName;
    @Column(nullable = false)
    Date dob;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Gender gender;
    @Column(nullable = false)
    String phone;
    @Column(nullable = false)
    String status;
    @Column(nullable = false)
    String guest1Name;
    @Column(nullable = false)
    String guest1Age;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Gender guest1Gender;

    String guest2Name;

    String guest2Age;

    @Enumerated(EnumType.STRING)
    Gender guest2Gender;
    @Column(nullable = false)
    Boolean extraBed=false;
    @Column(nullable = false)
    Boolean isExpanded=false;

    @ElementCollection
    @CollectionTable(name = "booking_dine_in", joinColumns = @JoinColumn(name = "owner_id"))
    @Column(name = "dine_in")
    private List<Boolean> dineIn=new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "booking_foods", joinColumns = @JoinColumn(name = "owner_id"))
    @Column(name = "food")
    private List<String> foods=new ArrayList<>();

    @Column(name="city_id", nullable = false)
    private Integer cityId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id", updatable = false, insertable = false)
    private City city;

    @Column(name="hotel_id", nullable = false)
    private Integer hotelId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", updatable = false, insertable = false)
    private Hotel hotel;

    @Column(name="country_id", nullable = false)
    private Integer countryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", updatable = false, insertable = false)
    private Country country;

    @Column(name="room_type_id", nullable = false)
    private Integer roomTypeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_type_id", updatable = false, insertable = false)
    private RoomType roomType;

}
