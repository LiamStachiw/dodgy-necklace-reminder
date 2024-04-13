package com.dodgynecklacereminder;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class DodgyNecklaceReminderOverlay extends Overlay {

    private final DodgyNecklaceReminderConfig config;
    private final Client client;
    private final PanelComponent panelComponent = new PanelComponent();


    @Inject
    private DodgyNecklaceReminderOverlay(Client client, DodgyNecklaceReminderConfig config){
        setPosition(OverlayPosition.TOP_CENTER);
        this.config = config;
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics){
        panelComponent.getChildren().clear();

        panelComponent.getChildren().clear();

        for(int i = 0; i < config.overlayHeight(); i++){
            panelComponent.getChildren().add(LineComponent.builder().build());
        }

        // Build overlay title
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(config.reminderText())
                .color(config.textColor())
                .build());

        for(int i = 0; i < config.overlayHeight(); i++){
            panelComponent.getChildren().add(LineComponent.builder().build());
        }

        // Set the size of the overlay (width)
        panelComponent.setPreferredSize(new Dimension(
                client.getViewportWidth(),
                client.getViewportHeight()));

        panelComponent.setBackgroundColor(config.backgroundColor());

        return panelComponent.render(graphics);
    }
}
