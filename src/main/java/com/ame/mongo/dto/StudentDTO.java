package com.ame.mongo.dto;

import com.ame.mongo.domain.Address;
import com.ame.mongo.domain.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;
}
