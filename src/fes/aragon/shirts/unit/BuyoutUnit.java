package fes.aragon.shirts.unit;

import java.util.ArrayList;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fes.aragon.shirts.*;

public class BuyoutUnit {
	private Buyout _buyout;
	
	@BeforeEach
	public void init() {
		_buyout = new Buyout();
	}
	
	@Test
	@DisplayName("Check correct create and delete product in buyout")
	public void addDelShirt(){
		_buyout.addShirt(ShirtsType.SHORT_SLEEVE);
		Map<ShirtsType, ArrayList<Shirt>> shirts = _buyout.getBuyout();
		assertEquals(1, shirts.size());
		_buyout.deleteShirt(ShirtsType.SHORT_SLEEVE);
		shirts = _buyout.getBuyout();
		assertEquals(0, shirts.get(ShirtsType.SHORT_SLEEVE).size());
	}
	
	@Test
	@DisplayName("Test correct discount prices for each product")
	public void checkPrices() {
		final ArrayList<Shirt> Lshirts = new ArrayList<>();
		_buyout.addShirt(ShirtsType.CASUAL_SLEEVE_LONG);
		_buyout.addShirt(ShirtsType.FORMAL_SLEEVE_LONG);
		getShirts(Lshirts);
		Lshirts.forEach(item -> assertEquals(item.price, Util.prices.get(item.type)));
		_buyout.addShirt(ShirtsType.SHORT_SLEEVE);
		getShirts(Lshirts);
		Lshirts.forEach(item -> {
			final Double disPrice = Util.getDiscount(Lshirts.size()) * Util.prices.get(item.type);
			assertEquals(Util.prices.get(item.type) - disPrice, item.price);
		});
		_buyout.addShirt(ShirtsType.CASUAL_SLEEVE_LONG);
		_buyout.addShirt(ShirtsType.FORMAL_SLEEVE_LONG);
		_buyout.addShirt(ShirtsType.SHORT_SLEEVE);
		getShirts(Lshirts);
		Lshirts.forEach(item -> {
			final Double disPrice = Util.getDiscount(Lshirts.size()) * Util.prices.get(item.type);
			assertEquals(Util.prices.get(item.type) - disPrice, item.price);
		});
	}
	
	private void getShirts(ArrayList<Shirt> Lshirts) {
		Lshirts.clear();
		Map<ShirtsType, ArrayList<Shirt>> Mshirts = _buyout.getBuyout();
		Mshirts.forEach((n, list) -> list.forEach(item -> Lshirts.add(item)));
	}
}
