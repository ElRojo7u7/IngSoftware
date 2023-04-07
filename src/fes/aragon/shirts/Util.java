package fes.aragon.shirts;

import java.util.Map;

public final class Util {
	public static Map<ShirtsType, Double> prices = Map.of(
			ShirtsType.SHORT_SLEEVE, 190.0d,
			ShirtsType.CASUAL_SLEEVE_LONG, 230.0d,
			ShirtsType.FORMAL_SLEEVE_LONG, 310.0d
	);
	public static final Float getDiscount(Integer length) {
		if(length <= 2) return 0.0f;
		else if(length < 5) return 0.05f;
		else return 0.08f;
	}
}
