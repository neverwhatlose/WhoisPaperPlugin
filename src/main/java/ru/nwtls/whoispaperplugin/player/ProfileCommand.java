package ru.nwtls.whoispaperplugin.player;

import cloud.commandframework.bukkit.parsers.PlayerArgument;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.PlayersDatabase;
import ru.nwtls.whoispaperplugin.WhoisPlugin;
import ru.nwtls.whoispaperplugin.gui.Button;
import ru.nwtls.whoispaperplugin.gui.Gui;
import ru.nwtls.whoispaperplugin.gui.GuiManager;
import ru.nwtls.whoispaperplugin.util.Formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.*;

public class ProfileCommand {
    private final PlayersDatabase db = WhoisPlugin.getDatabase();
    private static final GuiManager manager = WhoisPlugin.getManager();
    public ProfileCommand(@NotNull PaperCommandManager<CommandSender> manager) {
        manager.command(manager
                .commandBuilder("profile")
                .senderType(Player.class)
                .argument(PlayerArgument.<CommandSender>builder("target").asOptional())
                .handler(ctx -> {
                    Player sender = (Player) ctx.getSender();
                    handle(ctx.getOrDefault("target", sender.getPlayer()));
                })
        );
    }

    private void handle(@NotNull Player player) {
        Gui gui = new Gui(gray("profile"), 3, player);

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getUniqueId());

        List<Component> lore = new ArrayList<>();
        Component firstJoin = single(yellow("Первый вход: "), gray(Formatter.formatTime(db.getFirstJoin(player.getName()))));
        Component lastJoin = single(yellow("Последний вход: "), gray(Formatter.formatTime(db.getLastJoin(player.getName()))));

        lore.add(firstJoin);
        lore.add(lastJoin);

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(offlinePlayer);
        head.setItemMeta(meta);

        Button button = new Button(
                head,
                player.getName(),
                lore,
                //new Gui(component("ebanat"), 6, player)
                "tp 0 150 0"
        );
        gui.setButton(1, 4, button);

        manager.showGui(player, gui);

        // Чисто технически, updateGui не нужен,
        // поскольку showGui обновляет Gui в HashMap
    }
}
