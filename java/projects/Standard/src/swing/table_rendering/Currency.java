package swing.table_rendering;

import java.math.BigDecimal;
import java.util.Locale;

public class Currency {
	private Locale type;
	private BigDecimal value;

	public Currency(Locale itype, BigDecimal ivalue) {
		type = itype;
		value = ivalue;
	}

	public Currency(Locale itype, double ivalue) {
		this(itype, new BigDecimal(ivalue));
	}

	public Currency(double ivalue) {
		this(Locale.US, ivalue);
	}

	public Currency(BigDecimal ivalue) {
		this(Locale.US, ivalue);
	}

	public Locale getType() {
		return type;
	}

	public BigDecimal getValue() {
		return value;
	}
}
