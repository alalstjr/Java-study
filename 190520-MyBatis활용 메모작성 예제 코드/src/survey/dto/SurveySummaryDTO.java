package survey.dto;

public class SurveySummaryDTO {
	private int survey_num; // 문제번호
	private int num; // 응답번호
	private int sum_num; // 응답횟수
	private double rate; // 응답률
	
	public SurveySummaryDTO() {
		super();
	}
	
	public SurveySummaryDTO(int survey_num, int num, int sum_num, double rate) {
		super();
		this.survey_num = survey_num;
		this.num = num;
		this.sum_num = sum_num;
		this.rate = rate;
	}

	public final int getSurvey_num() {
		return survey_num;
	}

	public final void setSurvey_num(int survey_num) {
		this.survey_num = survey_num;
	}

	public final int getNum() {
		return num;
	}

	public final void setNum(int num) {
		this.num = num;
	}

	public final int getSum_num() {
		return sum_num;
	}

	public final void setSum_num(int sum_num) {
		this.sum_num = sum_num;
	}

	public final double getRate() {
		return rate;
	}

	public final void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "SurveySummaryDTO [survey_num=" + survey_num + ", num=" + num + ", sum_num=" + sum_num + ", rate=" + rate
				+ "]";
	}
	
}
