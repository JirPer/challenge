package com.restapp.challenge.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapp.challenge.dto.UserDetailDTO;
import com.restapp.challenge.entity.UserDetail;
import com.restapp.challenge.service.UserDetailService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserDetailController.class)
public class UserDetailControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private UserDetailService userDetailService;
  @MockBean
  private ModelMapper modelMapper;

  @BeforeEach
  void setUp() {
    modelMapper = new ModelMapper();
  }

  private final UserDetail user = new UserDetail(1L, "abcde", "ghost", "Jack", "Sparrow");

  @Test
  void canGetAllUsers() throws Exception {
    //given
    List<UserDetailDTO> listOfUsers = new ArrayList<>();
    listOfUsers.add(modelMapper.map(user, UserDetailDTO.class));
    given(userDetailService.findAllUsers()).willReturn(listOfUsers);

    //when
    ResultActions resultActions = mockMvc.perform(get("/users"));

    //then
    resultActions.andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.size()").value(listOfUsers.size()));
  }

  @Test
  void canGetUserById() throws Exception {
    //given
    given(userDetailService.findUserById(user.getId())).willReturn(modelMapper.map(user, UserDetailDTO.class));

    //when
    ResultActions resultActions = mockMvc.perform(get("/users/{id}", user.getId()));

    //then
    resultActions.andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.username").value(user.getUsername()))
        .andExpect(jsonPath("$.givenName").value(user.getGivenName()))
        .andExpect(jsonPath("$.lastName").value(user.getLastName()));
  }

  @Test
  void canCreateUser() throws Exception {
    //given
    given(userDetailService.createUser(user)).willReturn(modelMapper.map(user, UserDetailDTO.class));

    //when
    ResultActions resultActions = mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(user)));

    //then
    resultActions.andDo(print())
        .andExpect(status().isCreated());
  }
}
