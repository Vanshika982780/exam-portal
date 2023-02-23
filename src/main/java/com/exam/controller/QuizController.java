package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

//	@PostMapping("/")
//	public ResponseEntity<?> add(@RequestBody Quiz quiz) {
//		this.quizService.addQuiz(quiz);
//		return ResponseEntity.ok("Saved");
//	}
//
//	@PutMapping("/")
//	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
//		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
//	}

	@GetMapping("/")
	public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}

	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return this.quizService.getQuiz(qid);
	}

//	@DeleteMapping("/{qid}")
//	public void delete(@PathVariable("qid") Long qid) {
//		this.quizService.deleteQuiz(qid);
//	}

	@GetMapping("/cid/{cid}")
	public List<Quiz> getQuizzesCategory(@PathVariable("cid") Long cid) {
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesOfCategory(category);
	}
//
//	@GetMapping("/active")
//	public List<Quiz> getActiveQuizzes() {
//		return this.quizService.getActiveQuizzes();
//	}

//	@GetMapping("/category/active/{cid}")
//	public List<Quiz> getActiveQuizzes(@PathVariable("cid") long cid) {
//		Category category = new Category();
//		category.setCid(cid);
//		return this.quizService.getActiveQuizzesOfCategory(category);
//	}
}
