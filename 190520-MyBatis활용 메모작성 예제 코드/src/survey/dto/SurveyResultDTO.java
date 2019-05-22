package survey.dto;

public class SurveyResultDTO {
	private int answer_num; // 응답 일련번호
	private int survey_num; // 문제번호
	private int num; // 응답번호
	
	public SurveyResultDTO() {
		super();
	}

	public SurveyResultDTO(int answer_num, int survey_num, int num) {
		super();
		this.answer_num = answer_num;
		this.survey_num = survey_num;
		this.num = num;
	}
	
	public final int getAnswer_num() {
		return answer_num;
	}

	public final void setAnswer_num(int answer_num) {
		this.answer_num = answer_num;
	}

	public final int getSurvey_um() {
		return survey_num;
	}

	public final void setSurvey_um(int survey_num) {
		this.survey_num = survey_num;
	}

	public final int getNum() {
		return num;
	}

	public final void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "SurveyResultDTO [answer_num=" + answer_num + ", survey_num=" + survey_num + ", num=" + num + "]";
	}
}
