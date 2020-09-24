package org.chu;

import org.chu.model.*;
import org.chu.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Учет успеваемости студентов
 * В объектно-ориентированной системе должны присутствовать данные
 * о контингенте студентов (фамилия, имя, отчество, год поступления, форма
 * обучения (дневная/вечерняя/заочная), номер или название группы;
 * об учебном плане (название специальности, дисциплина, семестр, количество отводимых на дисциплину часов, форма отчетности (экзамен/зачет));
 * о журнале успеваемости студентов (год/семестр, студент, дисциплина, оценка).
 */
@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(scripts = "classpath:scripts/clean.sql"),
        @Sql(scripts = "classpath:scripts/group_populate.sql"),
        @Sql(scripts = "classpath:scripts/student_populate.sql"),
        @Sql(scripts = "classpath:scripts/specially_populate.sql"),
        @Sql(scripts = "classpath:scripts/discipline_populate.sql"),
        @Sql(scripts = "classpath:scripts/syllabus_populate.sql"),
        @Sql(scripts = "classpath:scripts/student_performance_populate.sql"),

})
public class StudentApplicationTests {

    @Autowired
    private StudentCrudRepository studentCrudRepository;
    @Autowired
    private DisciplineCrudRepository disciplineCrudRepository;
    @Autowired
    private SyllabusCrudRepository syllabusCrudRepository;
    @Autowired
    private GroupCrudRepository groupCrudRepository;
    @Autowired
    private SpeciallyCrudRepository speciallyCrudRepository;
    @Autowired
    private StudentPerformanceCrudRepository studentPerformanceCrudRepository;

    /**
     * для указанной формы обучения посчитать количество студентов
     * этой формы обучения;
     */
    @Test
    public void test1() {
        assertEquals(2L, studentCrudRepository.countAllByEducationForm(EducationForm.FULL_TIME));
    }

    /**
     * для указанной дисциплины получить количество часов и формы
     * отчетности по этой дисциплине;
     */
    @Test
    public void test2() {

        Discipline discipline = disciplineCrudRepository.findByName("АНГЛ");
        int countHours = discipline.getSyllabuses().stream().mapToInt(Syllabus::getCountHours).sum();
        assertEquals(240, countHours);
    }

    /**
     * предоставить возможность добавления и изменения информации о
     * студентах, об учебных планах, о журнале;
     */
    @Test
    public void testCrudStudent() {
        StudentGroup studentGroup = groupCrudRepository.findByName("МПОИС");
        studentCrudRepository.save(new Student("Glovanov", "Andrey", "Sergeevich", 2020, EducationForm.DISTANCE, studentGroup));
        assertEquals("Andrey", studentCrudRepository.findByName("Andrey").getName());

        Student student = studentCrudRepository.findByName("Andrey");
        student.setYearAdmission(2024);
        studentCrudRepository.save(student);
        assertEquals(2024, studentCrudRepository.findByName("Andrey").getYearAdmission());

    }

    @Test
    public void testCrudSyllabus() {
        Specially specially = speciallyCrudRepository.findByName("Бизнес-информатика");
        Discipline discipline = disciplineCrudRepository.findByName("ММОПР");
        Syllabus syllabus = syllabusCrudRepository.save(new Syllabus(specially, discipline, 3, 120, ReportingForm.EXAM));
        assertEquals(3, syllabusCrudRepository.findBySpeciallyAndDisciplineAndReportingForm(specially, discipline, ReportingForm.EXAM).getSemester());

        syllabus.setSemester(2);
        syllabusCrudRepository.save(syllabus);
        assertEquals(2, syllabusCrudRepository.findBySpeciallyAndDisciplineAndReportingForm(specially, discipline, ReportingForm.EXAM).getSemester());

    }

    @Test
    public void testCrudStudentPerformance() {
        Student student = studentCrudRepository.findByName("Kolya");
        Discipline discipline = disciplineCrudRepository.findByName("ММОПР");
        StudentPerformance studentPerformance = studentPerformanceCrudRepository.save(new StudentPerformance(2, 2021, student, discipline, 4));
        assertEquals(2021, studentPerformanceCrudRepository.findByStudentAndDiscipline(student, discipline).getYear());

        studentPerformance.setYear(2025);
        studentPerformanceCrudRepository.save(studentPerformance);
        assertEquals(2025, studentPerformanceCrudRepository.findByStudentAndDiscipline(student, discipline).getYear());

    }
}
