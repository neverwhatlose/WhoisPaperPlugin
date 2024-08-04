package ru.nwtls.whoispaperplugin.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class Gui implements InventoryHolder {
    private final Inventory inventory;
    private final int rows;
    private final Component title;

    public Gui(@NotNull Component title, @Range(from = 1, to = 6) int rows) {
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, rows * 9, title);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    //todo: setButton(row, column, new Button(abstract class???)), close(target?), show()
}
