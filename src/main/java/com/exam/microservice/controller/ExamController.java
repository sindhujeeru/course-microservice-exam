package com.exam.microservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.exam.microservice.entity.Exam;
import com.common.exam.microservice.entity.Question;
import com.common.microservice.controller.CommonController;
import com.exam.microservice.service.ExamService;


@RestController
@RequestMapping("api/exams")
public class ExamController extends CommonController<Exam, ExamService>{
	
	@GetMapping("/response-values-for-questions")
	public ResponseEntity<?> getExamIdsByQuestionIdsResponseValues(@RequestParam List<Long> questionIds){
		return ResponseEntity.ok().body(service.findExamIdsWithResponsesByQuestionIds(questionIds));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editData(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable("id") Long id){
		
		
		if(result.hasErrors()) {
			this.validate(result);
		}
		
		Optional<Exam> ex = service.findById(id);
		
		if(ex.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Exam examDb = ex.get();
		
		
		  List<Question> questions = new ArrayList<>();
		
		    
		  examDb.getQuestions().forEach(queDb->{
		  if(!exam.getQuestions().contains(queDb)) { questions.add(queDb); } });
		  
		  questions.forEach(examDb::removeQuestion);
		 
		
		/*List<Question> questions = examDb.getQuestions()
		.stream()
		.filter(que -> !exam.getQuestions().contains(que))
		.collect(Collectors.toList());
		
		questions.forEach(examDb::removeQuestion);*/
		
		examDb.setName(exam.getName());
		examDb.setQuestions(exam.getQuestions());
		examDb.setSubjectChild(exam.getSubjectChild());
		examDb.setSubjectParent(exam.getSubjectParent());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examDb));
	}
	
	@GetMapping("/search/{term}")
	public ResponseEntity<?> searchForExam(@PathVariable("term") String term){
		
		return ResponseEntity.ok(service.findByName(term));
	}
	
	
	@GetMapping("/subjects")
	public ResponseEntity<?> listSubjects(){
		
		return ResponseEntity.ok(service.findAllSubjects());
	}


	

}
