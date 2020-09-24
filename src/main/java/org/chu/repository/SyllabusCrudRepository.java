package org.chu.repository;

import org.chu.model.Discipline;
import org.chu.model.ReportingForm;
import org.chu.model.Specially;
import org.chu.model.Syllabus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusCrudRepository extends CrudRepository<Syllabus, Long> {

    Syllabus findBySpeciallyAndDisciplineAndReportingForm(Specially specially, Discipline discipline, ReportingForm reportingForm);
}
