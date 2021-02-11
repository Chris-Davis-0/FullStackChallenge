package com.revature;

import java.util.ArrayList;
import java.util.List;

import com.revature.controller.GroceryController;
import com.revature.dao.GroceryItemDao;
import com.revature.dao.GroceryListDao;
import com.revature.model.GroceryItem;
import com.revature.model.GroceryList;
import com.revature.service.GroceryService;

import io.javalin.Javalin;

public class ApplicationDriver {
	private static HibernateUtility hiUtil = new HibernateUtility();
	private static GroceryListDao gListDao = new GroceryListDao(hiUtil);
	private static GroceryItemDao gItemDao = new GroceryItemDao(hiUtil);
	private static GroceryService gService = new GroceryService(gListDao, gItemDao);
	private static GroceryController gControl = new GroceryController(gService);
	
	
	public static void main(String[] args) {
		Javalin app = Javalin.create(config ->{
			config.enableCorsForAllOrigins();
//			config.enableCorsForOrigin("*"); 
			config.addStaticFiles("/frontend");
		}).start(9003);
		
		app.get("/shop/grocery-lists", gControl.getAllGroceryLists);
		app.post("/shop/grocery-lists", gControl.postNewGroceryList);
		app.post("/shop/grocery-lists/items", gControl.postNewGroceryItemToGroceryList);
		app.delete("/shop/grocery-lists/items/:itemId", gControl.deleteGroceryItemFromGroceryList);
		app.delete("/shop/grocery-lists", gControl.deleteGroceryList);
		
		initializeDatabase();
	}
	
	private static void initializeDatabase() {
		List<GroceryList> gLists = new ArrayList<>();
		GroceryList gList1 = new GroceryList("Dorothy");
		GroceryList gList2 = new GroceryList("Scarecrow");
		GroceryList gList3 = new GroceryList("Cowardly Lion");
		GroceryList gList4 = new GroceryList("Tin Man");
		gLists.add(gList1);
		gLists.add(gList2);
		gLists.add(gList3);
		gLists.add(gList4);
		
		GroceryItem broccoliCrown = new GroceryItem("Broccoli Crown", 0.84, "Produce");
		GroceryItem bagSalad = new GroceryItem("Bag of Salad", 2.55, "Produce");
		GroceryItem redOnion = new GroceryItem("Red Onion", 0.67, "Produce");
		GroceryItem yellowPepper = new GroceryItem("Yellow Pepper", 1.21, "Produce");
		GroceryItem flankSteak = new GroceryItem("Flank Steak", 13.83, "Meat");
		GroceryItem chickenThighs = new GroceryItem("Flank Steak", 8.23, "Meat");
		GroceryItem breakfastSausage = new GroceryItem("Breakfast Sausage", 2.88, "Meat");
		GroceryItem flour = new GroceryItem("Flour", 4.22, "Baking");
		GroceryItem Corriander = new GroceryItem("Corriander", 3.35, "Spice");
		GroceryItem driedBasil = new GroceryItem("Dried Basil", 1.99, "Spice");
		GroceryItem dietCoke = new GroceryItem("Diet Coke", 5.25, "Soda");
		GroceryItem mtDew = new GroceryItem("Mountain Dew", 5.25, "Soda");
		
		broccoliCrown.addGroceryList(gList1);
		broccoliCrown.addGroceryList(gList2);
		bagSalad.addGroceryList(gList1);
		bagSalad.addGroceryList(gList2);
		redOnion.addGroceryList(gList2);
		chickenThighs.addGroceryList(gList3);
		
		for(GroceryList g : gLists) {
			gListDao.createGroceryList(g);			
		}
		
	}
}
