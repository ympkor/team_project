package dto.main;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.format.annotation.DateTimeFormat;

public class Calendar {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate selecDate; //사용자가 요청하면 보여 줄 날짜
	private int daysOfMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int year;
	private int month;
	private int firstDay;
	private int cntDay = 0;
	private String dayOfFirst;
	public Calendar() {}
	//생성자는 사용자가 요청한 Date만 받아서 그 값으로 다른 값들을 구한다.
	public Calendar(LocalDate selecDate) {
		this.selecDate = selecDate;
		this.year = selecDate.getYear();
		this.month = selecDate.getMonthValue();
		this.dayOfFirst = LocalDate.of(this.year, this.month, 1).getDayOfWeek().toString();
		switch (dayOfFirst) {
			case "SUNDAY" : this.firstDay = 1; 
			break;
			case "MONDAY" : this.firstDay = 2; 
			break;
			case "TUESDAY" : this.firstDay = 3; 
			break;
			case "WEDNESDAY" : this.firstDay = 4; 
			break;
			case "THURSDAY" : this.firstDay = 5; 
			break;
			case "FRIDAY" : this.firstDay = 6; 
			break;
			case "SATURDAY" : this.firstDay = 7; 
			break;
		}
		//윤년인지 체크
		if((year%4==0)&&(year%100!=0)||(year%400==0)) {daysOfMonth[1] = 29;}else {daysOfMonth[1] = 28;}
	}
	//일단 getter만 생성
	public LocalDate getSelecDate() {
		return selecDate;
	}
	public int[] getDaysOfMonth() {
		return daysOfMonth;
	}
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getFirstDay() {
		return firstDay;
	}
	public int getCntDay() {
		return cntDay;
	}
	public String getDayOfFirst() {
		return dayOfFirst;
	}
	@Override
	public String toString() {
		return "Calendar [selecDate=" + selecDate + ", daysOfMonth=" + Arrays.toString(daysOfMonth) + ", year=" + year
				+ ", month=" + month + ", firstDay=" + firstDay + ", cntDay=" + cntDay + ", dayOfFirst=" + dayOfFirst
				+ "]";
	}
}