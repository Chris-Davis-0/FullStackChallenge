package com.revature.controller;

import com.revature.service.GroceryService;

import io.javalin.http.Handler;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GroceryController {
	private GroceryService gListService;
	
	public GroceryController(GroceryService gListService) {
		this.gListService = gListService;
	}
	

	public Handler getAllGroceryLists = (ctx) -> {
		ctx.json(gListService.selectAllGroceryLists().toString());
		ctx.status(200);
	};
	
//	public Handler getAllGroceryLists = (ctx) -> {
//		System.out.println("Requesting all grocery lists");
//		List<GroceryList> gLists = gListService.selectAllGroceryLists();
//		System.out.println(gLists);
//		ObjectMapper mapper = new ObjectMapper();
//		TreeNode json = mapper.readTree(gLists.toString());
//		System.out.println(json);
//		ctx.json(json);
//		ctx.status(200);
//	};
	
	public Handler postNewGroceryList = (ctx) -> {
		String[] gListBody = ctx.body().split("\"");
		String gListName = gListBody[3];
		if(gListService.postGroceryList(gListName)) {
			ctx.status(201);
		} else ctx.status(400);
	};
	
	//Let the hackery commence!
	public Handler postNewGroceryItemToGroceryList = (ctx) -> {
		String[] rBody = ctx.body().split("\"");
		String gItemName = rBody[3];
		System.out.println(gItemName);
		String gItemType = rBody[11];
		System.out.println(gItemType);
		String gListName = rBody[15];
		System.out.println(gListName);
		try{
			Double gItemPrice = Double.parseDouble(rBody[7]);
			System.out.println(gItemPrice);
			if(gListService.postNewGroceryItemToList(gItemName, gItemPrice, gItemType, gListName)) {
				ctx.status(201);
			} else ctx.status(400);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			ctx.status(400);
		}
		
	};
	
	public Handler deleteGroceryItemFromGroceryList = (ctx) -> {
		String[] rBody = ctx.body().split("\"");
		String gListName = rBody[3];
		System.out.println(gListName);
		try{
			Integer itemId = Integer.parseInt(ctx.pathParam("itemId"));
			System.out.println(itemId);
			if(gListService.deleteGroceryListItem(gListName, itemId)) {
				ctx.status(200);
			} else ctx.status(400);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			ctx.status(400);
		}
	};
	
	public Handler deleteGroceryList = (ctx) -> {
		String[] rBody = ctx.body().split("\"");
		String gListName = rBody[3];
		System.out.println(gListName);
		if(gListService.deleteGroceryList(gListName)) {
			ctx.status(200);
		} else ctx.status(400);
	};
}
