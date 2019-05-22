package memo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.dto.MemoDTO;
import sqlmap.MyBatisManager;

public class MemoDAO {
	// mybatis에서 리턴타입을 ArrayList가 아닌 List로 해야함
	public List<MemoDTO> listMemo(String searchkey, String search) {
		// SqlSession : mybatis로 sql을 실행시키는 객체
		SqlSession session = MyBatisManager.getInstance().openSession();
		
		// SqlSession을 이용하여 memo 네임스페이스의 listAll 쿼리를 실행시킴
		List<MemoDTO> list = null;
		if(searchkey.equals("writer_memo")) { // 이름 + 메모로 찾기
			list = session.selectList("memo.listAll", search);
		} else { // 이름으로 찾기, 메모로 찾기
			Map<String, String> map = new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			list=session.selectList("memo.list", map);
		}
		session.close(); // 세션을 닫아줌(리소스 정리
		return list;
	}
	public void insertMemo(MemoDTO dto) {
		// SqlSession : mybatis로 sql을 실행시키는 객체
		SqlSession session = MyBatisManager.getInstance().openSession();
		
		String writer = dto.getWriter();
		String memo = dto.getMemo();
				
		// 태그 문자 처리 replace(a,b)
		writer = writer.replace("<", "&lt;");
		writer = writer.replace(">", "&gt;");
		writer = writer.replace("  ", "&nbsp;&nbsp;");
		memo = memo.replace("<", "&lt;");
		memo = memo.replace(">", "&gt;");
		memo = memo.replace("  ", "&nbsp;&nbsp;");
		
		dto.setWriter(writer);
		dto.setMemo(memo);
		
		// insert("네임스페이스.sql의 id", 매개변수) 매개변수는 1개만 가능
		session.insert("memo.insert", dto);
		session.commit(); // 수동 커밋
		session.close(); // 세션을 닫아줌(리소스 정리)
	}
	public MemoDTO viewMemo(int num) {
		SqlSession session = MyBatisManager.getInstance().openSession();
		
		// 결과 레코드가 한건이므로 selectOne("select id", 매개변수);
		MemoDTO dto = session.selectOne("memo.view", num);
		session.close();
		return dto;
	}
	
	public void updateMemo(MemoDTO dto) {
		SqlSession session = MyBatisManager.getInstance().openSession();
		// 레코드 수정 update(네임스페이스, 입력매개변수)
		session.update("memo.update", dto);
		session.commit(); // 레코드 추가, 수정, 삭제 때 commit() 해야함
		session.close(); 
	}
	
	public void deleteMemo(int num) {
		SqlSession session = MyBatisManager.getInstance().openSession();
		session.delete("memo.delete", num);
		session.commit();
		session.close(); 		
	}
}
