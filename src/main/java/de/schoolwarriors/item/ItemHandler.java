package de.schoolwarriors.item;

import de.schoolwarriors.util.ItemListHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ItemHandler extends ItemListHandler<Item> {

	public void loadItems(String resourcePath) throws IOException {
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
			int rarity = this.StringToInt(params[3]);

			Item item = new Item(name, attack_dmg, attack_speed, rarity);
			this.addItem(item);
		}
	}

	public Item getNewItem() {
		List<Item> chances = new ArrayList<>();
		for (Item item : this.getItems()) {
			for (int i = 0; i < item.getRarity(); i++) {
				chances.add(item);
			}
		}
		int randInt = ThreadLocalRandom.current().nextInt(0, chances.size());
		return chances.get(randInt);
	}
}
