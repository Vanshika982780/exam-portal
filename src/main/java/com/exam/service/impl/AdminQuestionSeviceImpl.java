package com.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.repo.AdminQuestionRepository;
import com.exam.service.AdminQuestionService;

@Service
public class AdminQuestionSeviceImpl implements AdminQuestionService {

	@Autowired
	private AdminQuestionRepository adminQuestionRepository;

	@Override
	public Question addQuestion(Question question) {
		return this.adminQuestionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.adminQuestionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.adminQuestionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.adminQuestionRepository.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionOfQuiz(Quiz quiz) {
		return this.adminQuestionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		Question question = new Question();
		question.setQuesId(quesId);
		this.adminQuestionRepository.delete(question);

	}

	@Override
	public Question get(Long questionId) {
		return this.adminQuestionRepository.getOne(questionId);
	}

}
