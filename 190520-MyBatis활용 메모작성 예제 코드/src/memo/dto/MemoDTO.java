package memo.dto;

public class MemoDTO {
	private int num;
	private String writer;
	private String memo;
	private String post_date;
	
	public MemoDTO() {
		super();
	}
	public MemoDTO (String writer, String memo) {
		super();
		this.writer = writer;
		this.memo = memo;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	
	@Override
	public String toString() {
		return "MemoDTO [num=" + num + ", writer=" + writer + ", memo=" + memo + ", post_date=" + post_date + "]";
	}
	
}
