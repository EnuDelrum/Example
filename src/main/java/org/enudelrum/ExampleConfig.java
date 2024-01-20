package org.enudelrum;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Enu's Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}

	@ConfigItem(
			keyName = "ItemSpawned",
			name = "ItemSpawned",
			description = "Output ItemSpawned"
	)
	default boolean ItemSpawned() { return false; }

	@ConfigItem(
			keyName = "PlayerLootReceived",
			name = "PlayerLootReceived",
			description = "Output PlayerLootReceived"
	)
	default boolean PlayerLootReceived() { return false; }

	@ConfigItem(
			keyName = "GameObjectSpawned",
			name = "GameObjectSpawned",
			description = "Output GameObjectSpawned"
	)
	default boolean GameObjectSpawned() { return false; }

}
