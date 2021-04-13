package com.exam.microservice.service;

import java.util.List;

import com.common.exam.microservice.entity.Exam;
import com.common.exam.microservice.entity.Subject;
import com.common.microservice.service.CommonService;

public interface ExamService extends CommonService<Exam> {
	
	public List<Exam> findByName(String term);
	
	public Iterable<Subject> findAllSubjects();
	
	public Iterable<Long> findExamIdsWithResponsesByQuestionIds(Iterable<Long> questionIds);
}
