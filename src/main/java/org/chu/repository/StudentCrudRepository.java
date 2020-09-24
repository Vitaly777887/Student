package org.chu.repository;

import org.chu.model.EducationForm;
import org.chu.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCrudRepository extends CrudRepository<Student, Long> {

    long countAllByEducationForm(EducationForm educationForm);

    Student findByName(String name);
}
