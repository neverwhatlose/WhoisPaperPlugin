package ru.nwtls.whoispaperplugin.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.WhoisPlugin;

public class GuiListener implements Listener {
    private static final @NotNull WhoisPlugin plugin = WhoisPlugin.getInstance();

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {
        Inventory inv = event.getClickedInventory();
        if (inv == null || event.getWhoClicked().getMetadata("profile").isEmpty()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onClose(@NotNull InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasMetadata("profile")) {
            player.removeMetadata("profile", plugin);
        }
    }

    @EventHandler
    public void onPlayerLeave(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();
        // todo: По-хорошему реализовать в Gui closeInventory()
        if (!(player.hasMetadata("profile"))) return;
        player.removeMetadata("profile", plugin);
    }
}
