package org.chu.repository;

import org.chu.model.Specially;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciallyCrudRepository extends CrudRepository<Specially, Long> {

    Specially findByName(String name);
}
