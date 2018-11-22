package de.schoolwarriors;

import de.schoolwarriors.entity.EntityHandler;
import de.schoolwarriors.entity.Mob;
import de.schoolwarriors.entity.Player;
import de.schoolwarriors.item.Item;
import de.schoolwarriors.item.ItemHandler;
import de.schoolwarriors.room.Room;
import de.schoolwarriors.room.RoomHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class SchoolWarriors {

	private static SchoolWarriors instance;
	private final ItemHandler itemHandler;
	private final EntityHandler entityHandler;
	private final RoomHandler roomHandler;

	public SchoolWarriors() throws IOException {
		instance = this;

		itemHandler = new ItemHandler();
		itemHandler.loadItems("/items.itl");

		entityHandler = new EntityHandler();
		entityHandler.loadEntities("/mobs.itl");

		roomHandler = new RoomHandler();
		roomHandler.loadRooms("/rooms.itl");
	}

	public static SchoolWarriors getInstance() {
		return instance;
	}


	public static void main(String[] args) {
		try {
			SchoolWarriors schoolWarriors = new SchoolWarriors();
			System.out.flush();
			InputStream in = System.in;
			Scanner scanner = new Scanner(in);

			//MAIN GAME LOOP
            Player player = new Player(100000000);
			player.setItem(schoolWarriors.getItemHandler().getItems().get(0));
			int nextRoomId = 0;
			Room current = null;
			boolean init = true;
			boolean raffleSpeed = true;
			Item itemLayingOnGround = null;

			System.out.println("CONTROLS: \n\tForward: W\n\tAttack: A\n\tSwitch Item: S\n\tPass: P\nType \"w\" to continue!");
			while (true) {
				Thread.sleep(1000);
				if (scanner.hasNext()) {
					String l = scanner.nextLine().toLowerCase().trim();
					/*System.out.println("\""+l+"\"");*/
					if (l.equalsIgnoreCase(""))
						continue;

					if (init) {
						l = "w";
						init = false;
					}

					if (!(l.equalsIgnoreCase("w") || l.equalsIgnoreCase("a") || l.equalsIgnoreCase("s") || l.equalsIgnoreCase("p")))
						continue;

					Mob enemy = null;
					if (current != null) {
						try {
							enemy = current.getMobsList().get(0);
						} catch (IndexOutOfBoundsException ignored) {
							enemy = null;
						}


					}

					switch (l) {
						case "w":
							if (current != null && current.getMobsList().size() != 0) {
								System.out.print("Please finish your room first!\n");
								break;
							}
                            try {
                                current = schoolWarriors.getRoomHandler().getItems().get(nextRoomId++);
                            } catch (IndexOutOfBoundsException ignored) {
								System.out.println("Congratz! You successfully finished the game!");
								return;
							}

                            System.out.print("You're entering room number " + current.getId() + "\n");
							for (int i = 0; i < current.getMobCount(); i++) {
								current.getMobsList().add((Mob) schoolWarriors.entityHandler.getNewEntity().clone());
							}
							System.out.print(String.format("The room has %s enemies!\n", !current.isBoss() ? String.valueOf(current.getMobCount()) : "???"));
							itemLayingOnGround = schoolWarriors.getItemHandler().getNewItem();
							System.out.print(String.format("You've found an item laying on the ground!\n\tName: %s\n\tATK Diff: %d\n\tSpeed Diff: %d\n\tTo keep it, press S\n", itemLayingOnGround.getName(), itemLayingOnGround.getDmg() - player.getAttackDamage(), itemLayingOnGround.getSpeed() - player.getAttackSpeed()));
							enemy = current.getMobsList().get(0);
							break;
						case "s":
							if (itemLayingOnGround == null) {
								System.out.print("There's no item laying on the ground!\n");
								break;
							}
							System.out.print(String.format("You've successfully switched your item!\n\tName: %s\n\tATK: %d\n\tSpeed: %d\n", itemLayingOnGround.getName(), itemLayingOnGround.getDmg(), itemLayingOnGround.getSpeed()));
							player.setItem(itemLayingOnGround);
							itemLayingOnGround = null;
							break;
						case "p":
						case "a":
							if (itemLayingOnGround != null)
								itemLayingOnGround = null;

							if (enemy == null) {
								System.out.print("There is no enemy to attack!\n");
								break;
							}

							boolean playerStarts = false;
							if (raffleSpeed) {
								if (enemy.getAttackSpeed() <= player.getAttackSpeed()) {
									playerStarts = true;
									System.out.print("It's your turn! You start\n");
								} else {
									System.out.print("The enemy will start!\n");
								}
								raffleSpeed = false;
							}

							boolean pass = false;
							if (l.equalsIgnoreCase("p")) {
								pass = true;
								System.out.print("You passed your turn!\n");
							}

							boolean enemyKilled = false;
							if (playerStarts) {
								if (!pass) {
									enemy.setHealth(enemy.getHealth() - player.getAttackDamage());
									System.out.print("Damage dealt: " + player.getAttackDamage() + "\n");
									if (enemy.getHealth() <= 0) {
										System.out.print("You've killed an enemy!\n");
										current.getMobsList().remove(0);
										enemyKilled = true;
									}
								}
							}


							if (!enemyKilled) {
								player.setHealth(player.getHealth() - enemy.getAttackDmg());
								System.out.print("Damage taken: " + enemy.getAttackDmg() + "\n");
								if (player.getHealth() <= 0) {
									System.out.print("You died!\n");
									return;
								}
							}


							if (!playerStarts) {
								if (!pass) {
									enemy.setHealth(enemy.getHealth() - player.getAttackDamage());
									System.out.print("Damage dealt: " + player.getAttackDamage() + "\n");
									if (enemy.getHealth() <= 0) {
										System.out.print("You've killed an enemy!\n");
										current.getMobsList().remove(0);
										enemyKilled = true;
									}
								}
							}

							if (enemyKilled && current.getMobsList().size() == 0) {
								enemy = null;
							}
							break;
					}

                    System.out.print(String.format("Your Name: Madox Mortan\nYour stats: Health: %d; ATK: %d; Speed: %d; Item: %s\n", player.getHealth(), player.getAttackDamage(), player.getAttackSpeed(),
							player.getItem().getName()));

					if (enemy == null) {
						System.out.print("There is no mob to attack! Go ahead with \"w\"!\n");
					} else {
						System.out.print(String.format("Your enemy: Name: %s; ATK: %d; Speed: %d; Health: %d\n", enemy.getName(), enemy.getAttackDmg(), enemy.getAttackSpeed(), enemy.getHealth()));
					}
					System.out.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ItemHandler getItemHandler() {
		return itemHandler;
	}

	public EntityHandler getEntityHandler() {
		return entityHandler;
	}

	public RoomHandler getRoomHandler() {
		return roomHandler;
	}
}
