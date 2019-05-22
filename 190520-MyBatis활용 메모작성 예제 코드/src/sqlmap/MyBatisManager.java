package sqlmap;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// mybatis를 이용하여 sql을 실행시키는 객체(sqlSession)를 생성하는 코드
public class MyBatisManager {
	// sqlSessionFactoryBuilder => sqlSessionFactory => sqlSession
	// sqlSession 객체 생성기
	
	// 인스턴스를 하나만 올리는것을 싱글톤 패턴
	// 다른곳에서 SqlSessionFactory 인스턴스 하면 서버 실행중에는 계속 한곳만 사용한다.
	private static SqlSessionFactory instance;

	// 생성자를 private 막아버려서 new 생성자로 인스턴스를 추가 생성을 막음 싱글톤패턴 
	private MyBatisManager() {
		super();
	}

	public static SqlSessionFactory getInstance() {
		Reader reader = null;
		try {
		    String resource = "sqlmap/sqlMapConfig.xml";
		    // java resources의 src
		    reader = Resources.getResourceAsReader(resource);
			// sqlSessionFactory 생성기
			instance = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
}
