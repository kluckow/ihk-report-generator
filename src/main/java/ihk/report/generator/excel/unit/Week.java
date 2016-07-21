package ihk.report.generator.excel.unit;

import java.util.List;

public class Week {

	private List<Day> days;
	
	private String start;
	private String end;
	private String nr;
	
	public Week(List<Day> days) {
		this.days = days;
		this.start = getFirstDate();
		this.end = getLastDate();
	}

	private String getLastDate() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getFirstDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}
}
