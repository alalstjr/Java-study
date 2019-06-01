package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import sqlmap.MyBatisManager;

public class BoardDAO {
	
	// 게시물 목록 리턴
	public List<BoardDTO> list() 
	{
		List<BoardDTO> list = null;
		SqlSession session = null;
		
		// try ~ catch 문을 활용합니다.
		try 
		{
			session = MyBatisManager.getInstance().openSession();
			list = session.selectList("board.List");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null) session.close(); // SqlSession 종료
		}
		
		return list;
	}
	
	// 게시물 저장
	public void insert(BoardDTO dto) 
	{
		SqlSession session = null;
		
		try 
		{
			session = MyBatisManager.getInstance().openSession();
			session.insert("board.insert", dto);
			session.commit();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null) session.close(); // SqlSession 종료
		}
	}
	
	// 첨부파일
	public String getFileName(int num) 
	{
		String result = new String();
		SqlSession session = null;
		
		try 
		{
			// myvatis 실행 객체 생성
			session = MyBatisManager.getInstance().openSession();
			// 게시물 번호에 해당하는 첨부파일 이름을 리턴
			result = session.selectOne("board.getFileName", num);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null) session.close(); // SqlSession 종료
		}
		return result;
	}
	
	// 다운로드 횟수 증가
	public void plusDown(int num) 
	{
		SqlSession session = null;
		try {
			session = MyBatisManager.getInstance().openSession();
			session.update("board.plusDown", num);
			session.commit();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			if(session != null) session.close();
		}
	}
	
	// 조회수 증가
	public void plusReadCount(int num, HttpSession count_session) 
	{
		SqlSession session = null;
		try 
		{
			long read_time = 0; //게시물을 읽은 시간
			
			// 세션이 존재하면 읽은 시간을 가져옴
			if(count_session.getAttribute("read_time_"+num) != null) 
			{
				read_time = (long)count_session.getAttribute("read_time_"+num);
			}
			long current_time = System.currentTimeMillis(); // 현재시각
			session = MyBatisManager.getInstance().openSession();
			// 일정시간이 경과하면 조회수를 올림
			if(current_time - read_time > 5*1000) 
			{
				session.update("board.plusReadCount", num);
				session.commit();
				count_session.setAttribute("read_time_"+num, current_time);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null) session.close();
		}
	}
	
	// 상세화면 dto 리턴 코드
	public BoardDTO view(int num, boolean newline) {
		SqlSession session = null;
		BoardDTO dto = null;
		try {
			session = MyBatisManager.getInstance().openSession();
			dto = session.selectOne("board.view", num);
			
			// 줄바꿈 처리 
			if(newline == true) {
				String content = dto.getContent();
				content = content.replace("\n", "<br/>");
				dto.setContent(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return dto;
	}
	
	// 게시물 비밀번호 체크	
	public String passwordCheck(int num, String password) 
	{
		String result = null;
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("num", num);
			map.put("password", password);
			result = session.selectOne("board.pass_check", map);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null) session.close();
		}
		return result;
	}
	
	// 게시물 수정
	public void update(BoardDTO dto) 
	{
		SqlSession session = null;
		try 
		{
			session = MyBatisManager.getInstance().openSession();
			session.update("board.update", dto);
			session.commit();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null) session.close();
		}
	}
	
	// 게시물 삭제/숨김
	public void delete(int num)
	{
		SqlSession session = null;
		try 
		{
			session = MyBatisManager.getInstance().openSession();
			session.update("board.delete", num);
			session.commit();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null) session.close();
		}
	}
	
	// 댓글 목록 리턴
	public List<BoardCommentDTO> commentList(int num) 
	{
		List<BoardCommentDTO> list = null;
		SqlSession session = null;
		try 
		{ 
			session = MyBatisManager.getInstance().openSession();
			list = session.selectList("board.commentList", num);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if(session != null) session.close();
		}
		
		return list;
	}
}
