package com.cvdejan.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Length(message = "Hotel name must be between 2 and 100 chars", min = 2, max = 100)
    @Pattern(message = "Hotel name must contains alphabets only", regexp = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")
    @Column(nullable = false)
    String hotelName;

    @Length(message = "Hotel Location name must be between 2 and 100 chars", min = 2, max = 100)
    @Pattern(message = "Hotel Location must contains alphabets only", regexp = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")
    @Column(nullable = false)
    String hotelLocation;

/*    @Length(message = "Hotel Image name must be between 2 and 100 chars", min = 2, max = 100)
    @Column(nullable = false)
    String hotelImage;*/


    @Length(message = "Hotel Description name must be between 10 and 255 chars", min = 10, max = 255)
    @Column(nullable = false)
    String hotelDescription;

    //booking mapping
    @JsonIgnore
    @OneToMany(mappedBy = "hotel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Booking> bookings=new HashSet<>();

    @Lob
    private byte[] hotelImage;

    //@Type(type="org.hibernate.type.BlobType")
}