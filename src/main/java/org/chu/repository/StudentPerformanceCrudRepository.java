package org.chu.repository;

import org.chu.model.Discipline;
import org.chu.model.Student;
import org.chu.model.StudentPerformance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPerformanceCrudRepository extends CrudRepository<StudentPerformance, Long> {

    StudentPerformance findByStudentAndDiscipline(Student student, Discipline discipline);
}
