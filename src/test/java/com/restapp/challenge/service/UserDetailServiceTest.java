package com.restapp.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.restapp.challenge.entity.UserDetail;
import com.restapp.challenge.exception.ApiException400;
import com.restapp.challenge.exception.ApiException404;
import com.restapp.challenge.repository.UserDetailRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceTest {

  @Mock
  private UserDetailRepository userRepositoryMock;
  @MockBean
  private ModelMapper modelMapper;
  private UserDetailService userDetailService;
  private final UserDetail user = new UserDetail(1L, "ghost", "xcvxvc", "Jack", "Sparrow");

  @BeforeEach
  public void setUp() {
    modelMapper = new ModelMapper();
    userRepositoryMock = mock(UserDetailRepository.class);
    userDetailService = new UserDetailServiceImpl(userRepositoryMock, modelMapper);
  }

  @Test
  void canGetAllUsers() {
    //when
    userDetailService.findAllUsers();

    //then
    verify(userRepositoryMock).findAll();
  }

  @Test
  void canGetUserById() {
    //given
    given(userRepositoryMock.findById(user.getId())).willReturn(Optional.of(user));

    //when
    userDetailService.findUserById(user.getId());

    //then
    verify(userRepositoryMock).findById(user.getId());
  }

  @Test
  void canCreateUser() {
    //when
    userDetailService.createUser(user);

    ArgumentCaptor<UserDetail> userDetailArgumentCaptor = ArgumentCaptor.forClass(UserDetail.class);
    verify(userRepositoryMock).save(userDetailArgumentCaptor.capture());
    UserDetail capturedUser = userDetailArgumentCaptor.getValue();

    //then
    assertEquals(user, capturedUser);
  }

  @Test
  void throwsApiException404WhenUserDoesNotExist() {
    //given
    //when
    given(userRepositoryMock.findById(2L)).willReturn(Optional.empty());

    //then
    assertThrows(ApiException404.class, () -> userDetailService.findUserById(2L));
  }

  @Test
  void throwsApiException400WhenUserAlreadyExists() {
    //given
    //when
    given(userRepositoryMock.findByUsername(user.getUsername())).willReturn(Optional.of(user));

    //then
    assertThrows(ApiException400.class, () -> userDetailService.createUser(user));
  }
}
