package ru.nwtls.whoispaperplugin.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nwtls.whoispaperplugin.WhoisPlugin;

import java.util.HashMap;
import java.util.List;

public class GuiManager {
    private static final HashMap<String, Gui> guis = new HashMap<>();
    private static final WhoisPlugin plugin = WhoisPlugin.getInstance();

    public GuiManager() {

    }

    public void addGui(@NotNull String nickname, @NotNull Gui gui) {
        guis.put(nickname, gui);
    }

    public Gui getGui(@NotNull Player player) {
        return guis.get(player.getName());
    }

    /**
     *
     * @param player target
     * @param gui gui
     */
    public void showGui(@NotNull Player player, @NotNull Gui gui) {
        player.openInventory(gui.getInventory());
        player.setMetadata("profile", new FixedMetadataValue(plugin, true));
    }

    public void closeInventory(@NotNull Player player) {
        if (!(player.hasMetadata("profile"))) return;
        player.removeMetadata("profile", plugin);
        guis.remove(player.getName());
    }
}
