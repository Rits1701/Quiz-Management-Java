package fr.epita.quiz.web.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.AddQuestionDAO;
import fr.epita.quiz.web.actions.SpringServlet;

/**
 * 
 * @author Bhrigu
 *
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/questionList")
public class QuestionList extends SpringServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(QuestionList.class);

	@Autowired
	private AddQuestionDAO repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Question> questionsList = (List<Question>) repository.searchAll(new Question());
			request.getSession().setAttribute("questionsList", questionsList);
			LOGGER.info("List retrieved Sucessfully");
			response.sendRedirect("questionList.jsp");
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
	}

}
