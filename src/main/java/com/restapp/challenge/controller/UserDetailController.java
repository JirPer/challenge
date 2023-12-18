package com.restapp.challenge.controller;

import com.restapp.challenge.dto.UserDetailDTO;
import com.restapp.challenge.entity.UserDetail;
import com.restapp.challenge.service.UserDetailService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserDetailController {

  @Autowired
  private UserDetailService userDetailService;

  @GetMapping
  public ResponseEntity<List<UserDetailDTO>> getAllUsers() {
    return ResponseEntity.ok(userDetailService.findAllUsers());
  }
  @GetMapping("/{id}")
  public ResponseEntity<UserDetailDTO> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userDetailService.findUserById(id));
  }

  @PostMapping
  public ResponseEntity<UserDetailDTO> createUser(@Valid @RequestBody UserDetail userDetail) {
   return new ResponseEntity<>(userDetailService.createUser(userDetail), HttpStatus.CREATED);
  }

}
