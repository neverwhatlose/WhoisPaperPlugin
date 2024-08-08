package ru.nwtls.whoispaperplugin.gui;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.WhoisPlugin;

import java.util.List;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.*;

public class GuiListener implements Listener {
    private static final @NotNull WhoisPlugin plugin = WhoisPlugin.getInstance();
    private final @NotNull GuiManager manager = new GuiManager();

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {
        Inventory inv = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        if (inv == null || player.getMetadata("profile").isEmpty()) return;
        event.setCancelled(true);

        if (manager.getGui(player).getButtonById(event.getSlot()) == null) return;
        Button button = manager.getGui(player).getButtonById(event.getSlot());
        System.out.println(button.getTitle());
    }

//    @EventHandler
//    public void onClose(@NotNull InventoryCloseEvent event) {
//        Inventory inv = event.getInventory();
//        if (!(inv.getHolder() instanceof Gui gui)) return;
//        gui = (Gui) event.getInventory().getHolder();
//        gui.closeInventory((Player) event.getPlayer());
//    }

    @EventHandler
    public void onPlayerLeave(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!(player.hasMetadata("profile"))) return;
        player.removeMetadata("profile", plugin);
    }
}
