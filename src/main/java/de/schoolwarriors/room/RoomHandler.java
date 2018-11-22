package de.schoolwarriors.room;

import de.schoolwarriors.util.ItemListHandler;
import java.io.IOException;
import java.util.Map;

public class RoomHandler extends ItemListHandler<Room> {

	public void loadRooms(String resourcePath) throws IOException {
		Map<Integer, String[]> prmM = this.load(resourcePath);
		int id = 0;
		for (Map.Entry<Integer, String[]> entry : prmM.entrySet()) {
			int i = entry.getKey();
			String[] params = entry.getValue();
			if (params.length != 2) {
				throw new IllegalStateException("Could not parse " + resourcePath + "! Line " + i + " is missing some parameters!");
			}
			int mobCount = this.StringToInt(params[0]);
			boolean isBoss = this.StringToInt(params[1]) != 0;

			this.addItem(new Room(++id, mobCount, isBoss));
		}
	}
}
