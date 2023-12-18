package com.restapp.challenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotEmpty
  private String password;
  @Size(min = 3, message = "must be at least 3 chars long")
  private String username;
  @Size(min = 3, message = "must be at least 3 chars long")
  private String givenName;
  @Size(min = 3, message = "must be at least 3 chars long")
  private String lastName;

}
