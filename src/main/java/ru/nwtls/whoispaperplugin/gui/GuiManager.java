package ru.nwtls.whoispaperplugin.gui;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.WhoisPlugin;

import java.util.HashMap;
import java.util.UUID;

public class GuiManager {
    private final HashMap<UUID, Gui> guis = new HashMap<>();
    private static final WhoisPlugin plugin = WhoisPlugin.getInstance();

    public GuiManager() {

    }

    public void addGui(@NotNull Player player, @NotNull Gui gui) {
        guis.put(player.getUniqueId(), gui);
    }

    public Gui getGui(@NotNull Player player) {
        return guis.get(player.getUniqueId());
    }

    public Gui getGui(@NotNull UUID uuid) {
        System.out.println(guis);
        System.out.println(guis.get(uuid).getButtons());
        return guis.get(uuid);
    }

    /**
     *
     * @param player target
     * @param gui gui
     */

    public void showGui(@NotNull Player player, @NotNull Gui gui) {
        player.openInventory(gui.getInventory());
        guis.put(player.getUniqueId(), gui);
        player.setMetadata("profile", new FixedMetadataValue(plugin, true));
    }

    public void updateGui(@NotNull Player player, @NotNull Gui gui) {
        if (guis.get(player.getUniqueId()) == null) {
            System.out.println("Gui does not exists");
            return;
        }
        System.out.println("GUI!!!!! " + gui);
        guis.put(player.getUniqueId(), gui);
        System.out.println("GUUUI " + guis.get(player.getUniqueId()));
    }

    public void closeGui(@NotNull Player player) {
        if (!(player.hasMetadata("profile"))) return;
        player.removeMetadata("profile", plugin);
        guis.remove(player.getName());
    }

    public HashMap<UUID, Gui> getGuis() {
        return guis;
    }
}
