package de.schoolwarriors.entity;

import de.schoolwarriors.item.Item;

public class Player {

	private int health;
	private Item item;

	public Player(int health) {
		this.health = health;
	}

	public int getAttackSpeed() {
		if (this.item != null)
			return this.item.getSpeed();
		return 0;
	}

	public int getAttackDamage() {
		if (this.item != null)
			return this.item.getDmg();
		return 0;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
