package swing.table_rendering;

import java.math.BigDecimal;
import java.util.Locale;

public class Currency {
	private Locale type;
	private BigDecimal value;

	public Currency(double value) {
		this(Locale.US, value);
	}

	public Currency(BigDecimal value) {
		this(Locale.US, value);
	}

	public Currency(Locale type, double value) {
		this(type, new BigDecimal(value));
	}

	public Currency(Locale type, BigDecimal value) {
		this.type = type;
		this.value = value;
	}

	public Locale getType() {
		return type;
	}

	public BigDecimal getValue() {
		return value;
	}
}
