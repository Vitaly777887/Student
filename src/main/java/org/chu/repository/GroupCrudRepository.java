package org.chu.repository;

import org.chu.model.StudentGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCrudRepository extends CrudRepository<StudentGroup, Long> {

    StudentGroup findByName(String name);
}
