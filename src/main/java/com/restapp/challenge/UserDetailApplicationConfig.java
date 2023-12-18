package com.restapp.challenge;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDetailApplicationConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


}
