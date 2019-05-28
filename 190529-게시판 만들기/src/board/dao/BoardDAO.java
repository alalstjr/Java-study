package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

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
}
