package com.exam.microservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.common.exam.microservice.entity.Subject;

public interface SubjectRepository extends PagingAndSortingRepository<Subject, Long>{
	
	
}
