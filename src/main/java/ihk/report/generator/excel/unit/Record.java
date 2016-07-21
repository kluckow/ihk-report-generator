package ihk.report.generator.excel.unit;

public class Record {
	
	private String task;
	private float hours;
	// TODO: string or date object?
	private String date;
	private String description;
	
	public Record(String task, float hours, String date, String description) {
		this.task = task;
		this.hours = hours;
		this.date = date;
		this.description = description;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public float getHours() {
		return hours;
	}
	public void setHours(float hours) {
		this.hours = hours;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
