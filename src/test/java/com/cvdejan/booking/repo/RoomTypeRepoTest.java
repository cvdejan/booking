package com.cvdejan.booking.repo;

import com.cvdejan.booking.model.RoomType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
class RoomTypeRepoTest {
    @Autowired
    private RoomTypeRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findMaxPersonsinRoomByPrice() {
        //given
        RoomType roomType=new RoomType(null,"Standard Double Room",100F,0.1F,2,LocalTime.of(12,0),LocalTime.of(11,30),null);
        Float price=100F;
        underTest.save(roomType);

        //when
        Integer returnedResult = underTest.findRoom(price).orElseThrow(()->new RuntimeException("Room not found"));
        Integer expectedResult=2;

        //then
        assertThat(returnedResult).isEqualTo(expectedResult);
    }

   @Test
    void notFindMaxPersonsinRoomByPrice() {
        //given
        Float price=90F;
        //when

        //then
        assertThatThrownBy(() -> {
            underTest.findRoom(price).orElseThrow(()->new RuntimeException("Room not found"));
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Room");
    }
}