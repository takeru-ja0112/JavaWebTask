
public class XSSservlet {
	private String sanitizedValue;

	XSSservlet(String input) {
		this.sanitizedValue = sanitizeInput(input);
	}

	private String sanitizeInput(String input) {
		if (input == null)
			return null;

		return input.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;")
				.replaceAll("&", "&amp;")
				.replaceAll("\"", "&quot;")
				.replaceAll("'", "&#x27;");
	}

	public String getSanitizeValue() {
		return sanitizedValue;
	}
}
