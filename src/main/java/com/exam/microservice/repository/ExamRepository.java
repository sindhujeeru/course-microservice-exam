package com.exam.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.common.exam.microservice.entity.Exam;

public interface ExamRepository extends PagingAndSortingRepository<Exam, Long> {
	
	@Query("select e from Exam e where e.name like %?1%")
	public List<Exam> findByName(String term);
	
	@Query("select e.id from Question q join q.exam e where q.id in ?1 group by e.id")
	public Iterable<Long> findExamIdsWithResponsesByQuestionIds(Iterable<Long> questionIds);
}
