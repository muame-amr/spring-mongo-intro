package com.ame.mongo.service;

import com.ame.mongo.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();

    StudentDTO findById(Long id);

    StudentDTO create(StudentDTO studentDTO);

    StudentDTO update(Long id, StudentDTO studentDTO);

    void delete(Long id);
}
