package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="grocery_item")
public class GroceryItem {
	
	@Id @Column(name="grocery_item_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)private int id;
	@Column(name="grocery_item_name", unique=true)
	private String name;
	@Column(name="grocery_item_price")
	private double price;
	@Column(name="grocery_item_type_of_good")
	private String type;
	@ManyToMany(mappedBy = "groceryItems", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<GroceryList> groceryLists = new ArrayList<>();
	
	
	public GroceryItem(String name, double price, String typeOfGood) {
		this.name = name;
		this.price = price;
		this.type = typeOfGood;
	}
	
	public void addGroceryList(GroceryList gList) {
		groceryLists.add(gList);
		gList.getGroceryItems().add(this);
	}

	@Override
	public String toString() {
		return "GroceryItem [id=" + id + ", name=" + name + ", price=" + price + ", typeOfGood=" + type + "]";
	}
	
	
}
