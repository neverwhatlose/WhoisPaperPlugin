package ru.nwtls.whoispaperplugin.command;

import cloud.commandframework.bukkit.parsers.PlayerArgument;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.WhoisPlugin;
import ru.nwtls.whoispaperplugin.PlayersDatabase;
import ru.nwtls.whoispaperplugin.util.Formatter;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.*;

public class WhoisCommand {
    private final @NotNull PlayersDatabase db = WhoisPlugin.getDatabase();
    public WhoisCommand(PaperCommandManager<CommandSender> manager) {
        manager.command(manager
                .commandBuilder("whois")
                .argument(PlayerArgument.<CommandSender>builder("target").asRequired())
                .senderType(Player.class)
                .handler(ctx -> {
                    handle(ctx.get("target"), ctx.getSender());
                })
        );
    }

    private void handle(@NotNull Player dude, @NotNull CommandSender sender) {
        Component separator = aqua("------------------");

        Component nickname = single(yellow("Ник: "), gray(dude.getName()));
        Component UUID = single(yellow("UUID: "), gray(dude.getUniqueId()));

        Component status = single(yellow("Статус: "), dude.isOnline() ? green("Онлайн") : red("Оффлайн"));

        Component firstjoin = single(yellow("Первый вход: "), gray(Formatter.formatTime(db.getFirstJoin(dude.getName()))));
        Component lastjoin = single(yellow("Последний вход: "), gray(Formatter.formatTime(db.getLastJoin(dude.getName()))));

        Component playerCords = single(yellow("Координаты: "),
                                suggestCommand(hover(gray(Formatter.locationToString(dude.getLocation())),
                                aqua(Formatter.teleportToLocation(dude.getLocation()))),
                                Formatter.teleportToLocation(dude.getLocation())));

        String loc = Formatter.locationToString(dude.getRespawnLocation());
        Component respawnCords = single(yellow("Точка респавна: "), loc.equals("не установлена") ? gray(loc) : suggestCommand(hover(gray(loc), aqua(Formatter.teleportToLocation(dude.getRespawnLocation()))), Formatter.teleportToLocation(dude.getRespawnLocation())));

        Component health = single(yellow("Здоровье: "), gray(String.format("%.2f", dude.getHealth()).replace(",", ".") + " / " + dude.getHealthScale() + " HP"));

        Component ipaddress = single(yellow("IP-адрес: "), gray(dude.getAddress().toString().replace("/", "")));
        Component connection = single(yellow("Адрес подключения: "), gray(text("localhost")));
        Component ping = single(yellow("Пинг: "), green(dude.getPing() + " мс"));

        Component viewDistance = single(yellow("Дальность прорисовки: "), green(dude.getClientViewDistance()), gray(" чанков"));
        Component clientBrandName = single(yellow("Клиент: "), gray(dude.getClientBrandName()));

        sender.sendMessage(newlined(
                separator,
                nickname,
                UUID,
                empty(),
                status,
                firstjoin,
                lastjoin,
                empty(),
                playerCords,
                respawnCords,
                health,
                empty(),
                ipaddress,
                connection,
                ping,
                empty(),
                viewDistance,
                clientBrandName,
                separator,
                empty()
                ));

    }
}
