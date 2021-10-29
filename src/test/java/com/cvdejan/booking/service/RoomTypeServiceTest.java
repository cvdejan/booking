package com.cvdejan.booking.service;

import com.cvdejan.booking.exception.ResourceNotFoundException;
import com.cvdejan.booking.model.RoomType;
import com.cvdejan.booking.repo.RoomTypeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoomTypeServiceTest {

    @Mock
    private RoomTypeRepo roomTypeRepo;
    private RoomTypeService underTest;

    @BeforeEach
    void setUp() {
        underTest=new RoomTypeService(roomTypeRepo);
    }

    @Test
    void canAddRoomType() {
        //given
        RoomType roomType=new RoomType(null,"Standard Double Room",100F,0.1F,2, LocalTime.of(12,0),LocalTime.of(11,30),null);
        //when
        underTest.addRoomType(roomType);
        //then
        ArgumentCaptor<RoomType> roomTypeArgumentCaptor=ArgumentCaptor.forClass(RoomType.class);
        verify(roomTypeRepo).save(roomTypeArgumentCaptor.capture());
        RoomType capturedRoomType = roomTypeArgumentCaptor.getValue();
        assertThat(capturedRoomType).isEqualTo(roomType);

    }

    @Test
    void canGetAllRoomTypes() {
        //when
        underTest.getAllRoomTypes();
        //then
        verify(roomTypeRepo).findAll();
    }

    @Test
    @Disabled
    void addRoomTypes() {
    }

    @Test
    void findRoomType() {
        //given
        Long roomId=5L;
        //
        //RoomType roomType=new RoomType(null,"Standard Double Room",100F,0.1F,2, LocalTime.of(12,0),LocalTime.of(11,30),null);


        //given(roomTypeRepo.findRoom(100F)).willReturn(java.util.Optional.of(2));

        //then
        assertThatThrownBy(()->underTest.findRoomType(roomId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Room not found");

        //verify(roomTypeRepo,never()).save(any());
    }
}