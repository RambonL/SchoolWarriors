package de.schoolwarriors.item;

public class Item {
    private String name;
    private int dmg;
    private int speed;
    private int rarity; //-128 127

    public Item(String name, int dmg, int speed, int rarity) {
        this.name = name;
        this.dmg = dmg;
        this.speed = speed;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", dmg=" + dmg +
                ", speed=" + speed +
                ", rarity=" + rarity +
                '}';
    }
}
