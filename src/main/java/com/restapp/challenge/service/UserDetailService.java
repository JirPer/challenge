package com.restapp.challenge.service;

import com.restapp.challenge.dto.UserDetailDTO;
import com.restapp.challenge.entity.UserDetail;
import java.util.List;

public interface UserDetailService {

  List<UserDetailDTO> findAllUsers();
  UserDetailDTO createUser(UserDetail userDetail);
  UserDetailDTO findUserById(Long id);
}
