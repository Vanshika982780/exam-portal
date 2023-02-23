package com.exam.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.repo.AdminQuizRepository;
import com.exam.service.AdminQuizService;

@Service
public class AdminQuizServiceImpl implements AdminQuizService {
	@Autowired
	private AdminQuizRepository adminQuizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		return this.adminQuizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return this.adminQuizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		return new HashSet<>(this.adminQuizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return this.adminQuizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		Quiz quiz = new Quiz();
		quiz.setqId(quizId);
		this.adminQuizRepository.delete(quiz);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		return this.adminQuizRepository.findBycategory(category);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		return this.adminQuizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category c) {
		return this.adminQuizRepository.findByCategoryAndActive(c, true);
	}

}