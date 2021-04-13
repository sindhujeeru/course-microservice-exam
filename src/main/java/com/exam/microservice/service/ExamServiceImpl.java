package com.exam.microservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exam.microservice.entity.Exam;
import com.common.exam.microservice.entity.Subject;
import com.common.microservice.service.CommonServiceImpl;
import com.exam.microservice.repository.ExamRepository;
import com.exam.microservice.repository.SubjectRepository;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository>implements ExamService{
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	@Transactional
	public List<Exam> findByName(String term) {
		return repository.findByName(term);
	}

	@Override
	@Transactional
	public Iterable<Subject> findAllSubjects() {
		return (List<Subject>) subjectRepository.findAll();
	}

	@Override
	@Transactional
	public Iterable<Long> findExamIdsWithResponsesByQuestionIds(Iterable<Long> questionIds) {
		return repository.findExamIdsWithResponsesByQuestionIds(questionIds);
	}

}
