package com.ame.mongo;

import com.ame.mongo.domain.Address;
import com.ame.mongo.domain.Gender;
import com.ame.mongo.domain.Student;
import com.ame.mongo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {

            mongoTemplate.dropCollection("student");
            mongoTemplate.createCollection("student");

            List<Student> students = new ArrayList<>();
            Address address = new Address("Malaysia", "KL", "58100");
            String email = "jahmed@mail.to";
            Student student = new Student(
                    "Jamila",
                    "Ahmed",
                    email,
                    Gender.FEMALE,
                    address,
                    List.of("Comp Sc", "Database", "Networking"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
            students.add(student);

            address = new Address("England", "Manchester", "NE98");
            email = "mamadou@mail.to";
            student = new Student(
                    "Amad",
                    "Diallo",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Sports", "Football", "Nutrition"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
            students.add(student);

            for (Student s : students) {
                // useMongoTemplate(repository, mongoTemplate, s.getEmail(), s);
                repository.findStudentByEmail(s.getEmail()).ifPresentOrElse(st -> {
                    log.info("Student " + st.getId() + " already exists");
                }, () -> {
                    log.info("Insert student with name: " + s.getFirstName() + " " + s.getLastName());
                    repository.insert(s);
                });
            }
        };
    }

    private void useMongoTemplate(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalStateException("Found many students with email " + email);
        }

        if (students.isEmpty()) {
            log.info("Insert student with id: " + student.getId());
            repository.insert(student);
        } else {
            log.info("Student " + student.getId() + "already exists");
        }
    }
}
