package de.schoolwarriors.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemListHandler<T> {

	private ArrayList<T> items = new ArrayList<>();

	public void addItem(T t) {
		if (!this.items.contains(t))
			this.items.add(t);
	}

	public void removeItem(T t) {
		this.items.remove(t);
	}

	public ArrayList<T> getItems() {
		return this.items;
	}

	protected Map<Integer, String[]> load(String resourcePath) throws IOException {
		//READ FILE TO MEMORY
		InputStream in = this.getClass().getResourceAsStream(resourcePath);
		if (in == null) {
			throw new IllegalStateException("Resource not found! " + resourcePath);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		ArrayList<String> lines = new ArrayList<>();
		String r;

		while ((r = br.readLine()) != null) {
			lines.add(r);
		}

		//PARSE FILE
		//FORMAT:
		//NAME ATTACK_DMG ATTACK_SPD RARITY
		Map<Integer, String[]> m = new HashMap<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.startsWith("//"))
				continue;

			String[] params = line.split(" "); // HALLO$WELT$WIE$GEHT$ES$DIR => split("$") => {HALLO,WELT,WIE,GEHT,ES,DIR}
			m.put(i, params);
		}

		//CLOSE STREAMS
		br.close();
		in.close();

		//RETURN MAP
		return m;
	}

	protected int StringToInt(String str) {
		 return Integer.parseInt(str);
	}
}
