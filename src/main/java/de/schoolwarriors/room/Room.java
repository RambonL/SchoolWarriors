package de.schoolwarriors.room;

import de.schoolwarriors.entity.Mob;
import java.util.ArrayList;
import java.util.List;

public class Room {

	private int id;
	private int mobCount;
	private List<Mob> mobsList = new ArrayList<>();
	private boolean boss;

	public Room(int id, int mobCount, boolean boss) {
		this.id = id;
		this.mobCount = mobCount;
		this.boss = boss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMobCount() {
		return mobCount;
	}

	public void setMobCount(int mobCount) {
		this.mobCount = mobCount;
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public List<Mob> getMobsList() {
		return mobsList;
	}

	@Override
	public String toString() {
		return "Room{" +
				"id=" + id +
				", mobCount=" + mobCount +
				", mobsList=" + mobsList +
				", boss=" + boss +
				'}';
	}
}
