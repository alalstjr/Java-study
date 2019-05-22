package survey.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MyBatisManager;
import survey.dto.SurveyDTO;
import survey.dto.SurveyResultDTO;
import survey.dto.SurveySummaryDTO;

public class SurveyDAO {
	// 설문문제를 dto엣 저장하여 리턴하는 코드
	
	public SurveyDTO viewQuestion(int survey_num) {
		// sql 실행을 위한 mybatis 객체 생성
		SqlSession session = MyBatisManager.getInstance().openSession();
		
		// survey 네임스페이스의 view_question 쿼리 실행
		// 실행결과가 dto에 리턴됨
		// 결과가 하나이면 selectOne 이고
		// 결과가 두개 이상이면 selectList 이다
		SurveyDTO dto = session.selectOne("survey.view_question", survey_num);
		session.close();
		return dto;
	}
	
	public void insertSurvey(SurveyResultDTO dto) {
		SqlSession session = MyBatisManager.getInstance().openSession();
		session.insert("survey.insert_survey", dto); // 레코드 추가
		session.commit();
		session.close();
	}

	public List<SurveySummaryDTO> listSummary(int survey_num) {
		SqlSession session = MyBatisManager.getInstance().openSession();
		List<SurveySummaryDTO> items = session.selectList("survey.list_summary", survey_num);
		session.close();
		return items;
	}
}
