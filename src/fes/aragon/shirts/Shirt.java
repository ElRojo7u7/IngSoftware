package fes.aragon.shirts;

public final class Shirt {
	public ShirtsType type;
	public Double price;
	public Shirt(ShirtsType _type) {
		type = _type;
		price = Util.prices.get(_type);
	}
}
