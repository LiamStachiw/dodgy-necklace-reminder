package com.dodgynecklacereminder;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Dodgy Necklace Reminder"
)
public class DodgyNecklaceReminderPlugin extends Plugin
{
	private final int DODGY_NECKLACE_ID = 21143;

	private boolean inventoryHasDodgyNecklace = false;
	private boolean dodgyNecklaceEquipped = false;

	@Inject
	private Client client;

	@Inject
	private Notifier notifier;

	@Inject
	private DodgyNecklaceReminderConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private DodgyNecklaceReminderOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Dodgy Necklace Reminder started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Dodgy Necklace Reminder stopped!");
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged){
		if (itemContainerChanged.getContainerId() == InventoryID.INVENTORY.getId() && itemContainerChanged.getItemContainer().contains(DODGY_NECKLACE_ID)){
			inventoryHasDodgyNecklace = true;
			if(!dodgyNecklaceEquipped){
				overlayManager.add(overlay);
			}
		} else if (itemContainerChanged.getContainerId() == InventoryID.INVENTORY.getId() && !itemContainerChanged.getItemContainer().contains(DODGY_NECKLACE_ID)){
			inventoryHasDodgyNecklace = false;
			overlayManager.remove(overlay);
		}


		if (itemContainerChanged.getContainerId() == InventoryID.EQUIPMENT.getId() && itemContainerChanged.getItemContainer().contains(DODGY_NECKLACE_ID)){
			overlayManager.remove(overlay);
			dodgyNecklaceEquipped = true;
		} else if (itemContainerChanged.getContainerId() == InventoryID.EQUIPMENT.getId() && !itemContainerChanged.getItemContainer().contains(DODGY_NECKLACE_ID) && inventoryHasDodgyNecklace){
			overlayManager.add(overlay);
			dodgyNecklaceEquipped = false;
			notifier.notify("Equip a new dodgy necklace!");
		}
	}

	@Provides
	DodgyNecklaceReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DodgyNecklaceReminderConfig.class);
	}
}
