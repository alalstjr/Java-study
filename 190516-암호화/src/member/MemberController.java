package member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿 매핑을 member_servlet 경로 로 오는 모든것을 이곳에서 처리하겠다.
@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청한 URL
		String url = request.getRequestURL().toString();
		System.out.println(url);
		
		// 컨텍스트 패스
		String context = request.getContextPath();
		
		MemberDAO dao = new MemberDAO();
		
		if (url.indexOf("join_bcrypt.do") != -1) {
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPassword(password);
			dto.setName(name);
			dao.insertBcrypt(dto);
			
		} else if (url.indexOf("login_bcrypt.do") != -1) {
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPassword(password);
			String result = dao.loginCheckBcrypt(dto);
			
			request.setAttribute("result", result);
			String page = "/page/login_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
