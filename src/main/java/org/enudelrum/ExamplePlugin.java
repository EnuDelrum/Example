package org.enudelrum;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Field;
import java.util.List;
import javax.inject.Inject;
import javax.naming.Name;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.kit.KitType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.PlayerLootReceived;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Enu's Example",
	description = "Enu Delrum's example plugin.",
	tags = {"config", "menu"},
	loadWhenOutdated = true,
	enabledByDefault = true
)

public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	//@Inject
	//private ExampleOverlay overlay;

	@Inject
	private ExampleConfig config;

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		System.out.println("Example started!");
		//PluginPanel pluginPanel = new PluginPanel();
		//overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		//overlayManager.remove(overlay);
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

	public void send(String category, double message) {
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
	@Subscribe
	public void onChatMessage(ChatMessage chatMessage) {
		//Get ChatMessage. The output is printed to console instead of sending it to the chat box (or else RuneLite would go into a loop and crash).
		if (config.ChatMessage()) {
			System.out.println("***ChatMessage***" + "START");


			System.out.println("chatMessage.getMessage: " + chatMessage.getMessage());
			//Output: Test

			System.out.println("chatMessage.getName: " + chatMessage.getName());
			//Output: Enu Delrum

			System.out.println("chatMessage.getSender: " + chatMessage.getSender());
			//Output: null

			System.out.println("chatMessage.getTimestamp: " + chatMessage.getTimestamp());
			//Output: 1706594443

			MessageNode messageNode = chatMessage.getMessageNode();
			System.out.println("messageNode.getId: " + messageNode.getId());
			//Output: 3

			System.out.println("messageNode.getRuneLiteFormatMessage: " + messageNode.getRuneLiteFormatMessage());
			//Output: null

			System.out.println("messageNode.getValue: " + messageNode.getValue());
			//Output: X

			ChatMessageType chatMessageType = messageNode.getType();
			System.out.println("chatMessage.getName: " + chatMessage.getName());
			//Output: Enu Delrum

			System.out.println("chatMessageType.toString: " + chatMessageType.toString());
			//Output: PUBLICCHAT


			System.out.println("***ChatMessage***" + "END");

			/*
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
			*/
		}
	}

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
			//Output: ItemContainerChanged(containerId=93, itemContainer=da@43c4b82a)

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
			//Output: MenuOptionClicked(getParam0=52, getParam1=55, getMenuOption=Collect, getMenuTarget=Grand Exchange booth, GetMenuAction=GAME_OBJECT_THIRD_OPTION, getId=10061)

			send("menuOptionClicked.getMenuOption", menuOptionClicked.getMenuOption());
			//Output: Collect

			send("menuOptionClicked.getMenuTarget", menuOptionClicked.getMenuTarget());
			//Output: Grand Exchange booth

			send("menuOptionClicked.getParam0", menuOptionClicked.getParam0());
			//Output: 52

			send("menuOptionClicked.getParam1", menuOptionClicked.getParam1());
			//Output: 55

			send("***MenuOptionClicked***", "END");
		}
	}

	@Subscribe
	public void onMenuShouldLeftClick(MenuShouldLeftClick menuShouldLeftClick) {
		//Get MenuShouldLeftClick.
		if (config.MenuShouldLeftClick()) {
			send("***MenuShouldLeftClick***", "START");

			send("menuShouldLeftClick.toString", menuShouldLeftClick.toString());
			//Output: MenuShouldLeftClick(forceRightClick=false)

			send("menuShouldLeftClick.isForceRightClick", menuShouldLeftClick.isForceRightClick());
			//Output: false

			send("***MenuShouldLeftClick***", "END");
		}
	}

	@Subscribe
	public void onNameableNameChanged(NameableNameChanged nameableNameChanged) {
		//Get NameableNameChanged.
		if (config.NameableNameChanged()) {
			send("***NameableNameChanged***", "START");

			send("nameableNameChanged.toString", nameableNameChanged.toString());
			//Output: NameableNameChanged(nameable=rm@3bbe015c)

			Nameable nameable = nameableNameChanged.getNameable();
			send("nameable.getName", nameable.getName());
			//Output: Abu Delrum

			send("nameable.getPrevName", nameable.getPrevName());
			//Output: null

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
			//Output: NpcSpawned(npc=ds@2f54a9e8)

			NPC npc = npcSpawned.getNpc();
			send("npc.getName", npc.getName());
			//Output: Guard

			send("npc.getIndex", npc.getIndex());
			//Output: 23768

			send("npc.getId", npc.getId());
			//Output: 11911

			send("npc.getCombatLevel", npc.getCombatLevel());
			//Output: 21

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
			//Output:

			NPCComposition npcComposition = npc.getComposition();
			send("npcComposition.isVisible", npcComposition.isVisible());
			//Output: false

			send("npcComposition.isMinimapVisible", npcComposition.isMinimapVisible());
			//Output: true

			send("npcComposition.isInteractible", npcComposition.isInteractible());
			//Output: true

			send("npcComposition.isFollower", npcComposition.isFollower());
			//Output: false

			send("npcComposition.getWidthScale", npcComposition.getWidthScale());
			//Output: 128

			send("npcComposition.getSize", npcComposition.getSize());
			//Output: 1

			send("npcComposition.getName", npcComposition.getName());
			//Output: Guard

			send("npcComposition.getId", npcComposition.getId());
			//Output: 11911

			send("npcComposition.getHeightScale", npcComposition.getHeightScale());
			//Output: 128

			send("npcComposition.getCombatLevel", npcComposition.getCombatLevel());
			//Output: 21

			int[] models = npcComposition.getModels();
			if (models != null) {
				for (int i = 0; i < models.length; i++) {
					send("models # " + i, models[i]);
				}
			}
			//Output: #0: 46760
			//Output: #1: 46761
			//Output: #2: 176
			//Output: #3: 46762
			//Output: #4: 219
			//Output: #5: 4954
			//Output: #6: 25684
			//Output: #7: 246
			//Output: #8: 518


			int[] configs = npcComposition.getConfigs();
			if (configs != null) {
				for (int i = 0; i < configs.length; i++) {
					send("configs # " + i, configs[i]);
				}
			}
			//Output:

			short[] colorToReplaceWith = npcComposition.getColorToReplaceWith();
			if (colorToReplaceWith != null) {
				for (int i = 0; i < colorToReplaceWith.length; i++) {
					send("colorsToReplaceWith # " + i, colorToReplaceWith[i]);
				}
			}
			//Output: #0: 24

			short[] colorToReplace = npcComposition.getColorToReplace();
			if (colorToReplace != null) {
				for (int i = 0; i < colorToReplace.length; i++) {
					send("colorsToReplace # " + i, colorToReplace[i]);
				}
			}
			//Output: #0: 8741

			String[] actions = npcComposition.getActions();
			for (int i = 0; i < actions.length; i++) {
				send("actions # " + i, actions[i]);
			}
			//Output: #0: null
			//Output: #1: Attack
			//Output: #2: Pickpocket
			//Output: #3: null
			//Output: #4: null

			Actor actor = npcSpawned.getActor();
			send("actor.getName", actor.getName());
			//Output: Guard

            send("***NpcSpawned***", "END");
		}
	}

	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged overheadTextChanged) {
		//Get OverheadTextChanged.
		if (config.OverheadTextChanged()) {
			send("***OverheadTextChanged***", "START");

			send("overheadTextChanged.toString", overheadTextChanged.toString());
			//Output: OverheadTextChanged(actor=dn@19345bd4, overheadText: But it is dope)

			send("overheadTextChanged.getOverheadText", overheadTextChanged.getOverheadText());
			//Output: But it is dope

			Actor actor = overheadTextChanged.getActor();
			send("actor.getName", actor.getName());
			//Output: Trip2Much

			send("***OverheadTextChanged***", "END");
		}
	}

	@Subscribe
	public void onPlayerChanged(PlayerChanged playerChanged) {
		//Get PlayerChanged.
		if (config.PlayerChanged()) {
			send("***PlayerChanged***", "START");

			send("playerChanged.toString", playerChanged.toString());
			//Output: PlayerChanged=dn@14854e8c)

			Player player = playerChanged.getPlayer();

			//Player extends Actor.
			send("player.getName", player.getName());
			//Output: Enu Delrum

			send("player.isDead", player.isDead());
			//Output: false

			send("player.isInteracting", player.isInteracting());
			//Output: false

			//Back to Player methods.
			send("player.getId", player.getId());
			//Output: 608

			send("player.getCombatLevel", player.getCombatLevel());
			//Output: 126

			send("player.getTeam", player.getTeam());
			//Output: 0

			send("player.isClanMember", player.isClanMember());
			//Output: false

			send("player.isFriend", player.isFriend());
			//Output: false

			send("player.isFriendsChatMember", player.isFriendsChatMember());
			//Output: false

			HeadIcon headIcon = playerChanged.getPlayer().getOverheadIcon();
			if (headIcon != null) {
				send("headIcon.name", headIcon.name());
				//Output:

				send("headIcon.toString", headIcon.toString());
				//Output:
			}

			SkullIcon skullIcon = playerChanged.getPlayer().getSkullIcon();
            if (skullIcon != null) {
                send("skullIcon.toString", skullIcon.toString());
				//Output:

				send("skullIcon.toString", skullIcon.name());
				//Output:
            }

			PlayerComposition playerComposition = playerChanged.getPlayer().getPlayerComposition();
			send("playerComposition.getGender", playerComposition.getGender());
			//Output: 0

			//This doesn't seem to be useful. I'm not sure sure what these IDs are, but they're not item or model IDs.
			int[] equipmentIds = playerComposition.getEquipmentIds();
			for (int i = 0; i < equipmentIds.length; i++) {
				send("equipmentIds # " + i, equipmentIds[i]);
			}
			//Output: #0: 10966
			//Output: #1: 10272
			//Output: #2: 23498
			//Output: #3: 9353
			//Output: #4: 10186
			//Output: #5: 13343
			//Output: #6: 0
			//Output: #7: 10188
			//Output: #8: 0
			//Output: #9: 20509
			//Output: #10: 23466
			//Output: #11: 369

			send("playerComposition.getKitId(KitType.WEAPON)", playerComposition.getKitId(KitType.WEAPON));
			//Output: -1

			send("playerComposition.getEquipmentId(KitType.WEAPON)", playerComposition.getEquipmentId(KitType.WEAPON));
			//Output: 8841

			int[] colors = playerComposition.getColors();
			for (int i = 0; i < colors.length; i++) {
				send("colors # " + i, colors[i]);
			}
			//Output: #0: 0
			//Output: #1: 0
			//Output: #2: 4
			//Output: #3: 4
			//Output: #4: 0

			send("***PlayerChanged***", "END");
		}
	}

	@Subscribe
	public void onPlayerDespawned(PlayerDespawned playerDespawned) {
		//Get PlayerDespawned. There's more examples in onPlayerChanged.
		if (config.PlayerDespawned()) {
			send("***PlayerDespawned***", "START");

			Player player = playerDespawned.getPlayer();
			send("player.getName", player.getName());
			//Output: De Dog226

			send("***PlayerDespawned***", "END");
		}
	}

	@Subscribe
	public void onPlayerMenuOptionsChanged(PlayerMenuOptionsChanged playerMenuOptionsChanged) {
		//Get PlayerMenuOptionsChanged.
		if (config.PlayerMenuOptionsChanged()) {
			send("***PlayerMenuOptionsChanged***", "START");

			send("playerMenuOptionsChanged.toString", playerMenuOptionsChanged.toString());
			//Output: PlayerMenuOptionsChanged(index=1)

			send("playerMenuOptionsChanged.getIndex", playerMenuOptionsChanged.getIndex());
			//Output: 1

			send("***PlayerMenuOptionsChanged***", "END");
		}
	}

	@Subscribe
	public void onPlayerSpawned(PlayerSpawned playerSpawned) {
		//Get PlayerSpawned. There's more examples in onPlayerChanged.
		if (config.PlayerSpawned()) {
			send("***PlayerSpawned***", "START");

			Player player = playerSpawned.getPlayer();
			send("player.getName", player.getName());
			//Output: PKerNova

			send("***PlayerSpawned***", "END");
		}
	}

	//SKIPPED: PostAnimation, PostClientTick

	@Subscribe
	public void onPostHealthBar(PostHealthBar postHealthBar) {
		//Get PostHealthBar. This only triggered the first time the healthbar was drawn.
		if (config.PostHealthBar()) {
			send("***PostHealthBar***", "START");

			send("postHealthBar.toString", postHealthBar.toString());
			//Output: PostHealthBar(healthBar=gd@cd21716)

			HealthBar healthBar = postHealthBar.getHealthBar();
			send("healthBar.getHealthBarFrontSpriteId", healthBar.getHealthBarFrontSpriteId());
			//Output: 2176

			//Set healthBar padding. This doesn't seem to do anything.
			//healthBar.setPadding(25);

			SpritePixels healthBarBack = healthBar.getHealthBarBackSprite();
			send("healthBarBack.getWidth", healthBarBack.getWidth());
			//Output: 30

			SpritePixels healthBarFront = healthBar.getHealthBarFrontSprite();
			send("healthBarFront.getWidth", healthBarFront.getWidth());
			//Output: 30

			send("***PostHealthBar***", "END");
		}
	}

	@Subscribe
	public void onPostItemComposition(PostItemComposition postItemComposition) {
		//Get PostItemComposition.
		if (config.PostItemComposition()) {
			send("***PostItemComposition***", "START");

			send("postItemComposition.toString", postItemComposition.toString());
			//Output: PostItemComposition(itemComposition=hp@29d84de4)

			ItemComposition itemComposition = postItemComposition.getItemComposition();
			send("itemComposition.getName", itemComposition.getName());
			//Output: Burnt bread

			//Add the ID after the name. The new name is keep after picking it up.
			// Dropping an item doesn't trigger this event.
			itemComposition.setName(itemComposition.getName() + " (" + itemComposition.getId() + ")");
			//Item Name: Burnt bread (2311)

			send("***PostItemComposition***", "END");
		}
	}

	@Subscribe
	public void onPostMenuSort(PostMenuSort postMenuSort) {
		//Get PostMenuSort.
		if (config.PostMenuSort()) {
			send("***PostMenuSort***", "START");

			send("postMenuSort.toString", postMenuSort.toString());
			//Output: net.runelite.api.events.PostMenuSort@6683c371

			send("***PostMenuSort***", "END");
		}
	}

	@Subscribe
	public void onPostObjectComposition(PostObjectComposition postObjectComposition) {
		//Get PostObjectComposition. Trying to use this event almost always caused RuneLite to crash.
		if (config.PostObjectComposition()) {
			/*
			send("***PostObjectComposition***", "START");

			//send("postObjectComposition.toString", postObjectComposition.toString());
			//Output:

			ObjectComposition objectComposition = postObjectComposition.getObjectComposition();
			send("objectComposition.getId", objectComposition.getId());
			//Output:

			send("objectComposition.getMapIconId", objectComposition.getMapIconId());
			//Output:

			send("objectComposition.getMapIconId", objectComposition.getMapIconId());
			//Output:

			send("objectComposition.getMapSceneId", objectComposition.getMapSceneId());
			//Output:

			send("objectComposition.getName", objectComposition.getName());
			//Output:

			send("objectComposition.getVarbitId", objectComposition.getVarbitId());
			//Output:

			send("objectComposition.getVarPlayerId", objectComposition.getVarPlayerId());
			//Output:

			String[] actions = objectComposition.getActions();
			if (actions != null) {
				for (int i = 0; i < actions.length; i++) {
					send("actions # " + i, actions[i]);
				}
			}
			//Output:

			int[] impostorIds = objectComposition.getImpostorIds();
			if (impostorIds != null) {
				for (int i = 0; i < impostorIds.length; i++) {
					send("impostorIds # " + i, impostorIds[i]);
				}
			}
			//Output:

			send("***PostObjectComposition***", "END");
			*/
		}
	}

	@Subscribe
	public void onPostStructComposition(PostStructComposition postStructComposition) {
		//Get PostStructComposition.
		if (config.PostStructComposition()) {
			send("***PostStructComposition***", "START");

			send("postStructComposition.toString", postStructComposition.toString());
			//Output: PostStructComposition(structComposition=hi@5a15dfb4)

			StructComposition structComposition = postStructComposition.getStructComposition();
			send("structComposition.getId", structComposition.getId());
			//Output: 3773

			send("structComposition.toString", structComposition.toString());
			//Output: hi@5a15dfb4

			send("***PostStructComposition***", "END");
		}
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved projectileMoved) {
		//Get ProjectileMoved.
		if (config.ProjectileMoved()) {
			send("***ProjectileMoved***", "START");

			send("projectileMoved.toString", projectileMoved.toString());
			//Output: ProjectileMoved(projectile=cx@2513e3ad, position=LocalPoint(x=5864, y=5864), z=-401

			send("projectileMoved.getZ", projectileMoved.getZ());
			//Output: -401

			Projectile projectile = projectileMoved.getProjectile();
			//projectile.



			//*****TO BE COMPLETED IN MORE DEPTH*****
			//*****TO BE COMPLETED IN MORE DEPTH*****
			//*****TO BE COMPLETED IN MORE DEPTH*****
			//*****TO BE COMPLETED IN MORE DEPTH*****
			//*****TO BE COMPLETED IN MORE DEPTH*****
			//*****TO BE COMPLETED IN MORE DEPTH*****
			//*****TO BE COMPLETED IN MORE DEPTH*****



			send("***ProjectileMoved***", "END");
		}
	}

	//SKIPPED: RemovedFriend

	@Subscribe
	public void onResizeableChanged(ResizeableChanged resizeableChanged) {
		//Get ResizeableChanged. This event doesn't seem to ever be triggered.
		if (config.ResizeableChanged()) {
			send("***ResizeableChanged***", "START");

			send("resizeableChanged.toString", resizeableChanged.toString());
			//Output:

			send("resizeableChanged.isResized", resizeableChanged.isResized());
			//Output:

			send("***ResizeableChanged***", "END");
		}
	}

	@Subscribe
	public void onScriptCallbackEvent(ScriptCallbackEvent ScriptCallbackEvent) {
		//Get ScriptCallbackEvent.
		if (config.ScriptCallbackEvent()) {
			/*
			send("***ScriptCallbackEvent***", "START");

			send("ScriptCallbackEvent.toString", ScriptCallbackEvent.toString());
			//Output:

			send("ScriptCallbackEvent.getEventName", ScriptCallbackEvent.getEventName());
			//Output:

			send("***ScriptCallbackEvent***", "END");
			*/
		}
	}

	@Subscribe
	public void onScriptPostFired(ScriptPostFired scriptPostFired) {
		//Get ScriptPostFired.
		if (config.ScriptPostFired()) {
			send("***ScriptPostFired***", "START");

			send("scriptPostFired.toString", scriptPostFired.toString());
			//Output: ScriptPostFired(scriptId=3302)

			send("scriptPostFired.getScriptId", scriptPostFired.getScriptId());
			//Output: 3302

			send("***ScriptPostFired***", "END");
		}
	}

	@Subscribe
	public void onScriptPreFired(ScriptPreFired scriptPreFired) {
		//Get ScriptPreFired.
		if (config.ScriptPreFired()) {
			send("***ScriptPreFired***", "START");

			send("scriptPreFired.toString", scriptPreFired.toString());
			//Output: ScriptPreFired(scriptId=180, scriptEvent=null)

			send("scriptPreFired.getScriptId", scriptPreFired.getScriptId());
			//Output: 180

			ScriptEvent scriptEvent = scriptPreFired.getScriptEvent();
			if (scriptEvent != null) {
				send("scriptEvent.getMouseX & getMouseY", scriptEvent.getMouseX() + ", " + scriptEvent.getMouseY());
			}
			//Output:

			send("***ScriptPreFired***", "END");
		}
	}

	@Subscribe
	public void onSoundEffectPlayed(SoundEffectPlayed soundEffectPlayed) {
		//Get SoundEffectPlayed.
		if (config.SoundEffectPlayed()) {
			send("***SoundEffectPlayed***", "START");

			send("soundEffectPlayed.toString", soundEffectPlayed.toString());
			//Output: SoundEffectPlayed(source=null, soundId=2266, delay=0, consumed=false)

			send("soundEffectPlayed.isConsumed", soundEffectPlayed.isConsumed());
			//Output: false

			send("soundEffectPlayed.getSoundId", soundEffectPlayed.getSoundId());
			//Output: 2266

			send("soundEffectPlayed.getDelay", soundEffectPlayed.getDelay());
			//Output: 0

			Actor actor = soundEffectPlayed.getSource();
            if (actor != null) {
                send("actor.getName", actor.getName());
            }
            //Output:

			send("***SoundEffectPlayed***", "END");
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged) {
		//Get StatChanged.
		if (config.StatChanged()) {
			send("***StatChanged***", "START");

			send("statChanged.toString", statChanged.toString());
			//Output: StatChanged(skill=PRAYER, xp=13085294, level=99, boostedLevel=98)

			send("statChanged.getBoostedLevel", statChanged.getBoostedLevel());
			//Output: 98

			send("statChanged.getLevel", statChanged.getLevel());
			//Output: 99

			send("statChanged.getXp", statChanged.getXp());
			//Output: 13085294

			Skill skill = statChanged.getSkill();
			send("skill.getName", skill.getName());
			//Output: Prayer

			send("skill.toString", skill.toString());
			//Output: PRAYER

			send("***StatChanged***", "END");
		}
	}

	@Subscribe
	public void onUsernameChanged(UsernameChanged usernameChanged) {
		//Get UsernameChanged.
		if (config.UsernameChanged()) {
			send("***UsernameChanged***", "START");

			send("usernameChanged.toString", usernameChanged.toString());
			//Output:

			send("***UsernameChanged***", "END");
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged) {
		//Get VarbitChanged.
		if (config.VarbitChanged()) {
			send("***VarbitChanged***", "START");

			send("varbitChanged.toString", varbitChanged.toString());
			//Output: VarbitChanged(varpId=2855, varbitId=9657, value=32)

			send("varbitChanged.getValue", varbitChanged.getValue());
			//Output: 32

			send("varbitChanged.getVarbitId", varbitChanged.getVarbitId());
			//Output: 9657

			send("varbitChanged.getVarpId", varbitChanged.getVarpId());
			//Output: 2855

			send("***VarbitChanged***", "END");
		}
	}

	@Subscribe
	public void onVarClientIntChanged(VarClientIntChanged varClientIntChanged) {
		//Get VarClientIntChanged.
		if (config.VarClientIntChanged()) {
			send("***VarClientIntChanged***", "START");

			send("varClientIntChanged.toString", varClientIntChanged.toString());
			//Output: VarClientIntChanged(index=1112)

			send("***VarClientIntChanged***", "END");
		}
	}

	@Subscribe
	public void onVarClientStrChanged(VarClientStrChanged varClientStrChanged) {
		//Get VarClientStrChanged.
		if (config.VarClientStrChanged()) {
			send("***VarClientStrChanged***", "START");

			send("varClientStrChanged.toString", varClientStrChanged.toString());
			//Output: VarClientStrChanged(index=359)

			send("***VarClientStrChanged***", "END");
		}
	}

	@Subscribe
	public void onVolumeChanged(VolumeChanged volumeChanged) {
		//Get VolumeChanged.
		if (config.VolumeChanged()) {
			send("***VolumeChanged***", "START");

			send("volumeChanged.toString", volumeChanged.toString());
			//Output: VolumeChanged(type=MUSIC)

			send("volumeChanged.getType().toString", volumeChanged.getType().toString());
			//Output: MUSIC

			send("volumeChanged.getType().name", volumeChanged.getType().name());
			//Output: MUSIC

			send("String.valueOf(VolumeChanged.Type.valueOf(\"AREA\"))", String.valueOf(VolumeChanged.Type.valueOf("AREA")));
			//Output:
			send("String.valueOf(VolumeChanged.Type.valueOf(\"EFFECTS\"))", String.valueOf(VolumeChanged.Type.valueOf("EFFECTS")));
			//Output:
			send("String.valueOf(VolumeChanged.Type.valueOf(\"MUSIC\"))", String.valueOf(VolumeChanged.Type.valueOf("MUSIC")));
			//Output:

			send("***VolumeChanged***", "END");
		}
	}

	//SKIPPED: WallObjectDespawned

	@Subscribe
	public void onWallObjectSpawned(WallObjectSpawned wallObjectSpawned) {
		//Get WallObjectSpawned.
		if (config.WallObjectSpawned()) {
			send("***WallObjectSpawned***", "START");

			if (config.x1()) {
				send("wallObjectSpawned.toString", wallObjectSpawned.toString());
				//Output: WallObjectSpawned(tile=rl5@76d41c93, wallObject=lv@6a3a3400)
			}

			WallObject wallObject = wallObjectSpawned.getWallObject();
			//if (wallObject != null) {send("wallObject", "not null");}

			Tile tile = wallObjectSpawned.getTile();
			//if (tile != null) {send("tile", "not null");}
			if (config.x1()) {
				send("tile.toString", tile.toString());
				//Output: rl5@76d41c93

				send("tile.getPlane", tile.getPlane());
				//Output: 3

				send("tile.getRenderLevel", tile.getRenderLevel());
				//Output: 3
			}

			//extends TileObject
			DecorativeObject decorativeObject = tile.getDecorativeObject();
			if (decorativeObject != null) {
				//send("decorativeObject", "not null");
				if (config.x2()) {
					send("decorativeObject.toString", decorativeObject.toString());
					//Output: lu@568887da

					send("decorativeObject.getXOffset", decorativeObject.getXOffset());
					//Output: 16

					send("decorativeObject.getYOffset", decorativeObject.getYOffset());
					//Output: 0
				}

				Shape shape = decorativeObject.getConvexHull();
				//if (shape != null) {send("shape", "not null");}
				if (config.x3()) {
					send("shape.toString", shape.toString());
					//Output: net.runelite.api.geometry.SimplePolygon@5267d123
				}

				Rectangle rectangle = shape.getBounds();
				if (rectangle != null) {
					//send("rectangle", "not null");
					if (config.x4()) {
						send("rectangle.toString", rectangle.toString());
						//Output: java.awt.Rectangle[x=939,y=-300,width=0,height=0]
						send("rectangle.getHeight", rectangle.getHeight());
						//Output: 0.0
						send("rectangle.height", rectangle.height);
						//Output: 0
						send("rectangle.getWidth", rectangle.getWidth());
						//Output: 0.0
						send("rectangle.width", rectangle.width);
						//Output: 0
						send("rectangle.getX", rectangle.getX());
						//Output: 939.0
						send("rectangle.x", rectangle.x);
						//Output: 939
						send("rectangle.getY", rectangle.getY());
						//Output: -300.0
						send("rectangle.y", rectangle.y);
						//Output: -300
					}
				}

				Rectangle2D rectangle2D = shape.getBounds2D();
				//if (rectangle2D != null) {send("rectangle2D", "not null");}
				if (config.x5()) {
					send("rectangle2D.toString", rectangle2D.toString());
					//Output: java.awt.geom.Rectangle2D$Float[x=768.0,y=-299,w=0.0,h=0.0]
					send("rectangle2D.getHeight", rectangle2D.getHeight());
					//Output: 0.0
					send("rectangle2D.getWidth", rectangle2D.getWidth());
					//Output: 0.0
					send("rectangle2D.getX", rectangle2D.getX());
					//Output: 768.0
					send("rectangle2D.getY", rectangle2D.getY());
					//Output: -299.0
				}

				Renderable renderable = decorativeObject.getRenderable();
				//if (renderable != null) {send("renderable", "not null");}
				if (config.x6()) {
					send("renderable.toString", renderable.toString());
					//Output: ko@5c2efb4b
					send("renderable.getModelHeight", renderable.getModelHeight());
					//Output: 1000
				}

				Model model = renderable.getModel();
				if (model != null) {
					//send("model", "not null");
					if (config.x7()) {
						send("model.toString", model.toString());
						//Output: ko@21dcc409
						send("model.getBottomY", model.getBottomY());
						//Output: 0
						send("model.getBufferOffset", model.getBufferOffset());
						//Output: 0
						send("model.getDiameter", model.getDiameter());
						//Output: 538
						send("model.getRadius", model.getRadius());
						//Output: 365
						send("model.getSceneId", model.getSceneId());
						//Output: 0
						send("model.getUvBufferOffset", model.getUvBufferOffset());
						//Output: 0
						send("model.getXYZMag", model.getXYZMag());
						//Output: 173
						send("model.isClickable", model.isClickable());
						//Output: false
					}
				}
			}

			//extends TileObject
			GameObject[] gameObjects = tile.getGameObjects();
			//if (gameObjects != null) {send("gameObjects", "not null");}
			if (config.x8()) {
				for (int i = 0; i < gameObjects.length; i++) {
					send("gameObjects # " + i + ".toString", gameObjects[i].toString());
					//Output: ll@6062f923
				}
			}

			//I don't think I'm using this method correctly.
			//TileItem extends Renderable
			List<TileItem> list = tile.getGroundItems();
			//if (list != null) {send("list", "not null");}
			if (config.x9()) {
				send("list.toString", list.toString());
				//Output:
			}

			//extends TileObject
			GroundObject groundObject = tile.getGroundObject();
			//if (groundObject != null) {send("groundObject", "not null");}
			if (config.x10()) {
				send("groundObject.toString", groundObject.toString());
				//Output: ku@1aafd7ef
				send("groundObject.getConfig", groundObject.getConfig());
				//Output: 342
				send("groundObject.getId", groundObject.getId());
				//Output: 768
			}

			//extends TileObject
			ItemLayer itemLayer = tile.getItemLayer();
			//if (itemLayer != null) {send("itemLayer", "not null");}
			if (config.x11()) {
				send("itemLayer.toString", itemLayer.toString());
				//Output:
				send("itemLayer.getHeight", itemLayer.getHeight());
				//Output:
			}

			if (itemLayer != null) {
				Renderable renderableBottom = itemLayer.getBottom();
				Model modelBottom = renderableBottom.getModel();
				Renderable renderableMiddle = itemLayer.getMiddle();
				Model modelMiddle = renderableMiddle.getModel();
				Renderable renderableTop = itemLayer.getTop();
				Model modelTop = renderableTop.getModel();


				if (config.x12()) {
					send("modelBottom.toString", modelBottom.toString());
					//Output:
					send("modelBottom.getSceneId", modelBottom.getSceneId());
					//Output:

					send("modelMiddle.toString", modelMiddle.toString());
					//Output:
					send("modelMiddle.getSceneId", modelMiddle.getSceneId());
					//Output:

					send("modelTop.toString", modelTop.toString());
					//Output:
					send("modelTop.getSceneId", modelTop.getSceneId());
					//Output:
				}
			}

			LocalPoint localPoint = tile.getLocalLocation();
			//if (localPoint != null) {send("localPoint", "not null");}
			if (config.x13()) {
				send("localPoint.toString", localPoint.toString());
				//Output: LocalPoint(x=12480, y=320)
				send("localPoint.getSceneX", localPoint.getSceneX());
				//Output: 97
				send("localPoint.getSceneY", localPoint.getSceneY());
				//Output: 2
				send("localPoint.getX", localPoint.getX());
				//Output: 12480
				send("localPoint.getY", localPoint.getY());
				//Output: 320
				send("localPoint.isInScene", localPoint.isInScene());
				//Output: true
			}

			Point point = tile.getSceneLocation();
			//if (point != null) {send("point", "not null");}
			if (config.x14()) {
				send("point.toString", point.toString());
				//Output: Point(x=97, y=2)
				send("point.getX", point.getX());
				//Output: 97
				send("point.getY", point.getY());
				//Output: 2
			}

			SceneTileModel sceneTileModel = tile.getSceneTileModel();
			//if (sceneTileModel != null) {send("sceneTileModel", "not null");}
			if (config.x15()) {
				send("sceneTileModel.toString", sceneTileModel.toString());
				//Output:
				send("sceneTileModel.getBufferLen", sceneTileModel.getBufferLen());
				//Output:
				send("sceneTileModel.getBufferOffset", sceneTileModel.getBufferOffset());
				//Output:
				send("sceneTileModel.getModelOverlay", sceneTileModel.getModelOverlay());
				//Output:
				send("sceneTileModel.getModelUnderlay", sceneTileModel.getModelUnderlay());
				//Output:
				send("sceneTileModel.getRotation", sceneTileModel.getRotation());
				//Output:
				send("sceneTileModel.getShape", sceneTileModel.getShape());
				//Output:
				send("sceneTileModel.getUvBufferOffset", sceneTileModel.getUvBufferOffset());
				//Output:
				send("sceneTileModel.isFlat", sceneTileModel.isFlat());
				//Output:
			}

			SceneTilePaint sceneTilePaint = tile.getSceneTilePaint();
			//if (sceneTilePaint != null) {send("sceneTilePaint", "not null");}
			if (config.x16()) {
				send("sceneTilePaint.toString", sceneTilePaint.toString());
				//Output: hk@eea59d4
				send("sceneTilePaint.getBufferLen", sceneTilePaint.getBufferLen());
				//Output: 6
				send("sceneTilePaint.getBufferOffset", sceneTilePaint.getBufferOffset());
				//Output: 2392599
				send("sceneTilePaint.getNeColor", sceneTilePaint.getNeColor());
				//Output: 8335
				send("sceneTilePaint.getNwColor", sceneTilePaint.getNwColor());
				//Output: 8333
				send("sceneTilePaint.getRBG", sceneTilePaint.getRBG());
				//Output: 6051909
				send("sceneTilePaint.getSeColor", sceneTilePaint.getSeColor());
				//Output: 8330
				send("sceneTilePaint.getSwColor", sceneTilePaint.getSwColor());
				//Output: 8336
				send("sceneTilePaint.getTexture", sceneTilePaint.getTexture());
				//Output: -1
				send("sceneTilePaint.getUvBufferOffset", sceneTilePaint.getUvBufferOffset());
				//Output: -1
				send("sceneTilePaint.isFlat", sceneTilePaint.isFlat());
				//Output: true
			}

			WallObject wallObject2 = tile.getWallObject();
			//if (wallObject2 != null) {send("wallObject2", "not null");}
			if (config.x17()) {
				send("wallObject2.toString", wallObject2.toString());
				//Output: lv@4abdc1ce
				send("wallObject2.getConfig", wallObject2.getConfig());
				//Output: 256
				send("wallObject2.getOrientationA", wallObject2.getOrientationA());
				//Output: 1
				send("wallObject2.getOrientationB", wallObject2.getOrientationB());
				//Output: 0
			}

			WorldPoint worldPoint = tile.getWorldLocation();
			//if (worldPoint != null) {send("worldPoint", "not null");}
			if (config.x18()) {
				send("worldPoint.toString", worldPoint.toString());
				//Output: WorldPoint(x=3001, y=3178, plane=2)
				send("worldPoint.getPlane", worldPoint.getPlane());
				//Output: 2
				send("worldPoint.getRegionID", worldPoint.getRegionID());
				//Output: 11825
				send("worldPoint.getRegionX", worldPoint.getRegionX());
				//Output: 57
				send("worldPoint.getRegionY", worldPoint.getRegionY());
				//Output: 42
				send("worldPoint.getX", worldPoint.getX());
				//Output: 3001
				send("worldPoint.getY", worldPoint.getY());
				//Output: 3178
				send("worldPoint.isInScene(client)", worldPoint.isInScene(client));
				//Output: false
			}

			WorldArea worldArea = worldPoint.toWorldArea();
			if (worldArea != null) {
				//send("worldArea", "not null");
				if (config.x19()) {
					send("worldArea.toString", worldArea.toString());
					//Output: net.runelite.api.coords.WorldArea@503754d
					send("worldArea.getHeight", worldArea.getHeight());
					//Output: 1
					send("worldArea.getPlane", worldArea.getPlane());
					//Output: 2
					send("worldArea.getX", worldArea.getX());
					//Output: 3001
					send("worldArea.getY", worldArea.getY());
					//Output: 3178
				}
			}

			send("***WallObjectSpawned***", "END");
		}
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed widgetClosed) {
		//Get WidgetClosed.
		if (config.WidgetClosed()) {
			send("***WidgetClosed***", "START");

			send("widgetClosed.toString", widgetClosed.toString());
			//Output: WidgetClosed(groupID=160, modalMode=1, unload=false)

			send("widgetClosed.getGroupId", widgetClosed.getGroupId());
			//Output: 160

			send("widgetClosed.getModalMode", widgetClosed.getModalMode());
			//Output: 1

			send("widgetClosed.isUnload", widgetClosed.isUnload());
			//Output: false

			send("***WidgetClosed***", "END");
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widgetLoaded) {
		//Get WidgetLoaded.
		if (config.WidgetLoaded()) {
			send("***WidgetLoaded***", "START");

			send("widgetLoaded.toString", widgetLoaded.toString());
			//Output: WidgetLoaded(groupID=163)

			send("widgetLoaded.getGroupId", widgetLoaded.getGroupId());
			//Output: 163

			send("***WidgetLoaded***", "END");
		}
	}

	@Subscribe
	public void onWorldChanged(WorldChanged worldChanged) {
		//Get WorldChanged.
		if (config.WorldChanged()) {
			send("***WorldChanged***", "START");

			send("worldChanged.toString", worldChanged.toString());
			//Output:

			send("***WorldChanged***", "END");
		}
	}

	@Subscribe
	public void onWorldListLoad(WorldListLoad worldListLoad) {
		//Get WorldListLoad.
		if (config.WorldListLoad()) {
			send("***WorldListLoad***", "START");

			send("worldListLoad.toString", worldListLoad.toString());
			//Output: (This is a very, very long string.)

			World[] worlds = worldListLoad.getWorlds();
			send("worlds.length", worlds.length);
			//Output: 231
			send("worlds[0].toString()", worlds[0].toString());
			//Output: ch@286cdf89
			send("worlds[0].getActivity()", worlds[0].getActivity());
			//Output: 500 skill total
			send("worlds[0].getAddress()", worlds[0].getAddress());
			//Output: oldschool168.runescape.com
			send("worlds[0].getId()", worlds[0].getId());
			//Output: 468
			send("worlds[0].getIndex()", worlds[0].getIndex());
			//Output: 0
			send("worlds[0].getLocation()", worlds[0].getLocation());
			//Output: 0
			send("worlds[0].getPlayerCount()", worlds[0].getPlayerCount());
			//Output: 74

			send("***WorldListLoad***", "END");
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
}
