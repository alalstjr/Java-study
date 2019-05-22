package survey.dto;

public class SurveyDTO {
	private int survey_num; // 문제 번호
	private String question; // 문제 내용
	private String ans1; // 문제 내용
	private String ans2; // 문제 내용
	private String ans3; // 문제 내용
	private String ans4; // 문제 내용
	private String status; // 문제 내용
	
	public SurveyDTO() {
		super();
	}

	public SurveyDTO(int survey_num, String question, String ans1, String ans2, String ans3, String ans4,
			String status) {
		super();
		this.survey_num = survey_num;
		this.question = question;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.ans4 = ans4;
		this.status = status;
	}

	public final int getSurvey_num() {
		return survey_num;
	}

	public final void setSurvey_num(int survey_num) {
		this.survey_num = survey_num;
	}

	public final String getQuestion() {
		return question;
	}

	public final void setQuestion(String question) {
		this.question = question;
	}

	public final String getAns1() {
		return ans1;
	}

	public final void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public final String getAns2() {
		return ans2;
	}

	public final void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public final String getAns3() {
		return ans3;
	}

	public final void setAns3(String ans3) {
		this.ans3 = ans3;
	}

	public final String getAns4() {
		return ans4;
	}

	public final void setAns4(String ans4) {
		this.ans4 = ans4;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SurveyDTO [survey_num=" + survey_num + ", question=" + question + ", ans1=" + ans1 + ", ans2=" + ans2
				+ ", ans3=" + ans3 + ", ans4=" + ans4 + ", status=" + status + "]";
	}
}
