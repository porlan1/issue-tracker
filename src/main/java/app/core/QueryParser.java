package app.core;
import app.resources.exceptions.IncompleteDataException;

public class QueryParser {
	String queryString;
	public String inputType;
	public float inputNumber;

	QueryParser(String queryString) {
		this.queryString = queryString;
	}

	public void parse() throws Exception {
		String valueString;
		int i;
		boolean invalidNumber = false;
		boolean invalidUnit = false;
		for (i = queryString.length()-1; i > -1; i--) {
			if (Character.isDigit(queryString.charAt(i))) {
				break;
			}
		}
		if (i == queryString.length()-1) {
			invalidNumber = true;
		}
		inputType = queryString.substring(i + 1).toString().toLowerCase();
		System.out.println(inputType);
		if (!inputType.equals("km") && !inputType.equals("mi") && !inputType.equals("gal") && !inputType.equals("l") && !inputType.equals("lbs") && !inputType.equals("kg")) {
			invalidUnit = true;
		}

		valueString = queryString.substring(0, i + 1).toString().toLowerCase();
		try {
			if (valueString.contains("/")) {
				String[] split = valueString.split("/");
				inputNumber = Float.parseFloat(split[0])/Float.parseFloat(split[1]);
			} else {
				inputNumber = Float.parseFloat(valueString);
			}
		} catch (Exception e) {
			invalidNumber = true;
		}
		if (invalidNumber || invalidUnit) {
			String exceptionString = (invalidNumber && invalidUnit)?"invalid number and unit":(invalidNumber? "invalid number": "invalid unit");
			throw new IncompleteDataException(exceptionString);
		}
	}
}
