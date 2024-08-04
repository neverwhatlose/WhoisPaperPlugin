package ru.nwtls.whoispaperplugin.gui;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.*;

public class Button {
    private ItemStack item;
    private String title;

    public Button(@NotNull ItemStack item, @Nullable String title) {
        this.item = item;
        this.item.editMeta(meta -> {
            if (title != null) meta.displayName(destyle(aqua(title)));
        });
    }

    public ItemStack getItem() {
        return item;
    }
}
