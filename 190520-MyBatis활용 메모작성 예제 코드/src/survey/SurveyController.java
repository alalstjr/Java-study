package survey;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import survey.dao.SurveyDAO;
import survey.dto.SurveyDTO;
import survey.dto.SurveyResultDTO;
import survey.dto.SurveySummaryDTO;

@WebServlet("/survey_servlet/*")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SurveyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String url = request.getRequestURI();
		SurveyDAO dao = new SurveyDAO();
		
		if(url.indexOf("input.do") != -1) {
			SurveyDTO dto = dao.viewQuestion(1); // 1번 문제 리턴
			request.setAttribute("dto", dto); // request 영역에 저장
			
			// 페이지 이동(포워딩)
			String page = "/survey/survey_input.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("insert.do") != -1) {
			// 문제번호
			int survey_num = Integer.parseInt(request.getParameter("survey_num"));
			// 응답번호
			int num = Integer.parseInt(request.getParameter("num"));
			
			// 응답내용을 저장할 dto 생성
			SurveyResultDTO dto = new SurveyResultDTO();
			dto.setSurvey_um(survey_num);
			dto.setNum(num);
			
			//응답내용을 테이블에 저장
			dao.insertSurvey(dto);
			response.sendRedirect(path + "/survey_servlet/survey_result.do?survey_num=2");
			
		} else if(url.indexOf("survey_result.do") != -1) {
			System.out.println("asd");
			int survey_num = Integer.parseInt(request.getParameter("survey_num"));
			List<SurveySummaryDTO> items = dao.listSummary(survey_num);
			request.setAttribute("list", items);
			
			// 페이지 이동(포워딩)
			String page = "/survey/survey_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
