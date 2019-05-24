package paging;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paging.dao.MemoDAO;
import paging.dto.MemoDTO;

@WebServlet("/page_servlet/*")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		MemoDAO dao = new MemoDAO();
		
		// 페이지번호
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		// 시작번호 = (현재페이지 - 1) * 페이지당 게시물수 + 1
		// 끝번호 = 시작번호 + (페이지당 게시물수 - 1)
		int start = (curPage - 1) * 10+1;
		int end = 10;
		List<MemoDTO> list = dao.memoList(start, end);
		
		// list.jsp에서 출력할 수 있도록 데이터 저장
		request.setAttribute("list", list);
		
		// 페이지 갯수를 계산하려면? 991/10 > 99.1 > 올림 > 100
		// 전체레코드갯수 / 10 > 올림처리
		// Math.ceil(실수값) > 올림함수
		int rows= dao.memoCount();
		int total_page = (int)(Math.ceil((rows) / 10.0));
		
		//--- 페이징 이전 다음 페이지 블록단위 코드 ---//		
		
		// 블록당 표시할 페이지의 갯수
		int page_list_size = 10;
		
		// 페이지 블록의 갯수 계산(전체 페이지 갯수 / 블록당페이지갯수)
		int total_block = (int)Math.ceil(total_page * 1.0 / page_list_size);
		
		// 현재 페이지가 몇번째 블록에 속하는지 계산
		//(현재페이지번호 - 1) / 블록당 페이지갯수 + 1
		int current_block = (int)Math.ceil((curPage - 1) / page_list_size)+1;
		
		// n번째 블록의 시작페이지 번호, 끝페이지 번호 계산
		int block_start = (current_block-1)*page_list_size + 1;
		int block_end = block_start + page_list_size - 1;
		
		// 이전 페이지 번호, 다음 페이지 번호
		int prev_page = current_block == 1 ? 1 : (current_block-1) * page_list_size;
		int next_page = current_block > total_block ?
				(current_block*page_list_size) : (current_block*page_list_size) + 1; 
		
		// 마지막 블록의 끝번호가 범위를 초과하지 않도록 처리
		if(block_end > total_page) {
			block_end = total_page;
		}
		
		// 마지막 페이지가 범위를 초과하지 않도록 처리
		if(next_page >= total_page) {
			next_page = total_page;
		}
		// 페이지 네비게이션에 필요한 변수들을 request 영역에 저장
		request.setAttribute("curPage", curPage); // 현재페이지 번호	
		request.setAttribute("total_page", total_page); // 총페이지	
		request.setAttribute("total_block", total_block); // 블록 갯수	
		request.setAttribute("current_block", current_block); //현재 블록
		request.setAttribute("block_start", block_start); // 시작페이지번호
		request.setAttribute("block_end", block_end); // 끝페이지번호
		request.setAttribute("prev_page", prev_page); // 이전페이지 번호
		request.setAttribute("next_page", next_page); // 다음페이지 번호
		
		// list.jsp로 넘어가서 출력
		String page = "/paging/memo_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
