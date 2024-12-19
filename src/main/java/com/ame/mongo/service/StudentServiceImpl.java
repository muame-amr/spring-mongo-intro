package com.ame.mongo.service;

import com.ame.mongo.dto.StudentDTO;
import com.ame.mongo.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(student -> objectMapper.convertValue(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        return null;
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public StudentDTO update(Long id, StudentDTO studentDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
