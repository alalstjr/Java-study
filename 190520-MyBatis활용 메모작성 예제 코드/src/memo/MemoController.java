package memo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.dao.MemoDAO;
import memo.dto.MemoDTO;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemoController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		System.out.println(url);
		
		MemoDAO dao = new MemoDAO();
		
		if(url.indexOf("list.do") != -1) {
			List<MemoDTO> list = dao.listMemo(); // 메모 리스트 받음
			request.setAttribute("list", list); // request 영역에 저장
			
			// 페이지 이동(포워딩)
			String page = "/page/memo_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("insert.do") != -1) {
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			
			MemoDTO dto = new MemoDTO(writer, memo);
			dao.insertMemo(dto);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
