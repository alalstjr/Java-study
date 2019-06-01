package board.dto;

import java.sql.Date;

public class BoardCommentDTO {
	private int comment_num;
	private int board_num;
	private String writer;
	private String content;
	private Date reg_date;
	
	public BoardCommentDTO() {
		super();
	}
	
	public BoardCommentDTO(int comment_num, int board_num, String writer, String content, Date reg_date) {
		super();
		this.comment_num = comment_num;
		this.board_num = board_num;
		this.writer = writer;
		this.content = content;
		this.reg_date = reg_date;
	}

	public final int getComment_num() {
		return comment_num;
	}

	public final void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public final int getBoard_num() {
		return board_num;
	}

	public final void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public final String getWriter() {
		return writer;
	}

	public final void setWriter(String writer) {
		this.writer = writer;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final Date getReg_date() {
		return reg_date;
	}

	public final void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [comment_num=" + comment_num + ", board_num=" + board_num + ", writer=" + writer
				+ ", content=" + content + ", reg_data=" + reg_date + "]";
	}
	
}
