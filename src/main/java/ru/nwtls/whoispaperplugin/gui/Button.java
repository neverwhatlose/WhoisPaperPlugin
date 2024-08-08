package ru.nwtls.whoispaperplugin.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nwtls.whoispaperplugin.util.StyleUtils;

import java.util.ArrayList;
import java.util.List;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.*;

public class Button {
    private ItemStack item;
    private String title;
    private int id;

    /**
     * Конструктор кнопки
     *
     * @param item предмет, используемый в качетсве кнопки
     * @param title название предмета
     */

    public Button(@NotNull ItemStack item, @Nullable String title) {
        this.item = item;
        this.item.editMeta(meta -> {
            if (title != null) meta.displayName(destyle(aqua(title)));
        });
        this.id = id;
    }

    /**
     * Конструктор кнопки
     *
     * @param item предмет, используемый в качестве кнопки
     * @param title название предмета
     * @param lore лор (описание) предмета
     */

    public Button(@NotNull ItemStack item, @Nullable String title, @Nullable List<@NotNull Component> lore) {
        this.item = item;
        this.item.editMeta(meta -> {
            if (title != null) meta.displayName(destyle(aqua(title)));
            meta.lore(lore == null ? null : lore.stream()
                    .map(StyleUtils::destyle)
                    .toList());
        });
    }

    public ItemStack getItem() {
        return item;
    }

    public String getTitle(){
        return ((TextComponent) (this.getItem().getItemMeta().displayName())).content();
    }
}
