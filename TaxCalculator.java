package taxcalculator.aizat.de;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TaxCalculator {
	
	/**
	 * Input JSON-string
	 */
	private final static String input = "[" +
							"{\"item\":\"Book 3\", \"tax\":7, \"price\":4.95}," +
							"{\"item\":\"Movie\", \"tax\":19, \"price\":29.95}," +
							"{\"item\":\"Book 2\", \"tax\":7, \"price\":9.95}," +
							"{\"item\":\"Computer\", \"tax\":19, \"price\":499.00}," +
							"{\"item\":\"Book\", \"tax\":7, \"price\":9.95}" +
							"]";
	
	/**
	 * Name of parameter in JSON with item name
	 */
	private final static String PARAM_ITEM_NAME		=		"item";
	
	/**
	 * Name of parameter in JSON with tax
	 */
	private final static String PARAM_ITEM_TAX		=		"tax";
	
	/**
	 * Name of parameter in JSON with price
	 */
	private final static String PARAM_ITEM_PRICE	=		"price";
	
	/**
	 * Print list of elements from JSON-array
	 * 
	 * @param items the {@link JsonArray}
	 */
	private static void printItems(JsonArray items) {
		for (JsonElement item : items) {
			JsonObject currentItem = item.getAsJsonObject();
			
			String name = currentItem.get(PARAM_ITEM_NAME).getAsString();
			double tax = currentItem.get(PARAM_ITEM_TAX).getAsDouble();
			double price = currentItem.get(PARAM_ITEM_PRICE).getAsDouble();
			
			System.out.println(String.format("Item name: %s\tTax: %.2f\tPrice: %.2f", name, tax, price));
		}
	}
	
	/**
	 * Calculate a net sum
	 * 
	 * @param items the {@link JsonArray}
	 */
	private static void calcNetSum(JsonArray items) {
		double sum = 0;
		
		for (JsonElement item : items) {
			JsonObject currentItem = item.getAsJsonObject();
			
			sum += currentItem.get(PARAM_ITEM_PRICE).getAsDouble();
		}
		
		
	}
	
	/**
	 * Calculate a total sum
	 * 
	 * @param items the {@link JsonArray}
	 */
	private static double calcTotalSum(JsonArray items) {
		double sum = 0;
		
		for (JsonElement item : items) {
			JsonObject currentItem = item.getAsJsonObject();
			
			sum += currentItem.get(PARAM_ITEM_PRICE).getAsDouble();
			sum += currentItem.get(PARAM_ITEM_TAX).getAsDouble();
		}
		
		return sum;
	}
	
	/**
	 * Print a tax rate
	 * 
	 * @param items the {@link JsonArray}
	 */
	private static void printTaxRate(JsonArray items) {
		
		for (JsonElement item : items) {
			JsonObject currentItem = item.getAsJsonObject();
			
			String itemName = currentItem.get(PARAM_ITEM_NAME).getAsString();
			double tax = currentItem.get(PARAM_ITEM_TAX).getAsDouble();
			double price = currentItem.get(PARAM_ITEM_PRICE).getAsDouble();
			
			System.out.println(String.format("%s tax rate: %.1f%%", itemName, ((tax * 100) / (tax + price)))); 
		}
	}
	
	public static void main(String [] args) {
		
		JsonParser parser = new JsonParser();
	    JsonArray items = parser.parse(input).getAsJsonArray();
	    
	    printItems(items);
	    
	    calcNetSum(items);
	    
	    printTaxRate(items);
	    
	    System.out.println(String.format("\nNet sum: %.2f\n", calcTotalSum(items)));
	}
}
