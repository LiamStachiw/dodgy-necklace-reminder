package com.dodgynecklacereminder;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

import java.awt.Color;

@ConfigGroup("dodgynecklacereminder")
public interface DodgyNecklaceReminderConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "reminderText",
		name = "Reminder Text",
		description = "The reminder text to display on the screen"
	)
	default String reminderText()
	{
		return "Equip a dodgy necklace!";
	}

	@ConfigItem(
			position = 1,
			keyName = "textColor",
			name = "Text Color",
			description = "The color of the reminder text to display on the screen"
	)
	default Color textColor() { return Color.YELLOW; }

	@ConfigItem(
			position = 2,
			keyName = "backgroundColor",
			name = "Background Color",
			description = "The background color of the reminder textbox to display on the screen"
	)
	default Color backgroundColor() { return Color.BLACK; }


	@Range(
			min = 0,
			max = 30
	)
	@ConfigItem(
			position = 3,
			keyName = "overlayHeight",
			name = "Overlay Height",
			description = "Set the height of the text box overlay"
	)
	default int overlayHeight() { return 5; }
}
