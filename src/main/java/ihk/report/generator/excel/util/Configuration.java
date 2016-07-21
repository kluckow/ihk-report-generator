package ihk.report.generator.excel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Configuration.
 */
public final class Configuration {
	
	/** The Constant relevantHeaders. */
	public static final List<String> relevantHeaders = new ArrayList<>();
	
	public static final List<Integer> relevantIndices = new ArrayList<>();
	
	/**
	 * Instantiates a new configuration.
	 */
	private Configuration() {}
	
	// static will be 
	static {
		
		// Check from JIRA exported .xls-sheets
		relevantHeaders.add("Vorgangszusammenfassung");
		relevantHeaders.add("Stunden");
		relevantHeaders.add("Arbeitsdatum");
		relevantHeaders.add("Arbeitsbeschreibung");
		
		// Check from JIRA exported .xls-sheets
		relevantIndices.add(1);
		relevantIndices.add(2);
		relevantIndices.add(3);
		relevantIndices.add(17);
	}
}
