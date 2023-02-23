package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.service.AdminQuestionService;
import com.exam.service.AdminQuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/question")
public class AdminQuestionController {

	@Autowired
	private AdminQuestionService adminQuestionService;

	@Autowired
	private AdminQuizService adminQuizService;

	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody Question question) {
		return ResponseEntity.ok(this.adminQuestionService.addQuestion(question));
	}

	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.ok(this.adminQuestionService.updateQuestion(question));
	}

//	@GetMapping("/quiz/{qid}")
//	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {
//		System.out.println(qid);
//		Quiz quiz = this.adminQuizService.getQuiz(qid);
//		Set<Question> questions = quiz.getQuestions();
//		List list = new ArrayList(questions);
//		System.out.println("get number of questions"+quiz.getNumberOfQuestion());
//		if (quiz.getNumberOfQuestion() != null && list.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
//			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion() + 1));
//		}
//		Collections.shuffle(list);
//		return ResponseEntity.ok(list);
//	}

	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionOfQuizadmin(@PathVariable("qid") Long qid) {
		Quiz quiz = new Quiz();
		quiz.setqId(qid);
		Set<Question> questionOfQuiz = this.adminQuestionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);

	}

	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {
		return this.adminQuestionService.getQuestion(quesId);
	}

	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId) {
		this.adminQuestionService.deleteQuestion(quesId);
	}

	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
		System.out.println(questions);
		double markGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		for (Question q : questions) {
			Question question = this.adminQuestionService.get(q.getQuesId());
			if (question.getAnswer().trim().equals(q.getGivenAnswer().trim())) {
				correctAnswers++;
				double markSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
				markGot+=markSingle;
			}

			if (q.getGivenAnswer() == null || q.getGivenAnswer().trim().equals("")) {
				attempted++;
			}
		}
		return ResponseEntity.ok("Got Question with answer ");
	}
}
