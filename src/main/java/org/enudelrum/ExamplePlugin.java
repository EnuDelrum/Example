package org.enudelrum;

import java.lang.reflect.Field;
import java.util.List;
import javax.inject.Inject;
import javax.naming.Name;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.PlayerLootReceived;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

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

	protected void startUp()
	{
		System.out.println("Example started!");
	}

	protected void shutDown()
	{
		System.out.println("Example stopped!");
	}

	public void send(String category, String message) {
		//This will send a message to the game chat.
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", category + ": " + message, null);
	}

	public void send(String category, int message) {
		//This will send a message to the game chat.
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", category + ": " + message, null);
	}

	public void send(String category, short message) {
		//This will send a message to the game chat.
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", category + ": " + message, null);
	}

	public void send(String category, boolean message) {
		//This will send a message to the game chat.
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", category + ": " + message, null);
	}

	//To be figured out later.
	//@Inject
	//ItemManager itemManager = null;
	//itemManager.getItemComposition(grandExchangeOffer.getItemId()).getName();

	@Subscribe
	public void onAccountHashChanged(AccountHashChanged accountHashChanged) {
		//Get AccountHashChanged. This doesn't seem to ever trigger. Maybe because you can't switch accounts anymore in RuneLite.
		if (config.AccountHashChanged()) {
			send("***AccountHashChanged***", "START");

			send("accountHashChanged.hashCode", accountHashChanged.hashCode());
			//Output:

			send("accountHashChanged.toString", accountHashChanged.toString());
			//Output:

			send("***AccountHashChanged***", "END");
		}
	}

	@Subscribe
	public void onActorDeath(ActorDeath actorDeath) {
		//Get info when an actor dies. This can be an NPC or player.
		if (config.ActorDeath()) {
			send("***ActorDeath***", "START");

			send("actorDeath.toString",actorDeath.toString());
			//Output: ActorDeath(actor=ds@5b2c725f)



			//Get info on the dying/dead actor.
			Actor actor = actorDeath.getActor();
			send("actor.getName", actor.getName());
			//Output: Monk

			send("actor.getCombatLevel", actor.getCombatLevel());
			//Output: 7

			send("actor.isDead", actor.isDead());
			//Output: true

			send("actor.isInteracting", actor.isInteracting());
			//Output: true

			actor.setOverheadText("I'm dead!");



			//Get actor minimap location.
			Point actorPoint = actor.getMinimapLocation();
			send("actorPoint.toString", actorPoint.toString());
			//Output: Point(x=1504, y=51)



			WorldArea actorWorldArea = actor.getWorldArea();
			send("actorWorldArea.getPlane", actorWorldArea.getPlane());
			//Output: 0

			send("actorWorldArea.getHeight", actorWorldArea.getHeight());
			//Output: 1

			send("actorWorldArea.getWidth", actorWorldArea.getWidth());
			//Output: 1

			send("actorWorldArea.getX", actorWorldArea.getX());
			//Output: 3202

			send("actorWorldArea.getY", actorWorldArea.getY());
			//Output: 10076

			//Can the actor travel north?
			send("actorWorldArea.canTravelInDirection(client, 0, 1) (North)", actorWorldArea.canTravelInDirection(client, 0, 1));
			//Output: true



			WorldPoint actorWorldPoint = actor.getWorldLocation();
			send("actorWorldPoint.getRegionID", actorWorldPoint.getRegionID());
			//Output: 12957

			send("actorWorldPoint.toString", actorWorldPoint.toString());
			//Output: WorldPoint(x=3202, y=10076, plane=0)



			//Get info on the attacking actor.
			Actor attacker = actor.getInteracting();
			send("attacker.getName", attacker.getName());
			//Output: Enu Delrum

			send("attacker.getHealthRatio", attacker.getHealthRatio());
			//Output: 28

			send("attacker.getHealthScale", attacker.getHealthScale());
			//Output: 30

			//This won't clear out automatically like normal overhead text.
			attacker.setOverheadText("I killed him!");



			//Get actor's location.
			LocalPoint actorLocalPoint = actor.getLocalLocation();
			send("actorLocalPoint.toString", actorLocalPoint.toString());
			//Output: LocalPoint(x=5568, y=5568)

			//Get attacker location.
			LocalPoint attackerLocalPoint = attacker.getLocalLocation();
			send("attackerLocalPoint.toString", attackerLocalPoint.toString());
			//Output: LocalPoint(x=5440, y=5568)

			//Get the distance between the actor and attacker.
			send("attackerLocalPoint.distanceTo(actorLocalPoint)", attackerLocalPoint.distanceTo(actorLocalPoint));
			//Output: 128
			//Every tile seems to have an X or Y distance of 128, but sometimes it's just part of a tile (like 244).

			send("***ActorDeath***", "END");
		}
	}

	@Subscribe
	public void onAmbientSoundEffectCreated(AmbientSoundEffectCreated ambientSoundEffectCreated) {
		//Get an ambient sound effect.
		if (config.AmbientSoundEffectCreated()) {
			send("***AmbientSoundEffect***", "START");

			//Get the string of ambientSoundEffectCreated.
			send("ambientSoundEffectCreated.toString", ambientSoundEffectCreated.toString());
			//Output: AmbientSoundEffectCreated(ambientSoundEffect=ce@34ccd7ed)

			//Get the sound effect ID.
			AmbientSoundEffect ambientSoundEffect = ambientSoundEffectCreated.getAmbientSoundEffect();
			send("ambientSoundEffect.getSoundEffectId", ambientSoundEffect.getSoundEffectId());
			//Output: 3139

			//Get the plane of the sound effect.
			send("ambientSoundEffect.getPlane", ambientSoundEffect.getPlane());
			//Output: 0

			//Get the number of sound effects in the area.
			//This can sometimes fail. Maybe check for null first?
			int[] soundEffectIds = ambientSoundEffect.getBackgroundSoundEffectIds();
			if (soundEffectIds != null) {send("soundEffectIds.length", soundEffectIds.length);}
			//Output: 1

			//Get the ID of the first sound effect in a list.
			if (soundEffectIds != null) {send("soundEffectIds[0]", soundEffectIds[0]);}
			//Output: 3721

			//Get the min x/y position of the sound effect.
			LocalPoint minPosition = ambientSoundEffect.getMinPosition();
			send("minPosition.toString", minPosition.toString());
			//Output: LocalPoint(x=3712, y=3712)

			//Get the max x/y position of the sound effect.
			LocalPoint maxPosition = ambientSoundEffect.getMaxPosition();
			send("maxPosition.toString", maxPosition.toString());
			//Output: LocalPoint(x=3840, y=3840)

			send("***AmbientSoundEffect***", "END");
		}
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged animationChanged) {
		//Get AnimationChanged.
		if (config.AnimationChanged()) {
			send("***AnimationChanged***", "START");

			send("animationChanged.toString", animationChanged.toString());
			//Output: AnimationChanged(actor=dn@2d8cebe6)

			Actor actor = animationChanged.getActor();
			send("actor.getName", actor.getName());
			//Output: Enu Delrum

			send("actor.getAnimation", actor.getAnimation());
			//Output: 6688

			send("***AnimationChanged***", "END");
		}
	}

	@Subscribe
	public void onAreaSoundEffectPlayed(AreaSoundEffectPlayed areaSoundEffectPlayed) {
		//Get AreaSoundEffectPlayed.
		if (config.AreaSoundEffectPlayed()) {
			send("***AreaSoundEffectPlayed***", "START");

			send("areaSoundEffectPlayed.getSoundId", areaSoundEffectPlayed.getSoundId());
			//Output: 200

			send("areaSoundEffectPlayed.getSceneX", areaSoundEffectPlayed.getSceneX());
			//Output: 50

			send("areaSoundEffectPlayed.getSceneY", areaSoundEffectPlayed.getSceneY());
			//Output: 55

			send("areaSoundEffectPlayed.getRange", areaSoundEffectPlayed.getRange());
			//Output: 5

			send("areaSoundEffectPlayed.toString", areaSoundEffectPlayed.toString());
			//Output: AreaSoundEffectPlayed(source=null, soundID=200, sceneX=50, sceneY=55, range=5, delay=0, consumed=false)

			//This gives the name of a person if they created the sound (like from fletching).
			//If the sounds don't come from a person (such as teleporting from POH with a Jewellery box) then there won't be a name.
			Actor actor = areaSoundEffectPlayed.getSource();
			if (actor != null) {send("actor.getName (sound coming from)", actor.getName());}
			//Output: Enu Delrum

			send("***AreaSoundEffectPlayed***", "END");
		}
	}

	@Subscribe
	public void onBeforeMenuRender(BeforeMenuRender beforeMenuRender) {
		//Get BeforeMenuRender.
		if (config.BeforeMenuRender()) {
			send("***BeforeMenuRender***", "START");

			send("beforeMenuRender.toString", beforeMenuRender.toString());
			//Output: BeforeMenuRender(consumed=false)

			send("***BeforeMenuRender***", "END");
		}
	}

	@Subscribe
	public void onBeforeRender(BeforeRender beforeRender) {
		//Get BeforeRender.
		if (config.BeforeRender()) {
			send("***BeforeRender***", "START");

			send("beforeRender.toString", beforeRender.toString());
			//Output: net.runelite.api.events.BeforeRender@66798395

			send("***BeforeRender***", "END");
		}
	}

	@Subscribe
	public void onCanvasSizeChanged(CanvasSizeChanged canvasSizeChanged) {
		//Get CanvasSizeChanged.
		if (config.CanvasSizeChanged()) {
			send("***CanvasSizeChanged***", "START");

			send("canvasSizeChanged.toString", canvasSizeChanged.toString());
			//Output: net.runelite.api.events.CanvasSizeChanged@7fa5506

			send("***CanvasSizeChanged***", "END");
		}
	}

	//The ChatMessage event caused RuneLite to lock up as soon as I enable it.
	/*@Subscribe
	public void onChatMessage(ChatMessage chatMessage) {
		//Get ChatMessage.
		if (config.ChatMessage()) {
			send("***ChatMessage***", "START");

			send("chatMessage.getMessage", chatMessage.getMessage());
			//Output:

			send("chatMessage.getName", chatMessage.getName());
			//Output:

			send("chatMessage.getSender", chatMessage.getSender());
			//Output:

			send("chatMessage.getTimestamp", chatMessage.getTimestamp());
			//Output:

			MessageNode messageNode = chatMessage.getMessageNode();
			send("messageNode.getId", messageNode.getId());
			//Output:

			send("messageNode.getRuneLiteFormatMessage", messageNode.getRuneLiteFormatMessage());
			//Output:

			send("messageNode.getValue", messageNode.getValue());
			//Output:

			ChatMessageType chatMessageType = messageNode.getType();
			send("chatMessage.getName", chatMessage.getName());
			//Output:

			send("chatMessageType.toString", chatMessageType.toString());
			//Output:


			send("***ChatMessage***", "END");
		}
	}*/

	//SKIPPED: ClanChannelChanged, ClanMemberJoined, ClanMemberLeft

	@Subscribe
	public void onClientTick(ClientTick clientTick) {
		//Get ClientTick.
		if (config.ClientTick()) {
			send("***ClientTick***", "START");

			send("clientTick.toString", clientTick.toString());
			//Output: net.runelite.api.events.ClientTick@1b258aae

			send("***ClientTick***", "END");
		}
	}

	@Subscribe
	public void onCommandExecuted(CommandExecuted commandExecuted) {
		//Get CommandExecuted. Use the chat format:   ::test alpha beta charlie
		if (config.CommandExecuted()) {
			send("***CommandExecuted***", "START");

			send("commandExecuted.toString", commandExecuted.toString());
			//Output: CommandExecuted(command=test, arguments=[alpha, beta, charlie])

			send("commandExecuted.getCommand", commandExecuted.getCommand());
			//Output: test

			//Get all the arguments that was used with the chat command.
			String[] arguments = commandExecuted.getArguments();
			if (arguments != null) {
                for (int i = 0; i < 5; i++) {
                    send("argument # " + i, arguments[i]);
                }
			}
			//Output: alpha
			//Output: beta
			//Output: charlie

			send("***CommandExecuted***", "END");
		}
	}

	//SKIPPED: DecorativeObjectDespawned

	@Subscribe
	public void onDecorativeObjectSpawned(DecorativeObjectSpawned decorativeObjectSpawned) {
		//Get DecorativeObjectSpawned.
		if (config.DecorativeObjectSpawned()) {
			send("***DecorativeObjectSpawned***", "START");

			send("decorativeObjectSpawned.toString", decorativeObjectSpawned.toString());
			//Output: DecorativeObjectSpawned(tile=rl5@666593d8, decorativeObject=lu@150342)

			Tile tile = decorativeObjectSpawned.getTile();
			send("tile.toString", tile.toString());
			//Output: rl5@666593d8

			DecorativeObject decorativeObject = decorativeObjectSpawned.getDecorativeObject();
			send("decorativeObject.toString", decorativeObject.toString());
			//Output: lu@150342

			send("***DecorativeObjectSpawned***", "END");
		}
	}

	@Subscribe
	public void onDraggingWidgetChanged(DraggingWidgetChanged draggingWidgetChanged) {
		//Get DraggingWidgetChanged.
		if (config.DraggingWidgetChanged()) {
			send("***DraggingWidgetChanged***", "START");

			send("draggingWidgetChanged.isDraggingWidget", draggingWidgetChanged.isDraggingWidget());
			//Output: true

			send("draggingWidgetChanged.toString", draggingWidgetChanged.toString());
			//Output: DraggingWidgetChanged(DraggingWidget=true)

			send("draggingWidgetChanged.hashCode", draggingWidgetChanged.hashCode());
			//Output: 138

			send("***DraggingWidgetChanged***", "END");
		}
	}

	@Subscribe
	public void onFakeXpDrop(FakeXpDrop fakeXpDrop) {
		//Get FakeXpDrop.
		if (config.FakeXpDrop()) {
			send("***FakeXpDrop***", "START");

			send("fakeXpDrop.toString", fakeXpDrop.toString());
			//Output:

			send("fakeXpDrop.getXp", fakeXpDrop.getXp());
			//Output:

			Skill skill = fakeXpDrop.getSkill();
			send("skill.name", skill.name());
			//Output:

			send("skill.getName", skill.getName());
			//Output:

			send("skill.toString", skill.toString());
			//Output:

			send("***FakeXpDrop***", "END");
		}
	}

	@Subscribe
	public void onFocusChanged(FocusChanged focusChanged) {
		//Get FocusChanged. This doesn't seem to work.
		if (config.FocusChanged()) {
			send("***FocusChanged***", "START");

			send("focusChanged.toString", focusChanged.toString());
			//Output:

			send("focusChanged.isFocused", focusChanged.isFocused());
			//Output:

			//Reset focus.
			//focusChanged.setFocused(true);

			send("***FocusChanged***", "END");
		}
	}

	//SKIPPED: FriendsChatChanged, FriendsChatMemberJoined, FriendsChatMemberLeft, GameObjectDespawned

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

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		//Get GameStateChanged.
		if (config.GameStateChanged()) {
			send("***GameStateChanged***", "START");

			/*if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
			}*/

			send("gameStateChanged.toString", gameStateChanged.toString());
			//Output: GameStateChanged(gameState=LOGGED_IN)

			switch(gameStateChanged.getGameState()) {
				case CONNECTION_LOST:
					send("gameStateChanged.getGameState", String.valueOf(gameStateChanged.getGameState()));
					break;
				case HOPPING:
					send("gameStateChanged.getGameState", "HOPPING");
					break;
				case LOADING:
					send("gameStateChanged.getGameState", "LOADING");
					break;
				case LOGGED_IN:
					send("gameStateChanged.getGameState", "LOGGED_IN");
					break;
				case LOGGING_IN:
					send("gameStateChanged.getGameState", "LOGGING_IN");
					break;
				case LOGIN_SCREEN:
					send("gameStateChanged.getGameState", "LOGIN_SCREEN");
					break;
				case LOGIN_SCREEN_AUTHENTICATOR:
					send("gameStateChanged.getGameState", "LOGIN_SCREEN_AUTHENTICATOR");
					break;
				case STARTING:
					send("gameStateChanged.getGameState", "STARTING");
					break;
				case UNKNOWN:
					send("gameStateChanged.getGameState", "UNKNOWN");
					break;
			}
			//Output: LOGGED_IN

			GameState gameState = gameStateChanged.getGameState();
			send("gameState.getState", gameState.getState());
			//Output: 30

			send("gameState.toString", gameState.toString());
			//Output: LOGGED_IN

			send("gameState.name", gameState.name());
			//Output: LOGGED_IN

			send("***GameStateChanged***", "END");
		}
	}

	@Subscribe
	public void onGameTick(GameTick gameTick) {
		//Get GameTick.
		if (config.GameTick()) {
			send("***GameTick***", "START");

			send("gameTick.toString", gameTick.toString());
			//Output: GameTick()

			send("***GameTick***", "END");
		}
	}

	@Subscribe
	public void onGrandExchangeOfferChanged(GrandExchangeOfferChanged grandExchangeOfferChanged) {
		//Get GrandExchangeOfferChanged.
		if (config.GrandExchangeOfferChanged()) {
			send("***GrandExchangeOfferChanged***", "START");

			send("grandExchangeOfferChanged.toString", grandExchangeOfferChanged.toString());
			//Output: GrandExchangeOfferChanged(offer=ob@39ac66b, slot=1)

			//Top-left to top-right: 0 to 3. Bottom-left to bottom-right: 4-7.
			send("grandExchangeOfferChanged.getSlot", grandExchangeOfferChanged.getSlot());
			//Output: 1

			GrandExchangeOffer grandExchangeOffer = grandExchangeOfferChanged.getOffer();
			send("grandExchangeOffer.getItemId", grandExchangeOffer.getItemId());
			//Output:4878

			send("grandExchangeOffer.getPrice", grandExchangeOffer.getPrice());
			//Output: 2695137

			send("grandExchangeOffer.getQuantitySold", grandExchangeOffer.getQuantitySold());
			//Output: 12

			send("grandExchangeOffer.getSpent", grandExchangeOffer.getSpent());
			//Output: 32341644

			send("grandExchangeOffer.getTotalQuantity", grandExchangeOffer.getTotalQuantity());
			//Output: 16

			GrandExchangeOfferState grandExchangeOfferState = grandExchangeOffer.getState();
			send("grandExchangeOfferState.name", grandExchangeOfferState.name());
			//Output: BUYING

			send("grandExchangeOfferState.toString", grandExchangeOfferState.toString());
			//Output: BUYING

			ItemComposition itemComposition = client.getItemDefinition(grandExchangeOffer.getItemId());
			send("itemComposition.getName", itemComposition.getName());
			//Output: Ahrim's robeskirt 0

			//This is the "value" of the item; not the GE price.
			send("itemComposition.getPrice", itemComposition.getPrice());
			//Output: 47000

			send("***GrandExchangeOfferChanged***", "END");
		}
	}

	@Subscribe
	public void onGrandExchangeSearched(GrandExchangeSearched grandExchangeSearched) {
		//Get GrandExchangeSearched. This fires each time a character is typed into the GE search window.
		if (config.GrandExchangeSearched()) {
			send("***GrandExchangeSearched***", "START");

			send("grandExchangeSearched.toString", grandExchangeSearched.toString());
			//Output: GrandExchangeSearched(consumed=false)

			send("***GrandExchangeSearched***", "END");
		}
	}

	//SKIPPED: GraphicChanged, GraphicsObjectCreated, GroundObjectDespawned

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned groundObjectSpawned) {
		//Get GroundObjectSpawned. This seems to apply to objects that can't be picked up that loads with a new region.
		if (config.GroundObjectSpawned()) {
			send("***GroundObjectSpawned***", "START");

			send("groundObjectSpawned.toString", groundObjectSpawned.toString());
			//Output: GroundObjectSpawned(tile=rl5@b13689, groundObject=ku@6c2c39e2)

			//groundObjectSpawned.getGroundObject()
			//groundObjectSpawned.getTile()

			send("***GroundObjectSpawned***", "END");
		}
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplatApplied) throws IllegalAccessException {
		//Get HitsplatApplied.
		if (config.HitsplatApplied()) {
			send("***HitsplatApplied***", "START");

			send("hitsplatApplied.toString", hitsplatApplied.toString());
			//Output: HitsplatApplied(actor=ds@6d546707, hitsplat=net.runelite.api.Hitsplat@2b4f7e0e)

			Hitsplat hitsplat = hitsplatApplied.getHitsplat();
			send("hitsplat.getAmount", hitsplat.getAmount());
			//Output: 3

			send("hitsplat.getHitsplatType", hitsplat.getHitsplatType());
			//Output: 16

			send("hitsplat.isMine", hitsplat.isMine());
			//Output: true

			send("hitsplat.getDisappearsOnGameCycle", hitsplat.getDisappearsOnGameCycle());
			//Output: 2735

			Actor actor = hitsplatApplied.getActor();
			send("actor.getName", actor.getName());
			//Output: Imp



			//Use the HitsplatName class to
			String hitsplatName = new HitsplatName().nameLookup(hitsplat.getHitsplatType());
			send("hitsplatName", hitsplatName);
			//Output: DAMAGE_ME



			String hitBy = "someone else";
			if (hitsplat.isMine()) {hitBy = "me";}
			send("Hit", actor.getName() + " was hit by " + hitBy + " for " + hitsplat.getAmount() + " damage, type " + hitsplatName + " (" + hitsplat.getHitsplatType() + ").");
			//Output: Imp was hit by me for 3 damage, type DAMAGE_ME (16).

			send("***HitsplatApplied***", "END");
		}
	}

	//SKIPPED: InteractingChanged

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged) {
		//Get ItemContainerChanged. This is what's in the player's inventory or bank.
		// I think the ID number indicated what kind of contain it is. 93 = player's inventory.
		if (config.ItemContainerChanged()) {
			send("***ItemContainerChanged***", "START");

			send("itemContainerChanged.toString", itemContainerChanged.toString());
			//Output:

			send("itemContainerChanged.getContainerId", itemContainerChanged.getContainerId());
			//Output: 93



			ItemContainer itemContainer = itemContainerChanged.getItemContainer();
			send("itemContainer.size", itemContainer.size());
			//Output: 7

			send("itemContainer.getID", itemContainer.getId());
			//Output: 93

			send("itemContainer.count", itemContainer.count());
			//Output: 7

			send("itemContainer.contains(1779)", itemContainer.contains(1779));
			//Output: true

			send("itemContainer.count(1779)", itemContainer.count(1779));
			//Output: 5

			Item[] items = itemContainer.getItems();
			for (int i = 0; i < itemContainer.size(); i++) {
				send("Item # " + i, "ID: " + items[i].getId() + ", Quantity: " + items[i].getQuantity());
			}
			//Output: #0 ID: 8007, Quantity: 6
			//Output: #1 ID: 13124, Quantity: 1
			//Output: #2 ID: 1779, Quantity: 1
			//Output: #3 ID: 1779, Quantity: 1
			//Output: #4 ID: 1779, Quantity: 1
			//Output: #5 ID: 1779, Quantity: 1
			//Output: #6 ID: 1779, Quantity: 1

			send("***ItemContainerChanged***", "END");
		}
	}

	@Subscribe
	public void onItemDespawned(ItemDespawned itemDespawned) {
		//Get ItemDespawned. This includes picking up an item from the ground.
		if (config.ItemDespawned()) {
			send("***ItemDespawned***", "START");

			TileItem tileItem = itemDespawned.getItem();
			send("tileItem.getId", tileItem.getId());
			//Output: 1779

			send("tileItem.getQuantity", tileItem.getQuantity());
			//Output: 1

			send("***ItemDespawned***", "END");
		}
	}

	@Subscribe
	public void onItemQuantityChanged(ItemQuantityChanged itemQuantityChanged) {
		//Get ItemQuantityChanged.
		if (config.ItemQuantityChanged()) {
			send("***ItemQuantityChanged***", "START");

			send("itemQuantityChanged.toString", itemQuantityChanged.toString());
			//Output:

			send("itemQuantityChanged.getOldQuantity", itemQuantityChanged.getOldQuantity());
			//Output:

			send("itemQuantityChanged.getNewQuantity", itemQuantityChanged.getNewQuantity());
			//Output:

			TileItem tileItem = itemQuantityChanged.getItem();
			send("tileItem.getId", tileItem.getId());
			//Output:

			send("tileItem.getQuantity", tileItem.getQuantity());
			//Output:

			send("***ItemQuantityChanged***", "END");
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

			//This is the "value" of the item; not the GE price.
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

			//int, gives the item ID
			send("item.getId", item.getId());
			//Output: 1779

			//int, seems to be 1 no matter if you drop multiple items or stackable items
			send("item.getQuantity", item.getQuantity());
			//Output: 1

			send("item.getModelHeight", item.getModelHeight());
			//Output: 1000



			//Get a string with the tile and item.
			String itemString = itemSpawned.toString();
			send("itemString", itemString);
			//Output: ItemSpawned(tile=rl5@18cb9706, item=el@30137bef)

			send("***ItemSpawned***", "END");
		}
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded menuEntryAdded) {
		//Get MenuEntryAdded.
		if (config.MenuEntryAdded()) {
			send("***MenuEntryAdded***", "START");

			send("menuEntryAdded.toString", menuEntryAdded.toString());
			//Output: MenuEntryAdded(getOption=Walk here, getTarget=, getType=23, getIdentifier=0, getActionParam0=370, getActionParam1=916)

			send("menuEntryAdded.getActionParam0", menuEntryAdded.getActionParam0());
			//Output: 370

			send("menuEntryAdded.getActionParam1", menuEntryAdded.getActionParam1());
			//Output: 916

			send("menuEntryAdded.getIdentifier", menuEntryAdded.getIdentifier());
			//Output: 0

			send("menuEntryAdded.getType", menuEntryAdded.getType());
			//Output: 23

			send("menuEntryAdded.getOption", menuEntryAdded.getOption());
			//Output: Walk here

			send("menuEntryAdded.getTarget", menuEntryAdded.getTarget());
			//Output:

			MenuEntry menuEntry = menuEntryAdded.getMenuEntry();
			send("menuEntry.getIdentifier", menuEntry.getIdentifier());
			//Output: 0

			send("menuEntry.getItemId", menuEntry.getItemId());
			//Output: -1

			send("menuEntry.getItemOp", menuEntry.getItemOp());
			//Output: -1

			send("menuEntry.getOption", menuEntry.getOption());
			//Output: Walk here

			send("menuEntry.getParam0", menuEntry.getParam0());
			//Output: 370

			send("menuEntry.getParam1", menuEntry.getParam1());
			//Output: 916

			send("menuEntry.getTarget", menuEntry.getTarget());
			//Output:

			send("menuEntry.isDeprioritized", menuEntry.isDeprioritized());
			//Output: false

			send("menuEntry.isForceLeftClick", menuEntry.isForceLeftClick());
			//Output: false

			send("menuEntry.isItemOp", menuEntry.isItemOp());
			//Output: false

			MenuAction menuAction = menuEntry.getType();
			send("menuAction.toString", menuAction.toString());
			//Output: WALK

			send("menuAction.getId", menuAction.getId());
			//Output: 23

			send("menuAction.name", menuAction.name());
			//Output: WALK

			send("***MenuEntryAdded***", "END");
		}
	}

	@Subscribe
	public void onMenuOpened(MenuOpened menuOpened) {
		//Get MenuOpened.
		if (config.MenuOpened()) {
			send("***MenuOpened***", "START");

			send("menuOpened.toString", menuOpened.toString());
			//Output: (THIS IS A VERY, VERY LONG STRING. IT INCLUDES EVERY MENU OPTION.)

			MenuEntry menuFirstEntry = menuOpened.getFirstEntry();
			send("menuEntry.getIdentifier", menuFirstEntry.getIdentifier());
			//Output: 0

			send("menuEntry.getItemId", menuFirstEntry.getItemId());
			//Output: 1779

			send("menuEntry.getItemOp", menuFirstEntry.getItemOp());
			//Output: -1

			send("menuEntry.getOption", menuFirstEntry.getOption());
			//Output: Use

			send("menuEntry.getParam0", menuFirstEntry.getParam0());
			//Output: 9

			send("menuEntry.getParam1", menuFirstEntry.getParam1());
			//Output: 9764864

			send("menuEntry.getTarget", menuFirstEntry.getTarget());
			//Output: Flax

			send("menuEntry.isDeprioritized", menuFirstEntry.isDeprioritized());
			//Output: false

			send("menuEntry.isForceLeftClick", menuFirstEntry.isForceLeftClick());
			//Output: false

			send("menuEntry.isItemOp", menuFirstEntry.isItemOp());
			//Output: false

			MenuAction menuAction = menuFirstEntry.getType();
			send("menuAction.toString", menuAction.toString());
			//Output: WIDGET_TARGET

			send("menuAction.getId", menuAction.getId());
			//Output: 25

			send("menuAction.name", menuAction.name());
			//Output: WIDGET_TARGET

			MenuEntry[] menuEntries = menuOpened.getMenuEntries();
			send("menuEntries.length", menuEntries.length);
			//Output: 4

			for (int i = 0; i < menuEntries.length; i++) {
				send("menuEntries[" + i + "].getIdentifier", menuEntries[i].getIdentifier());
				send("menuEntries[" + i + "].getParam0", menuEntries[i].getParam0());
				send("menuEntries[" + i + "].getParam1", menuEntries[i].getParam1());
			}
			//Output: 10
			//Output: 9
			//Output: 9764864

			send("***MenuOpened***", "END");
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked menuOptionClicked) {
		//Get MenuOptionClicked.
		if (config.MenuOptionClicked()) {
			send("***MenuOptionClicked***", "START");

			send("menuOptionClicked.toString", menuOptionClicked.toString());
			//Output:

			send("menuOptionClicked.getMenuOption", menuOptionClicked.getMenuOption());
			//Output:

			send("menuOptionClicked.getMenuTarget", menuOptionClicked.getMenuTarget());
			//Output:

			send("menuOptionClicked.getParam0", menuOptionClicked.getParam0());
			//Output:

			send("menuOptionClicked.getParam1", menuOptionClicked.getParam1());
			//Output:

			send("***MenuOptionClicked***", "END");
		}
	}

	@Subscribe
	public void onMenuShouldLeftClick(MenuShouldLeftClick menuShouldLeftClick) {
		//Get MenuShouldLeftClick.
		if (config.MenuShouldLeftClick()) {
			send("***MenuShouldLeftClick***", "START");

			send("menuShouldLeftClick.toString", menuShouldLeftClick.toString());
			//Output:

			send("menuShouldLeftClick.isForceRightClick", menuShouldLeftClick.isForceRightClick());
			//Output:

			send("***MenuShouldLeftClick***", "END");
		}
	}

	@Subscribe
	public void onNameableNameChanged(NameableNameChanged nameableNameChanged) {
		//Get NameableNameChanged.
		if (config.NameableNameChanged()) {
			send("***NameableNameChanged***", "START");

			send("nameableNameChanged.toString", nameableNameChanged.toString());
			//Output:

			Nameable nameable = nameableNameChanged.getNameable();
			send("nameable.getName", nameable.getName());
			//Output:

			send("nameable.getPrevName", nameable.getPrevName());
			//Output:

			send("***NameableNameChanged***", "END");
		}
	}

	@Subscribe
	public void onNpcChanged(NpcChanged npcChanged) {
		//Get NpcChanged.
		if (config.NpcChanged()) {
			send("***NpcChanged***", "START");

			send("npcChanged.toString", npcChanged.toString());
			//Output:

			NPC npc = npcChanged.getNpc();
			send("npc.getName", npc.getName());
			//Output:

			send("npc.getIndex", npc.getIndex());
			//Output:

			send("npc.getId", npc.getId());
			//Output:

			send("npc.getCombatLevel", npc.getCombatLevel());
			//Output:

			NPCComposition npcComposition = npcChanged.getOld();
			send("npcComposition.getName", npcComposition.getName());
			//Output:

			send("***NpcChanged***", "END");
		}
	}

	//SKIPPED: NpcDespawned

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned) {
		//Get NpcSpawned.
		if (config.NpcSpawned()) {
			send("***NpcSpawned***", "START");

			send("npcSpawned.toString", npcSpawned.toString());
			//Output:

			NPC npc = npcSpawned.getNpc();
			send("npc.getName", npc.getName());
			//Output:

			send("npc.getIndex", npc.getIndex());
			//Output:

			send("npc.getId", npc.getId());
			//Output:

			send("npc.getCombatLevel", npc.getCombatLevel());
			//Output:

			NpcOverrides npcOverrides = npc.getChatheadOverrides();
            if (npcOverrides != null) {
                short[] colorToReplaceWith = npcOverrides.getColorToReplaceWith();
				for (int i = 0; i < colorToReplaceWith.length; i++) {
					send("colorToReplaceWith # " + i, colorToReplaceWith[i]);
				}

				int[] modelIds = npcOverrides.getModelIds();
				for (int i = 0; i < modelIds.length; i++) {
					send("modelIds # " + i, modelIds[i]);
				}

				short[] textureToReplaceWith = npcOverrides.getTextureToReplaceWith();
				for (int i = 0; i < textureToReplaceWith.length; i++) {
					send("textureToReplaceWith # " + i, textureToReplaceWith[i]);
				}
            }

			NPCComposition npcComposition = npc.getComposition();
			send("npcComposition.isVisible", npcComposition.isVisible());
			//Output:

			send("npcComposition.isMinimapVisible", npcComposition.isMinimapVisible());
			//Output:

			send("npcComposition.isInteractible", npcComposition.isInteractible());
			//Output:

			send("npcComposition.isFollower", npcComposition.isFollower());
			//Output:

			send("npcComposition.getWidthScale", npcComposition.getWidthScale());
			//Output:

			send("npcComposition.getSize", npcComposition.getSize());
			//Output:

			send("npcComposition.getName", npcComposition.getName());
			//Output:

			send("npcComposition.getId", npcComposition.getId());
			//Output:

			send("npcComposition.getHeightScale", npcComposition.getHeightScale());
			//Output:

			send("npcComposition.getCombatLevel", npcComposition.getCombatLevel());
			//Output:

			int[] models = npcComposition.getModels();
			for (int i = 0; i < models.length; i++) {
				send("configs # " + i, models[i]);
			}

			int[] configs = npcComposition.getConfigs();
			for (int i = 0; i < configs.length; i++) {
				send("configs # " + i, configs[i]);
			}

			short[] colorToReplaceWith = npcComposition.getColorToReplaceWith();
			for (int i = 0; i < colorToReplaceWith.length; i++) {
				send("colorsToReplaceWith # " + i, colorToReplaceWith[i]);
			}

			short[] colorToReplace = npcComposition.getColorToReplace();
			for (int i = 0; i < colorToReplace.length; i++) {
				send("colorsToReplace # " + i, colorToReplace[i]);
			}

			String[] actions = npcComposition.getActions();
			for (int i = 0; i < actions.length; i++) {
				send("actions # " + i, actions[i]);
			}

			//Actor actor =

            send("***NpcSpawned***", "END");
		}
	}

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/

	/*@Subscribe
	public void on(XXXTemplate xxxTemplate) {
		//Get XXXTemplate.
		if (config.XXXTemplate()) {
			send("***XXXTemplate***", "START");

			//Output:

			send("***XXXTemplate***", "END");
		}
	}*/



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

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
}
