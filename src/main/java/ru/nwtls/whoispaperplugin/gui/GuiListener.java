package ru.nwtls.whoispaperplugin.gui;

import com.google.gson.internal.bind.util.ISO8601Utils;
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
    private static final @NotNull GuiManager manager = WhoisPlugin.getManager();

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {
        Inventory inv = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        if (inv == null || player.getMetadata("profile").isEmpty()) return;
        event.setCancelled(true);

        Gui gui = manager.getGui(player.getUniqueId());

        Button button = gui.getButtonById(event.getSlot());
        if (button == null) return;
        button.execute(manager, player);
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
