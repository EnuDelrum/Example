package org.enudelrum;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{
	@ConfigItem(
			keyName = "AccountHashChanged",
			name = "*AccountHashChanged (Not Triggered)",
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
			name = "*FakeXpDrop (Not Triggered)",
			description = "Output FakeXpDrop"
	)
	default boolean FakeXpDrop() { return false; }

	@ConfigItem(
			keyName = "FocusChanged",
			name = "*FocusChanged (Not Triggered)",
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
			name = "*ItemQuantityChanged (Not Triggered)",
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

	@ConfigItem(
			keyName = "OverheadTextChanged",
			name = "OverheadTextChanged",
			description = "Output OverheadTextChanged"
	)
	default boolean OverheadTextChanged() { return false; }

	@ConfigItem(
			keyName = "PlayerChanged",
			name = "PlayerChanged",
			description = "Output PlayerChanged"
	)
	default boolean PlayerChanged() { return false; }

	@ConfigItem(
			keyName = "PlayerDespawned",
			name = "PlayerDespawned",
			description = "Output PlayerDespawned"
	)
	default boolean PlayerDespawned() { return false; }

	@ConfigItem(
			keyName = "PlayerMenuOptionsChanged",
			name = "PlayerMenuOptionsChanged",
			description = "Output PlayerMenuOptionsChanged"
	)
	default boolean PlayerMenuOptionsChanged() { return false; }

	@ConfigItem(
			keyName = "PlayerSpawned",
			name = "PlayerSpawned",
			description = "Output PlayerSpawned"
	)
	default boolean PlayerSpawned() { return false; }

	//SKIPPED: PostAnimation, PostClientTick

	@ConfigItem(
			keyName = "PostHealthBar",
			name = "PostHealthBar",
			description = "Output PostHealthBar"
	)
	default boolean PostHealthBar() { return false; }

	@ConfigItem(
			keyName = "PostItemComposition",
			name = "PostItemComposition",
			description = "Output PostItemComposition"
	)
	default boolean PostItemComposition() { return false; }

	@ConfigItem(
			keyName = "PostMenuSort",
			name = "PostMenuSort",
			description = "Output PostMenuSort"
	)
	default boolean PostMenuSort() { return false; }

	@ConfigItem(
			keyName = "PostObjectComposition",
			name = "**PostObjectComposition (Crash)",
			description = "Output PostObjectComposition"
	)
	default boolean PostObjectComposition() { return false; }

	@ConfigItem(
			keyName = "PostStructComposition",
			name = "PostStructComposition",
			description = "Output PostStructComposition"
	)
	default boolean PostStructComposition() { return false; }

	@ConfigItem(
			keyName = "ProjectileMoved",
			name = "ProjectileMoved",
			description = "Output ProjectileMoved"
	)
	default boolean ProjectileMoved() { return false; }

	//SKIPPED: RemovedFriend

	@ConfigItem(
			keyName = "ResizeableChanged",
			name = "*ResizeableChanged (Not Triggered)",
			description = "Output ResizeableChanged"
	)
	default boolean ResizeableChanged() { return false; }

	@ConfigItem(
			keyName = "ScriptCallbackEvent",
			name = "**ScriptCallbackEvent (Crash)",
			description = "Output ScriptCallbackEvent"
	)
	default boolean ScriptCallbackEvent() { return false; }

	@ConfigItem(
			keyName = "ScriptPostFired",
			name = "ScriptPostFired",
			description = "Output ScriptPostFired"
	)
	default boolean ScriptPostFired() { return false; }

	@ConfigItem(
			keyName = "ScriptPreFired",
			name = "ScriptPreFired",
			description = "Output ScriptPreFired"
	)
	default boolean ScriptPreFired() { return false; }

	@ConfigItem(
			keyName = "SoundEffectPlayed",
			name = "SoundEffectPlayed",
			description = "Output SoundEffectPlayed"
	)
	default boolean SoundEffectPlayed() { return false; }

	@ConfigItem(
			keyName = "StatChanged",
			name = "StatChanged",
			description = "Output StatChanged"
	)
	default boolean StatChanged() { return false; }

	@ConfigItem(
			keyName = "UsernameChanged",
			name = "*UsernameChanged (Not Triggered)",
			description = "Output UsernameChanged"
	)
	default boolean UsernameChanged() { return false; }

	@ConfigItem(
			keyName = "VarbitChanged",
			name = "VarbitChanged",
			description = "Output VarbitChanged"
	)
	default boolean VarbitChanged() { return false; }

	@ConfigItem(
			keyName = "VarClientIntChanged",
			name = "VarClientIntChanged",
			description = "Output VarClientIntChanged"
	)
	default boolean VarClientIntChanged() { return false; }

	@ConfigItem(
			keyName = "VarClientStrChanged",
			name = "VarClientStrChanged",
			description = "Output VarClientStrChanged"
	)
	default boolean VarClientStrChanged() { return false; }

	@ConfigItem(
			keyName = "VolumeChanged",
			name = "VolumeChanged",
			description = "Output VolumeChanged"
	)
	default boolean VolumeChanged() { return false; }

	@ConfigItem(
			keyName = "WallObjectSpawned",
			name = "WallObjectSpawned",
			description = "Output WallObjectSpawned"
	)
	default boolean WallObjectSpawned() { return false; }

	@ConfigItem(
			keyName = "WidgetClosed",
			name = "WidgetClosed",
			description = "Output WidgetClosed"
	)
	default boolean WidgetClosed() { return false; }

	@ConfigItem(
			keyName = "WidgetLoaded",
			name = "WidgetLoaded",
			description = "Output WidgetLoaded"
	)
	default boolean WidgetLoaded() { return false; }

	@ConfigItem(
			keyName = "WorldChanged",
			name = "*WorldChanged (Not Triggered)",
			description = "Output WorldChanged"
	)
	default boolean WorldChanged() { return false; }

	@ConfigItem(
			keyName = "WorldListLoad",
			name = "WorldListLoad",
			description = "Output WorldListLoad"
	)
	default boolean WorldListLoad() { return false; }

	/*@ConfigItem(
			keyName = "XXXTemplate",
			name = "XXXTemplate",
			description = "Output XXXTemplate"
	)
	default boolean XXXTemplate() { return false; }*/
}
