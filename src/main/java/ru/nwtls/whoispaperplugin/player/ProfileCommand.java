package ru.nwtls.whoispaperplugin.player;

import cloud.commandframework.bukkit.parsers.PlayerArgument;
import cloud.commandframework.paper.PaperCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.WhoisPlugin;
import ru.nwtls.whoispaperplugin.gui.Gui;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.gray;

public class ProfileCommand {
    private static final @NotNull WhoisPlugin plugin = WhoisPlugin.getInstance();
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
        Gui inv = new Gui(gray("profile"), 3);
        player.openInventory(inv.getInventory());
        player.setMetadata("profile", new FixedMetadataValue(plugin, true));
    }
}
