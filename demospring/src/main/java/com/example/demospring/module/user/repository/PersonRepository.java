package com.example.demospring.module.user.repository;

import com.example.demospring.module.user.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName：PersonRepository
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-18 11:33
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
	List<Person> findByName(String name);
}
