package ru.nwtls.whoispaperplugin.gui;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Contract;
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

    public int getRows() {
        return rows;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @CanIgnoreReturnValue
    @Contract("_, _, _ -> this")
    public @NotNull Gui setButton(@Range(from = 0, to = 5) int row, @Range(from = 0, to = 8) int column, @NotNull Button button) {
        if (checkRange(this, row)) {
            this.inventory.setItem(row * 9 + column, button.getItem());
        }
        return this;
    }

    public static boolean checkRange(@NotNull Gui inv, int row) {
        return inv.getRows() - 1 >= row;
    }

    //todo: setButton(row, column,new Button(abstract class???)), done
    // close(target?),
    // show()
}
