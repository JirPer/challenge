package com.restapp.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.restapp.challenge.entity.UserDetail;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserDetailRepositoryTest {

  @Autowired
  private UserDetailRepository userRepository;

  @Test
  void checkIfUserCanBeFoundByUsername() {
    //given
    UserDetail user = new UserDetail(1L, "abcde", "ghost", "Jack", "Sparrow");
    userRepository.save(user);

    //when
    Optional<UserDetail> userOptional = userRepository.findByUsername(user.getUsername());

    //then
    assertTrue(userOptional.isPresent());
  }
}
