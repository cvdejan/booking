package com.cvdejan.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Length(message = "City name must be between 2 and 100 chars", min = 2, max = 100)
    @Pattern(message = "The City must contains alphabets only", regexp = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")
    @Column(name = "city_name", nullable = false, unique = true)
    private String cityName;

    //Booking mapping
    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Booking> bookings=new HashSet<>();

}