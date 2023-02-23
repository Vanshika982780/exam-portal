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
import com.exam.service.AdminQuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/quiz")
public class AdminQuizController {

	@Autowired
	private AdminQuizService adminQuizService;

	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.adminQuizService.addQuiz(quiz));
	}

	@PutMapping("/")
	public ResponseEntity<? > update(@RequestBody Quiz quiz) {
		this.adminQuizService.updateQuiz(quiz);
		return ResponseEntity.ok("update");
	}

	@GetMapping("/")
	public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(this.adminQuizService.getQuizzes());
	}

	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return this.adminQuizService.getQuiz(qid);
	}

	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid) {
		this.adminQuizService.deleteQuiz(qid);
	}

	public List<Quiz> getQuizzrsCategory(@PathVariable("cid") Long cid) {
		Category category = new Category();
		category.setCid(cid);
		return this.adminQuizService.getQuizzesOfCategory(category);
	}

	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes() {
		return this.adminQuizService.getActiveQuizzes();
	}

	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzes(@PathVariable("cid") long cid) {
		Category category = new Category();
		category.setCid(cid);
		return this.adminQuizService.getActiveQuizzesOfCategory(category);
	}
}
