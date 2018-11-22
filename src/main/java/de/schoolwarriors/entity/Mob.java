package de.schoolwarriors.entity;

public class Mob implements Cloneable {
	private String name;
	private int attackDmg;
	private int attackSpeed;
	private int health;

	public Mob(String name, int attackDmg, int attackSpeed, int health) {
		this.name = name;
		this.attackDmg = attackDmg;
		this.attackSpeed = attackSpeed;
		this.health = health;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void setAttackDmg(int attackDmg) {
		this.attackDmg = attackDmg;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Mob{" +
				"name='" + name + '\'' +
				", attackDmg=" + attackDmg +
				", attackSpeed=" + attackSpeed +
				", health=" + health +
				'}';
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
