package com.restapp.challenge.service;

import com.restapp.challenge.dto.UserDetailDTO;
import com.restapp.challenge.entity.UserDetail;
import com.restapp.challenge.exception.ApiException400;
import com.restapp.challenge.exception.ApiException404;
import com.restapp.challenge.exception.ErrorCause;
import com.restapp.challenge.repository.UserDetailRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

  @Autowired
  private UserDetailRepository userRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<UserDetailDTO> findAllUsers() {
    return userRepository.findAll().stream()
        .map(userDetail -> modelMapper.map(userDetail, UserDetailDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserDetailDTO findUserById(Long id) {
    final UserDetail user = userRepository.findById(id).orElseThrow(
        () -> new ApiException404(String.format("user with id: %s was not found", id),
            ErrorCause.ENTITY_NOT_FOUND));

    return modelMapper.map(user, UserDetailDTO.class);
  }

  @Override
  @Transactional
  public UserDetailDTO createUser(UserDetail userDetail) {
    Optional<UserDetail> userOptional = userRepository.findByUsername(userDetail.getUsername());
    if (userOptional.isPresent()) {
      throw new ApiException400(
          String.format("user with username: %s already exists", userDetail.getUsername()),
          ErrorCause.ENTITY_ALREADY_EXISTS);
    }
    userRepository.save(userDetail);

    return modelMapper.map(userDetail, UserDetailDTO.class);
  }
}
