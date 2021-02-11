package com.revature.service;

import java.util.List;

import com.revature.dao.GroceryItemDao;
import com.revature.dao.GroceryListDao;
import com.revature.model.GroceryItem;
import com.revature.model.GroceryList;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class GroceryService {
	private GroceryListDao gListDao;
	private GroceryItemDao gItemDao;
	
	public List<GroceryList> selectAllGroceryLists(){
		return gListDao.selectAllGroceryLists();
	}
	
	public GroceryList selectGroceryListByName(String name) {
		return gListDao.selectGroceryListByName(name);
	}
	
	public boolean postGroceryList(String name) {
		GroceryList gList = new GroceryList(name);
		return gListDao.createGroceryList(gList);
	}
		
	public boolean postNewGroceryItemToList(String itemName, Double itemPrice, String itemType, String listName) {
		GroceryItem gItem = new GroceryItem(itemName, itemPrice, itemType);
		GroceryList gList = gListDao.selectGroceryListByName(listName);
		if(gList != null) {
			return gListDao.addItemToGroceryList(gList, gItem);
		} else return false;
	}
	
	public boolean deleteGroceryListItem(String gListName, Integer gId) {
		GroceryList gList = selectGroceryListByName(gListName);
		if(gListDao.removeItemFromGroceryList(gList, gId)) {
			return true;
		}else return false;
	}
	
	public boolean deleteGroceryList(String gListName) {
		GroceryList gList = selectGroceryListByName(gListName);
		if(gListDao.removeGroceryList(gList)) {
			return true;
		}else return false;
	}
}
