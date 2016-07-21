package ihk.report.generator.excel.unit;

import java.util.List;

public class Day {
	
	private List<Record> records;
	private float hours;
	
	public Day(List<Record> records) {
		this.setRecords(records);
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}
}
