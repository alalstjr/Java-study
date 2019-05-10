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
		
		if(url.indexOf("list.do") != -1) {	// list 를 요청한 경우
			
			// Member의 각각의 정보를 list에 담습니다. 
			List<MemberDTO> list = dao.memberList();
			
			// Member의 정보 list를 map에 한번에 묶어서 넣습니다.
			Map<String, Object> map = new HashMap<String, Object>();
			
			// map 에 저장합니다.
			map.put("list", list);
			map.put("count", list.size());
			request.setAttribute("map", map);
			System.out.println(map);
			
			// Member List를 출력하여 보여주기 위해서 map 값을 member_list 로 전송시킵니다.  
			String page = "/page/member_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} 
		  else if(url.indexOf("join.do") != -1) 
		{
		  String userid = request.getParameter("userid");
		  String password = request.getParameter("password");
		  String name = request.getParameter("name");
		  String address = request.getParameter("address");
		  String tel = request.getParameter("tel"); 
					
		  MemberDTO dto = new MemberDTO(userid, password, name, address, tel);
		  System.out.println("join.do, dto:" + dto);
		  int rows = dao.insert(dto);
		  System.out.println("추가된 레코드수:" + rows);
		}
		  else if(url.indexOf("view.do") != -1) 
		{
		  String userid = request.getParameter("userid");
		  System.out.println("view.do, userid:" + userid);
					
		  MemberDTO dto = dao.memberDetail(userid);
		  request.setAttribute("dto", dto);
		  String page = "/page/member_view.jsp";
		  RequestDispatcher rd = request.getRequestDispatcher(page);
		  rd.forward(request, response);
		} 
		  else if(url.indexOf("update.do") != -1) 
		{
		  String userid = request.getParameter("userid");
		  String password = request.getParameter("password");
		  String name = request.getParameter("name");
		  String address = request.getParameter("address");
		  String tel = request.getParameter("tel");
					
		  MemberDTO dto = new MemberDTO(userid, password, name, address, tel);
					
		  dao.update(dto);
		  response.sendRedirect(context + "/page/member.jsp");
		} else if(url.indexOf("delete.do") != -1) {
		  String userid = request.getParameter("userid");
		  dao.delete(userid);
		  response.sendRedirect(context + "/page/member.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
