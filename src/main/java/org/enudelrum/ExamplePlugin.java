package org.enudelrum;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.PlayerLootReceived;
import net.runelite.client.game.ItemStack;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.game.ItemManager;
import org.lwjgl.system.linux.CMsghdr;
//import net.runelite.client.plugins.grounditems.LootType;

import java.awt.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap; //Used for HashMap
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Enu's Example"
)

public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ExampleConfig config;

	//@Override
	protected void startUp() throws Exception
	{
		System.out.println("Example started!");
	}

	//@Override
	protected void shutDown() throws Exception
	{
		System.out.println("Example stopped!");
	}

	public void send(String category, String message) {
		//This will send a message to the game chat.
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", category + ": " + message, null);
		//System.out.println(AnyVar);
	}

	public void send(String category, int message) {
		//This will send a message to the game chat.
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", category + ": " + String.valueOf(message), null);
		//System.out.println(AnyVar);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Subscribe
	public void onItemSpawned(ItemSpawned itemSpawned) {
		//Get info on items on the ground.
		if (config.ItemSpawned()) {
			send("***ItemSpawned***", "START");



			//Get the tile the items spawned on.
			Tile tile = itemSpawned.getTile();
			send("tile.toString", tile.toString());
			//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "TEST tile: " + tile, null);
			//Output:rl5@18cb9706

			send("tile.getPlane", tile.getPlane());
			//Output: 0

			send("tile.getRenderLevel", tile.getRenderLevel());
			//Output: 1



			//LocalPoint will provide various location information.
			LocalPoint localPoint = tile.getLocalLocation();
			send("localPoint.toString", localPoint.toString());
			//Output: LocalPoint(x=6720, y=7104

			//ItemLayer is about the three models that can be seen on a tile.
			//This will give an int on how many models are on this tile (1 to 3).
			ItemLayer itemLayer = tile.getItemLayer();
			send("itemLayer.getHeight", itemLayer.getHeight());
			//Output: 0

			//Get the model of the item on top.
			//This can be used to change display/lighting of the model.
			Renderable renderable = itemLayer.getTop();
			Model model = renderable.getModel();
			send("model.toString", model.toString());
			//Output: ko@7e401365

			//Get a list of all items on that tile.
			List<TileItem> groundItems = tile.getGroundItems();

			//Gives the number of items on that tile.
			send("groundItems.size", groundItems.size());
			//Output: 1

			//Gives the item ID for the first thing on the ground.
			send("groundItems.get(0).getId", groundItems.get(0).getId());
			//Output: 1779

			//Get the list/number of items on the ground.
			List<TileItem> list = tile.getGroundItems();
			send("list.size()", list.size());
			//Output: 3

			//Get info (Item ID) for every item on the ground.
			for (int i = 0; i < list.size(); i++) {
				send("list.get(i).getId() #" + i, list.get(i).getId());
			}
			//Output: 1779
			//Output: 1779
			//Output: 1779



			//Look up item information based on Item ID.
			ItemComposition itemComposition = client.getItemDefinition(list.get(0).getId());
			send("itemComposition.getName", itemComposition.getName());
			//Output: Flax

			send("itemComposition.getMembersName", itemComposition.getMembersName());
			//Output: Flax

			send("itemComposition.getHaPrice", itemComposition.getHaPrice());
			//Output: 3

			send("itemComposition.getPrice", itemComposition.getPrice());
			//Output: 5

			String[] inventoryActions = itemComposition.getInventoryActions();
			send("inventoryActions[0]", inventoryActions[0]);
			//Output: null



			//I cannot get the GameObject[] to work.
			//GameObject[] gameObject = tile.getGameObjects();
			//send("gameObject[0].getId", gameObject[0].getId());
			//send("gameObject.sizeX() & sizeY()", gameObject[0].sizeX() + ", " + gameObject[0].sizeY());



			//Get the item that spawned.
			TileItem item = itemSpawned.getItem(); //object
			send("item.toString", item.toString());
			//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "TEST item: " + item, null);
			//Output: el@30137bef

			send("item.getId", item.getId()); //int, gives the item ID
			//Output: 1779

			send("item.getQuantity", item.getQuantity());  //int, seems to be 1 no matter if you drop multiple items or stackable items
			//Output: 1

			send("item.getModelHeight", item.getModelHeight());
			//Output: 1000



			//Get a string with the tile and item.
			String itemString = itemSpawned.toString();
			send("itemString", itemString);
			//Output: ItemSpawned(tile=rl5@18cb9706, item=el@30137bef)

			//USELESS: Compare to item objects to see if they're the same.
			boolean itemEquals = itemSpawned.equals(item);
			send("String.valueOf(itemEquals)", String.valueOf(itemEquals));
			//Output: false

			//USELESS: Get a hash of the item. This doesn't seem to be a HashMap or HashSet.
			int itemHash = itemSpawned.hashCode();
			send("String.valueOf(itemHash)", String.valueOf(itemHash));
			//Output: 274391838

			send("***ItemSpawned***", "END");
		}
	}

	@Subscribe
	public void onPlayerLootReceived(PlayerLootReceived playerLootReceived) {
		//Get loot info from player in PVP.
		if (config.PlayerLootReceived()) {
			send("***PlayerLootReceived***", "START");

			//Get the name of the player?
			Player player = playerLootReceived.getPlayer();
			send("player.getName", player.getName());
			//Output:

			//Get the items dropped by the player. (Still working on this.)
			//Collection<ItemStack> items = playerLootReceived.getItems();

			send("***PlayerLootReceived***", "END");
		}
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event) {
		//Get a spawned object.
		if (config.GameObjectSpawned()) {
			send("***GameObjectSpawned***", "START");

			GameObject gameObject = event.getGameObject();

			//Get object SizeX and SizeY.
			send("gameObject.sizeX() & sizeY()", gameObject.sizeX() + ", " + gameObject.sizeY());
			//Output: 1, 2

			send("***GameObjectSpawned***", "END");
		}
	}

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
}
