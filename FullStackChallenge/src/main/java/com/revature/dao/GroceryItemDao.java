package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.HibernateUtility;
import com.revature.model.GroceryItem;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class GroceryItemDao {
	private HibernateUtility hiUtil;
	
	public List<GroceryItem> selectAllGroceryItems(){
		Session s = hiUtil.getSession();
		Query<GroceryItem> q = s.createQuery("from GroceryItem", GroceryItem.class);
		return q.list();
	}
	
	public GroceryItem selectGroceryItemByName(String name) {
		Session s = hiUtil.getSession();
		Query<GroceryItem> q = s.createQuery("from GroceryItem where name = '"+name+"'",GroceryItem.class);
		List<GroceryItem> gList = q.list();
		if(gList.size() == 0) return null;
		return gList.get(0);
	}
	
	public void createGroceryItem(GroceryItem gItem) {
		Session s = hiUtil.getSession();
		Transaction t = s.beginTransaction();
		s.save(gItem);
		t.commit();
	}

}
