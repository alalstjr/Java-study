package paging.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import paging.dto.MemoDTO;
import sqlmap.MyBatisManager;

public class MemoDAO {
	public List<MemoDTO> memoList(int start, int end) {
		List<MemoDTO> items = new ArrayList<MemoDTO>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		// mybatis 실행 객체 생성
		SqlSession session = MyBatisManager.getInstance().openSession();
		
		// page 네임스페이스의 id가 memoList 인 쿼리 실행
		map.put("start", start);
		map.put("end", end);
		
		items = session.selectList("page.memoList", map);
		
		// mybatis 리소스 정리
		session.close();
		
		return items;
	}
	
	public int memoCount() {
		SqlSession session = MyBatisManager.getInstance().openSession();
		int count = session.selectOne("page.memoCount");
		session.close();
		return count;
	}
}
