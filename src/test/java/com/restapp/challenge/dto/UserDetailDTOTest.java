package com.restapp.challenge.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.restapp.challenge.entity.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class UserDetailDTOTest {

  private ModelMapper modelMapper;
  @BeforeEach
  void setUp() {
    this.modelMapper = new ModelMapper();
  }

  @Test
  void canMapUserToDTO() {
    //when
    UserDetail user = new UserDetail(1L, "abcde", "ghost", "Jack", "Sparrow");
    UserDetailDTO userDetailDTO = modelMapper.map(user, UserDetailDTO.class);

    //then
    assertEquals(user.getGivenName(), userDetailDTO.getGivenName());
    assertEquals(user.getUsername(), userDetailDTO.getUsername());
    assertEquals(user.getLastName(), userDetailDTO.getLastName());
  }
}
