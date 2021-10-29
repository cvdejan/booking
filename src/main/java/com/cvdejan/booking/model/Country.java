package com.cvdejan.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Length(message = "The country name must be between 2 and 100 chars", min = 2, max = 100)
    @Pattern(message = "The country must contains alphabets only", regexp = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")
    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    //Booking mapping
    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Booking> bookings=new HashSet<>();



}