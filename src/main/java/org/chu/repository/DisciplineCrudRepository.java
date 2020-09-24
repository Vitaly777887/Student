package org.chu.repository;

import org.chu.model.Discipline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineCrudRepository extends CrudRepository<Discipline, Long> {
    Discipline findByName(String name);

}
