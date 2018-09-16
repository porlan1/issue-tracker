package app.core;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class IssueTracker {
	String inputType;
	public float inputNumber;
	Map<String, String> returnValue = new HashMap<String, String>();
	static Map<String, Float> map = new HashMap<String, Float>(3);
	static Map<String, String> nameMap = new HashMap<String, String>();
	static Map<String, String> convertedTo = new HashMap<String, String>();

	static {
		map.put("lbs", new Float(0.453592));
		map.put("kg", new Float(1/0.453592));
		map.put("gal", new Float(3.78541));
		map.put("kg", new Float(1/3.78541));
		map.put("mi", new Float(1.60934));
		map.put("km", new Float(1/1.60934));

		nameMap.put("mi", "miles");
		nameMap.put("km", "kilometers");
		nameMap.put("lbs", "pounds");
		nameMap.put("kg", "kilograms");
		nameMap.put("gal", "gallons");
		nameMap.put("l", "liters");

		convertedTo.put("mi", "km");
		convertedTo.put("km", "mi");
		convertedTo.put("lbs", "kg");
		convertedTo.put("kg", "lbs");
		convertedTo.put("gal", "l");
		convertedTo.put("l", "gal");
	}

	public IssueTracker(String queryString) throws Exception {
		QueryParser queryParser = new QueryParser(queryString);
		queryParser.parse();
		inputType = queryParser.inputType;
		inputNumber = queryParser.inputNumber;
	}

	@JsonAnyGetter
	public Map<String, String> convertInput() {
		float convertedValue;
		convertedValue = inputNumber*map.get(inputType);
		String returnString = String.format("%f %s converts to %f %s", inputNumber, nameMap.get(inputType), convertedValue, nameMap.get(convertedTo.get(inputType)));
		returnValue.put("initNum", Float.toString(inputNumber));
		returnValue.put("initUnit", inputType);
		returnValue.put("returnNum", Float.toString(convertedValue));
		returnValue.put("returnUnit", convertedTo.get(inputType));
		returnValue.put("string", returnString);
		return returnValue;
	}
}
