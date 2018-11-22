package de.schoolwarriors.entity;

import de.schoolwarriors.util.ItemListHandler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class EntityHandler extends ItemListHandler<Mob> {

	public void loadEntities(String resourcePath) throws IOException {
		Map<Integer, String[]> prmM = this.load(resourcePath);
		for (Map.Entry<Integer, String[]> entry : prmM.entrySet()) {
			int i = entry.getKey();
			String[] params = entry.getValue();
			if (params.length != 4) {
				throw new IllegalStateException("Could not parse " + resourcePath + "! Line " + i + " is missing some parameters!");
			}

			String name = params[0];
			int attack_dmg = this.StringToInt(params[1]);
			int attack_speed = this.StringToInt(params[2]);
			int health = this.StringToInt(params[3]);

			this.addItem(new Mob(name, attack_dmg, attack_speed, health));
		}
	}

	public Mob getNewEntity() {
		int randInt = ThreadLocalRandom.current().nextInt(0, this.getItems().size());
		return this.getItems().get(randInt);
	}
}
