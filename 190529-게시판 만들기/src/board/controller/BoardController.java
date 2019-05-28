package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import common.Constants;

@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		BoardDAO dao = new BoardDAO();
		
		if(url.indexOf("board_list.do") != -1) 
		{
			List<BoardDTO> list = dao.list(); // 게시물 목록이 넘어옴
			// 출력페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 출력 페이지로 이동
			String page = "/board/board_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		else if(url.indexOf("insert.do") != -1) 
		{
			BoardDTO dto = new BoardDTO();
			File uploadDir = new File(common.Constants.UPLOAD_PATH);
			if(!uploadDir.exists()) 
			{ // 디렉토리가 없으면
				uploadDir.mkdir(); // 디렉토리를 생성함
			}
			
			// request 객체를 확장한 MultipartRequest 객체 생성
			// DefaultFileRenamePolicy 파일이름이 중복되면 자동처리
			MultipartRequest multi = new MultipartRequest(
					request, 
					Constants.UPLOAD_PATH, 
					Constants.MAX_UPLOAD, "utf-8",
					new DefaultFileRenamePolicy());
			String fileName = "";
			int fileSize = 0;
			try 
			{
				Enumeration files = multi.getFileNames(); // 파일 업로드 여러개 처리를 도와줌
				while(files.hasMoreElements()) 
				{ // 다음 파일이 있으면
					String file1 = (String)files.nextElement(); // 다음 파일
					fileName = multi.getFilesystemName(file1); // 파일 이름
					File f1 =multi.getFile(file1);
					
					if(f1 != null) 
					{
						fileSize = (int)f1.length(); // 파일의 크기
						// File 클레스의 length메서드의 리턴값은 long 이기때문에 int형으로 형변환을 시켜줍니다.
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			// MultipartRequest 객체를 생성하는 순간 기존에 사용하던 request 는 사용할 수 없습니다.
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String password = multi.getParameter("password");
			String ip = request.getRemoteAddr(); // 클라이언트의 ip주소
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPassword(password);
			dto.setIp(ip);
			
			// 첨부파일이 없을 경우 처리
			if(fileName == null || fileName.equals(""))
			{
				fileName="-";
			}
			dto.setFilename(fileName);
			dto.setFilesize(fileSize);
			System.out.println(dto);

			dao.insert(dto); // 레코드가 추가됨
			// 게시물 목록 갱신
			String page = 
					request.getContextPath() + "/board_servlet/board_list.do";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
