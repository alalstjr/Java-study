package memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import memo.dto.MemoDTO;
import sqlmap.MyBatisManager;

public class MemoDAO {
	// mybatis에서 리턴타입을 ArrayList가 아닌 List로 해야함
	public List<MemoDTO> listMemo() {
		// SqlSession : mybatis로 sql을 실행시키는 객체
		SqlSession session = MyBatisManager.getInstance().openSession();
		
		// SqlSession을 이용하여 memo 네임스페이스의 listAll 쿼리를 실행시킴
		List<MemoDTO> list = session.selectList("memo.listAll");
		session.close(); // 세션을 닫아줌(리소스 정리
		return list;
	}
	public void insertMemo(MemoDTO dto) {
		// SqlSession : mybatis로 sql을 실행시키는 객체
		SqlSession session = MyBatisManager.getInstance().openSession();
		
		// insert("네임스페이스.sql의 id", 매개변수) 매개변수는 1개만 가능
		session.insert("memo.insert", dto);
		session.commit(); // 수동 커밋
		session.close(); // 세션을 닫아줌(리소스 정리)
	}
}
