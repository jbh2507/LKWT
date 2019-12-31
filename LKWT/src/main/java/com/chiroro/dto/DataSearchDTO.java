package com.chiroro.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
public class DataSearchDTO {
	
	private String groupBy;
	private String category, keyword;
	private String tag;
	private String userName;
	private Date startDate;
	private Date endDate;
	
	public DataSearchDTO() {
		tag = "이해도";
		userName = "";
		this.groupBy = "qno";
		this.endDate = Date.valueOf(LocalDate.now());
		this.startDate = Date.valueOf(endDate.toLocalDate().plusYears(-1L));
	}
	
	public void setRangeOfDate(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
