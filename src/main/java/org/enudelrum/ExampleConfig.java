package org.enudelrum;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{
	@ConfigItem(
			keyName = "AccountHashChanged",
			name = "AccountHashChanged",
			description = "Output AccountHashChanged"
	)
	default boolean AccountHashChanged() { return false; }

	@ConfigItem(
			keyName = "ActorDeath",
			name = "ActorDeath",
			description = "Output ActorDeath"
	)
	default boolean ActorDeath() { return false; }

	@ConfigItem(
			keyName = "AmbientSoundEffectCreated",
			name = "AmbientSoundEffectCreated",
			description = "Output AmbientSoundEffectCreated"
	)
	default boolean AmbientSoundEffectCreated() { return false; }

	@ConfigItem(
			keyName = "AnimationChanged",
			name = "AnimationChanged",
			description = "Output AnimationChanged"
	)
	default boolean AnimationChanged() { return false; }

	@ConfigItem(
			keyName = "AreaSoundEffectPlayed",
			name = "AreaSoundEffectPlayed",
			description = "Output AreaSoundEffectPlayed"
	)
	default boolean AreaSoundEffectPlayed() { return false; }

	@ConfigItem(
			keyName = "BeforeMenuRender",
			name = "BeforeMenuRender",
			description = "Output BeforeMenuRender"
	)
	default boolean BeforeMenuRender() { return false; }

	@ConfigItem(
			keyName = "BeforeRender",
			name = "BeforeRender",
			description = "Output BeforeRender"
	)
	default boolean BeforeRender() { return false; }

	@ConfigItem(
			keyName = "CanvasSizeChanged",
			name = "CanvasSizeChanged",
			description = "Output CanvasSizeChanged"
	)
	default boolean CanvasSizeChanged() { return false; }

	@ConfigItem(
			keyName = "ChatMessage",
			name = "ChatMessage",
			description = "Output ChatMessage"
	)
	default boolean ChatMessage() { return false; }

	//SKIPPED: ClanChannelChanged, ClanMemberJoined, ClanMemberLeft

	@ConfigItem(
			keyName = "ClientTick",
			name = "ClientTick",
			description = "Output ClientTick"
	)
	default boolean ClientTick() { return false; }

	@ConfigItem(
			keyName = "CommandExecuted",
			name = "CommandExecuted",
			description = "Output CommandExecuted"
	)
	default boolean CommandExecuted() { return false; }

	//SKIPPED: DecorativeObjectDespawned

	@ConfigItem(
			keyName = "DecorativeObjectSpawned",
			name = "DecorativeObjectSpawned",
			description = "Output DecorativeObjectSpawned"
	)
	default boolean DecorativeObjectSpawned() { return false; }

	@ConfigItem(
			keyName = "DraggingWidgetChanged",
			name = "DraggingWidgetChanged",
			description = "Output DraggingWidgetChanged"
	)
	default boolean DraggingWidgetChanged() { return false; }

	@ConfigItem(
			keyName = "FakeXpDrop",
			name = "FakeXpDrop",
			description = "Output FakeXpDrop"
	)
	default boolean FakeXpDrop() { return false; }

	@ConfigItem(
			keyName = "FocusChanged",
			name = "FocusChanged",
			description = "Output FocusChanged"
	)
	default boolean FocusChanged() { return false; }

	//SKIPPED: FriendsChatChanged, FriendsChatMemberJoined, FriendsChatMemberLeft, GameObjectDespawned

	@ConfigItem(
			keyName = "GameObjectSpawned",
			name = "GameObjectSpawned",
			description = "Output GameObjectSpawned"
	)
	default boolean GameObjectSpawned() { return false; }

	@ConfigItem(
			keyName = "GameStateChanged",
			name = "GameStateChanged",
			description = "Output GameStateChanged"
	)
	default boolean GameStateChanged() { return false; }

	@ConfigItem(
			keyName = "GameTick",
			name = "GameTick",
			description = "Output GameTick"
	)
	default boolean GameTick() { return false; }

	@ConfigItem(
			keyName = "GrandExchangeOfferChanged",
			name = "GrandExchangeOfferChanged",
			description = "Output GrandExchangeOfferChanged"
	)
	default boolean GrandExchangeOfferChanged() { return false; }

	@ConfigItem(
			keyName = "GrandExchangeSearched",
			name = "GrandExchangeSearched",
			description = "Output GrandExchangeSearched"
	)
	default boolean GrandExchangeSearched() { return false; }

	//SKIPPED: GraphicChanged, GraphicsObjectCreated, GroundObjectDespawned

	@ConfigItem(
			keyName = "GroundObjectSpawned",
			name = "GroundObjectSpawned",
			description = "Output GroundObjectSpawned"
	)
	default boolean GroundObjectSpawned() { return false; }

	@ConfigItem(
			keyName = "HitsplatApplied",
			name = "HitsplatApplied",
			description = "Output HitsplatApplied"
	)
	default boolean HitsplatApplied() { return false; }

	//SKIPPED: InteractingChanged

	@ConfigItem(
			keyName = "ItemContainerChanged",
			name = "ItemContainerChanged",
			description = "Output ItemContainerChanged"
	)
	default boolean ItemContainerChanged() { return false; }

	@ConfigItem(
			keyName = "ItemDespawned",
			name = "ItemDespawned",
			description = "Output ItemDespawned"
	)
	default boolean ItemDespawned() { return false; }

	@ConfigItem(
			keyName = "ItemQuantityChanged",
			name = "ItemQuantityChanged",
			description = "Output ItemQuantityChanged"
	)
	default boolean ItemQuantityChanged() { return false; }

	@ConfigItem(
			keyName = "ItemSpawned",
			name = "ItemSpawned",
			description = "Output ItemSpawned"
	)
	default boolean ItemSpawned() { return false; }

	@ConfigItem(
			keyName = "MenuEntryAdded",
			name = "MenuEntryAdded",
			description = "Output MenuEntryAdded"
	)
	default boolean MenuEntryAdded() { return false; }

	@ConfigItem(
			keyName = "MenuOpened",
			name = "MenuOpened",
			description = "Output MenuOpened"
	)
	default boolean MenuOpened() { return false; }

	@ConfigItem(
			keyName = "MenuOptionClicked",
			name = "MenuOptionClicked",
			description = "Output MenuOptionClicked"
	)
	default boolean MenuOptionClicked() { return false; }

	@ConfigItem(
			keyName = "MenuShouldLeftClick",
			name = "MenuShouldLeftClick",
			description = "Output MenuShouldLeftClick"
	)
	default boolean MenuShouldLeftClick() { return false; }

	@ConfigItem(
			keyName = "NameableNameChanged",
			name = "NameableNameChanged",
			description = "Output NameableNameChanged"
	)
	default boolean NameableNameChanged() { return false; }

	@ConfigItem(
			keyName = "NpcChanged",
			name = "NpcChanged",
			description = "Output NpcChanged"
	)
	default boolean NpcChanged() { return false; }

	//SKIPPED: NpcDespawned

	@ConfigItem(
			keyName = "NpcSpawned",
			name = "NpcSpawned",
			description = "Output NpcSpawned"
	)
	default boolean NpcSpawned() { return false; }

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/






	@ConfigItem(
			keyName = "PlayerLootReceived",
			name = "PlayerLootReceived",
			description = "Output PlayerLootReceived"
	)
	default boolean PlayerLootReceived() { return false; }



}
