package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAO;
import board.dto.BoardCommentDTO;
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
		
		if(url.indexOf("list.do") != -1) 
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
					request.getContextPath() + "/board_servlet/list.do";
			response.sendRedirect(page);
		}
		else if(url.indexOf("download.do") != -1) 
		{
			int num = Integer.parseInt(request.getParameter("num"));
			String fileName = dao.getFileName(num);
			// fileName = new String(fileName.getBytes("utf-8"), "8859_1");
			System.out.println(fileName);
			
			// 파일 다운로드 처리
			String path = Constants.UPLOAD_PATH + fileName;	// 파일의 위치
			byte b[] = new byte[4096]; 
			// 파일을 읽기 위한 입력 스트림
			FileInputStream fis = new FileInputStream(path);
			// 파일의 종류(마임타입)
			String mimeType = getServletContext().getMimeType(path);
			if(mimeType == null) 
			{ // 다운로드할 파일의 형식
				mimeType = "application/octet-stream;charset=utf-8";
			}
			
			// 무엇이 전송되는지 명시해주는 헤더 전송, attachment(첨부파일)
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			
			// body 전송
			// 출력 스트림 생성
			ServletOutputStream out = response.getOutputStream();
			int numRead;
			while(true)
			{
				numRead =fis.read(b, 0, b.length); // 파일을 읽음
				if(numRead == -1) break; // 내용이 있으면
				out.write(b, 0, numRead); // 파일저장
			}
			out.flush(); // 스트림 비우기
			out.close(); // 출력스트림 닫기
			fis.close(); // 입력스트림 닫기
			
			dao.plusDown(numRead);
		}
		else if(url.indexOf("view.do") != -1) 
		{
			int num = Integer.parseInt(request.getParameter("num"));
			// 조회수 증가 처리
			dao.plusReadCount(num, request.getSession());
			
			// view 페이지의 경우 줄바꿈 처리를 해줘야 하기때문에 true 를 전달
			BoardDTO dto = dao.view(num, true);
			System.out.println("상세화면 dto" + dto);
			// 출력을 위해 request 영역에 저장
			request.setAttribute("dto", dto);
			//출력 페이지로 이동
			String page = "/board/board_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}
		else if(url.indexOf("pass_check.do") != -1) 
		{
			int num = Integer.parseInt(request.getParameter("num"));
			String password = request.getParameter("password");
			// 올바른 비밀번호인지 확인
			String result = dao.passwordCheck(num, password);
			System.out.println("비밀번호 체크결과" + result);
			String page = new String();
			// 비번이 맞으면 수정 화면으로 이동
			if(result != null) 
			{
				page = "/board/board_edit.jsp";
				// edit 페이지의 경우 줄바꿈 처리가 필요없음으로 false 를 전달
				request.setAttribute("dto", dao.view(num, false));
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
			else 
			{
				page = request.getContextPath() + 
						"/board_servlet/view.do?num="+num+"&message=error";
				response.sendRedirect(page);
			}
			
			// 비번이 틀리면 되돌아감
		}
		else if(url.indexOf("update.do") != -1) 
		{
			// 폼에서 입력한 값을 dto에 저장
			BoardDTO dto = new BoardDTO();
			
			// 첨부파일처리를 위한 MutipartRequest 선언
			MultipartRequest multi = new MultipartRequest(
					request, 
					Constants.UPLOAD_PATH, 
					Constants.MAX_UPLOAD, "utf-8",
					new DefaultFileRenamePolicy());
			
			String fileName = new String();
			int fileSize = 0;
			try 
			{
				// 첨부파일이 여러개일수도 있으니 첨부파일 집합  
				Enumeration files = multi.getFileNames(); 
				while (files.hasMoreElements()) 
				{
					// 첨부파일의 이름을 불러옵니다.
					String file1 = (String) files.nextElement();
					// 시스템 파일 이름을 불러옵니다.
					fileName = multi.getFilesystemName(file1);
					// 해당 파일의 사이즈를 구합니다.
					File f1 = multi.getFile(file1);
					// 첨부파일의 size
					if(f1 != null) 
					{
						fileSize = (int)f1.length();
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			// reqest -> multi로 변경
			int num = Integer.parseInt(multi.getParameter("num"));
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String password = multi.getParameter("password");
			String ip = request.getRemoteAddr(); // 클라이언트의 ip주소
			
			dto.setNum(num);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPassword(password);
			dto.setIp(ip);
			
			/*
			 *	기존 첨부파일이 있을 때
			 *		파일 삭제 체크 여부
			 *		새로운 첨부파일이 있을 때 (교체)
			 *		새로운 첨부파일이 없을 때
			 *	
			 *	// 기존 첨푸파일 추가는 문제가 없음
			 *	기존 첨부파일이 없을 때
			 *		새로운 첨부파일이 있을 때
			 *		새로운 첨부파일이 없을 때
			 * */
			if(fileName == null || fileName.trim().equals(""))
			{	// 새로운 첨부파일이 없을 때(기존의 테이블 정보를 가져옴)
				BoardDTO dto2 = dao.view(num, false);
				String fName = dto2.getFilename();
				int fSize = dto2.getFilesize();
				int fDown = dto2.getDown();
				dto.setFilename(fName);
				dto.setFilesize(fSize);
				dto.setDown(fDown);
			} 
			else 
			{	// 새로운 첨부파일이 있을 때 
				dto.setFilename(fileName);
				dto.setFilesize(fileSize);
			}
			
			//첨부파일 삭제 처리
			String fileDel = multi.getParameter("fileDel");
			// 체크가 되지 않으면 null, 체크되면 on
			if(fileDel != null && fileDel.equals("on")) 
			{
				// 테이블에 저장된 파일 이름
				String fileName_d = dao.getFileName(num);
				File f = new File(Constants.UPLOAD_PATH + fileName);
				f.delete(); // 파일 삭제
				
				// 첨부파일 관련 레코드 정보 수정
				dto.setFilename("-");
				dto.setFilesize(0);
				dto.setDown(0);
			}
			
			// 
			String result = dao.passwordCheck(num, password);
			if(result != null) 
			{ // 비밀번호가 맞을 경우
				// dao에 update 요청
				dao.update(dto);
				// list.do로 이동
				String page = request.getContextPath() + "/board_servlet/list.do";
				response.sendRedirect(page);
			} 
			else 
			{
				request.setAttribute("dto", dto);
				String page = "/board/board_edit.jsp?password_error=y";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		}
		else if(url.indexOf("delete.do") != -1)
		{
			// enctype="multipart/form-data" 로 넘어온 값은 
			// request 객체로 받을 수 없다.
			MultipartRequest multi = new MultipartRequest(
					request, 
					Constants.UPLOAD_PATH, 
					Constants.MAX_UPLOAD, "utf-8",
					new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("num"));
			dao.delete(num);
			// DB 테이블 칼럽 추가 
			// alter table board add shows char(1) default('y');
			// 이럴경우 데이터를 완전 삭제가 아닌 shows 값을 n 으로 변경함으로 view 상에는 보이진 않지만
			// 데이터는 존재하는 형태로 진행합니다.
			
			String page = new String();
			page = request.getContextPath() + 
					"/board_servlet/list.do";
			response.sendRedirect(page);
		}
		else if(url.indexOf("commentList.do") != -1)
		{
			int num = Integer.parseInt(request.getParameter("num"));
			
			// 댓글 목록이 list로 넘어옴
			List<BoardCommentDTO> list = dao.commentList(num);
			// 출력 페이지에서 읽을 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			
			// 화면 전환
			String page = "/board/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		else if(url.indexOf("commentAdd.do") != -1)
		{
			BoardCommentDTO dto = new BoardCommentDTO();
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			dto.setBoard_num(board_num);
			dto.setWriter(writer);
			dto.setContent(content);
			
			System.out.println(dto);
		
			// 레코드가 추가됨
			dao.commentAdd(dto);
			// 실행이 끝나면 view.jsp의 콜백함수(success)로 넘어감
		}
		else if(url.indexOf("reply.do") != -1) 
		{
			// 게시물 번호 조회
			int num = Integer.parseInt(request.getParameter("num"));
			// 게시물 내용을 dto로 받음
			BoardDTO dto = dao.view(num, false);
			// 답변 작성의 편의를 위해 reply.jsp 페이지에 dto를 전달
			dto.setContent("---게시물의 내용---\n" + dto.getContent());
			request.setAttribute("dto", dto);
			String page = "/board/reply.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		else if(url.indexOf("insertReply.do") != -1) 
		{
			int num = Integer.parseInt(request.getParameter("num"));
			// 원글 내용
			BoardDTO dto = dao.view(num, false);
			int ref = dto.getref(); // 답변 그룹번호
			int re_step = dto.getRe_step() + 1; // 출력 순번
			int re_level = dto.getRe_level() + 1; // 답변 단계
			
			// 답변 내용
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String password = request.getParameter("password");
			
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPassword(password);
			dto.setref(ref);
			dto.setRe_level(re_level);
			dto.setRe_step(re_step);
			// 첨부파일 관련
			dto.setFilename("-");
			dto.setFilesize(0);
			dto.setDown(0);
			// 답글 순서 조정
			dao.updateStep(ref, re_step);
			// 답글 쓰기
			dao.reply(dto);
			// 목록으로 이동
			String page = "/board_servlet/list.do";
			response.sendRedirect(request.getContextPath() + page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
