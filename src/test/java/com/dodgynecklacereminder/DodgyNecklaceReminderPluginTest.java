package com.dodgynecklacereminder;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DodgyNecklaceReminderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DodgyNecklaceReminderPlugin.class);
		RuneLite.main(args);
	}
}