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
			// 검색 옵션, 검색 키워드
			String searchkey = request.getParameter("searchkey");
			String search = request.getParameter("search");
			
			List<MemoDTO> list = dao.listMemo(searchkey, search); // 메모 리스트 받음
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
		} else if(url.indexOf("view.do") != -1) {
			// request parameter 받아오기
			int num = Integer.parseInt(request.getParameter("num"));
			
			// dao method 호출
			MemoDTO dto = dao.viewMemo(num);
			
			// forwarding
			request.setAttribute("dto", dto);
			String page = "/page/memo_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
					
		} else if(url.indexOf("update.do") != -1) {
			int num = Integer.parseInt(request.getParameter("num"));
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			MemoDTO dto = new MemoDTO();
			dto.setNum(num);
			dto.setWriter(writer);
			dto.setMemo(memo);
			
			// 레코드 수정 처리
			dao.updateMemo(dto);
			// 페이지 이동
			response.sendRedirect(
					request.getContextPath()+"/page/memo.jsp");
		} else if(url.indexOf("delete.do") != -1) {
			int num = Integer.parseInt(request.getParameter("num"));
			dao.deleteMemo(num);
			
			// ajax 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/page/memo.jsp");
		} else if(url.indexOf("delete_all.do") != -1) {
			String[] num = request.getParameterValues("num");
			
			System.out.println(num);
			
			if(num != null) {
				for(int i=0; i<num.length; i++) {
					dao.deleteMemo(Integer.parseInt(num[i]));
				}
			}
		
			// ajax 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/page/memo.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
