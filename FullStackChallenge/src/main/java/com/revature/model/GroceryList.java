package com.revature.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="grocery_list")
public class GroceryList {
	@Id @Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)private int id;
	@Column(name="grocery_list_name", unique=true)
	private String name;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(	name="grocerylist_groceryitem", 
				joinColumns = {@JoinColumn(name = "grocery_list_id")},
				inverseJoinColumns = {@JoinColumn(name = "grocery_item_id")})
	private List<GroceryItem> groceryItems = new ArrayList<>();
	
	public GroceryList(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GroceryList [id=" + id + ", name=" + name + ", groceryItems=" + 
				groceryItems.stream().map(GroceryItem::getName).collect(Collectors.toList()) + "]";
	}
}

