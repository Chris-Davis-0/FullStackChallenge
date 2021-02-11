package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.HibernateUtility;
import com.revature.model.GroceryItem;
import com.revature.model.GroceryList;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class GroceryListDao {
	private HibernateUtility hiUtil;
	
	public List<GroceryList> selectAllGroceryLists(){
		Session s = hiUtil.getSession();
		Query<GroceryList> q = s.createQuery("from GroceryList", GroceryList.class);
		return q.list();
	}
	
	public GroceryList selectGroceryListByName(String name) {
		Session s = hiUtil.getSession();
		System.out.println("****"+name);
		Query<GroceryList> q = s.createQuery("from GroceryList where name = '"+name+"'",GroceryList.class);
		List<GroceryList> gList = q.list();
		if(gList.size() == 0) return null;
		return gList.get(0);
	}
	
	public boolean createGroceryList(GroceryList gList) {
		try {
			Session s = hiUtil.getSession();
			Transaction t = s.beginTransaction();
			s.save(gList);
			t.commit();
		}catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addItemToGroceryList(GroceryList gList, GroceryItem gItem) {
		if(gItem != null && gList != null) {
			try {
				Session s = hiUtil.getSession();
				Transaction t = s.beginTransaction();
				gItem.addGroceryList(gList);
				s.save(gList);
				t.commit();
			} catch(HibernateException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else return false;
	}
	
	public boolean removeItemFromGroceryList(GroceryList gList, Integer gId) {
		List<GroceryItem> gItems = gList.getGroceryItems();
		for(int i = 0; i < gItems.size(); i++) {
			if(gItems.get(i).getId() == gId) {
				try {
					Session s = hiUtil.getSession();
					Transaction t = s.beginTransaction();
					gItems.remove(i);
					s.save(gList);
					t.commit();
					return true;
				} catch(HibernateException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean removeGroceryList(GroceryList gList) {
		try {
			Session s = hiUtil.getSession();
			Transaction t = s.beginTransaction();
			s.remove(gList);
			t.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
