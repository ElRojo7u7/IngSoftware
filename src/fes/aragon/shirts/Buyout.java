package fes.aragon.shirts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Buyout {
	private Integer _items = 0;
	private Map<ShirtsType, ArrayList<Shirt>> _shirts = new HashMap<ShirtsType, ArrayList<Shirt>>();
	
	public final void addShirt(ShirtsType shirt) {
		if(_shirts.containsKey(shirt)) _shirts.get(shirt).add(new Shirt(shirt));
		else _shirts.put(shirt, new ArrayList<Shirt>() {{add(new Shirt(shirt));}}); _items++;
		updatePrices();
	}
	
	public final void deleteShirt(ShirtsType shirt) {
		if(!_shirts.containsKey(shirt) || _shirts.get(shirt).isEmpty()) return;
		_shirts.get(shirt).remove(_shirts.get(shirt).size()-1); _items--;
		updatePrices();
	}
	
	public final Map<ShirtsType, ArrayList<Shirt>> getBuyout() {
		return new HashMap<ShirtsType, ArrayList<Shirt>>(_shirts);
	}
	
	private final void updatePrices() {
		final Float discount = Util.getDiscount(_items);
		if(discount == 0.0f) return;
		_shirts.forEach((n, list) -> 
			list.forEach((item) -> item.price = Util.prices.get(item.type)* (1.0d-discount))
		);
	}
	
}
