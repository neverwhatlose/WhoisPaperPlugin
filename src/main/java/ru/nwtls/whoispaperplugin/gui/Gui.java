package ru.nwtls.whoispaperplugin.gui;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.internal.bind.util.ISO8601Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import ru.nwtls.whoispaperplugin.WhoisPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Gui implements InventoryHolder {
    private static final @NotNull WhoisPlugin plugin = WhoisPlugin.getInstance();
    private final Inventory inventory;
    private final int rows;
    private final Component title;

    private static final @NotNull GuiManager manager = WhoisPlugin.getManager();

    private final HashMap<Integer, Button> buttons;

    public Gui(@NotNull Component title, @Range(from = 1, to = 6) int rows, @NotNull Player player) {
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, rows * 9, title);
        this.buttons = new HashMap<>();
        manager.addGui(player, this);
    }

    public int getRows() {
        return rows;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public @Nullable Button getButtonById(int id) {
        return buttons.get(id);
    }

    /**
     * Установка кнопки в инвентаре
     *
     * @param row столбец (0 - 5)
     * @param column строка (0 - 8)
     * @param button кнопка (Button.java)
     * @return Gui
     */

    @Contract("_, _, _ -> this")
    public @NotNull Gui setButton(@Range(from = 0, to = 5) int row, @Range(from = 0, to = 8) int column, @NotNull Button button, Player player) {
        if (checkRange(this, row)) {
            this.inventory.setItem(row * 9 + column, button.getItem());
            this.buttons.put(row * 9 + column, button);
        }
        return this;
    }

    public static @NotNull GuiManager getManager() {
        return manager;
    }

    public HashMap<Integer, Button> getButtons() {
        return this.buttons;
    }

    public static boolean checkRange(@NotNull Gui inv, int row) {
        return inv.getRows() - 1 >= row;
    }
}
