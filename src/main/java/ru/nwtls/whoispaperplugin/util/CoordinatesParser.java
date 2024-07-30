package ru.nwtls.whoispaperplugin.util;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoordinatesParser {
    public static String CoordinatesToString(@NotNull Player player) {
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        return "x" + x + "/y" + y + "/z" + z + "/" + player.getLocation().getWorld().getName();
    }

    public static String RespawnToString(@NotNull Player player) {
        if (player.getRespawnLocation() == null) {
            return "не установлена";
        }
        int x = player.getRespawnLocation().getBlockX();
        int y = player.getRespawnLocation().getBlockY();
        int z = player.getRespawnLocation().getBlockZ();

        return "x" + x + "/y" + y + "/z" + z + "/" + player.getLocation().getWorld().getName();
    }
}
