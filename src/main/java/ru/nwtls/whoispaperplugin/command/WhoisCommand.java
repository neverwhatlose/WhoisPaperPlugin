package ru.nwtls.whoispaperplugin.command;

import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.WhoisPlugin;
import ru.nwtls.whoispaperplugin.listening.PlayersDatabase;
import ru.nwtls.whoispaperplugin.util.CoordinatesParser;
import ru.nwtls.whoispaperplugin.util.TimeFormat;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.*;

public class WhoisCommand {
    private final @NotNull WhoisPlugin plugin = WhoisPlugin.getInstance();
    private final @NotNull PlayersDatabase db = WhoisPlugin.getDatabase();
    public WhoisCommand(PaperCommandManager<CommandSender> manager) {
        manager.command(manager
                .commandBuilder("whois")
                .argument(StringArgument.<CommandSender>builder("target").asRequired())
                .senderType(Player.class)
                .handler(ctx -> {

                    handle(ctx.get("target"), ctx.getSender());
                })
        );
    }

    private void handle(@NotNull Player dude, @NotNull CommandSender sender) {
        Component msg = aqua("------------------").appendNewline();
        msg = msg.append(single(yellow("Ник: "), gray(dude.getName())).appendNewline());
        msg = msg.append(single(yellow("UUID: "), gray(dude.getUniqueId())).appendNewline());

        msg = msg.appendNewline();

        msg = msg.append(yellow("Статус: "));
        if (dude.isOnline()) {
            msg = msg.append(green("Онлайн")).appendNewline();
        } else {
            msg = msg.append(red("Оффлайн")).appendNewline();
        }

        msg = msg.append(single(yellow("Первый вход: "), gray(TimeFormat.formatTime(db.getFirstJoin(dude.getName())))).appendNewline());
        msg = msg.append(single(yellow("Последний вход: "), gray(TimeFormat.formatTime(db.getLastJoin(dude.getName())))).appendNewline());

        msg = msg.appendNewline();

        msg = msg.append(single(yellow("Координаты: "), suggestCommand(hover(gray(CoordinatesParser.CoordinatesToString(dude)),
                aqua("/tp " + dude.getLocation().getBlockX() + " " + dude.getLocation().getBlockY() + " " + dude.getLocation().getBlockZ())),
                "/tp " + dude.getLocation().getBlockX() + " " + dude.getLocation().getBlockY() + " " + dude.getLocation().getBlockZ()).appendNewline()));

        msg = msg.append(yellow("Точка респавна: "));

        String loc = CoordinatesParser.RespawnToString(dude);
        if (loc.equals("не установлена")) {
            msg = msg.append(gray(loc)).appendNewline();
        } else {
            String tp = "/tp " + dude.getRespawnLocation().getBlockX() + " " + dude.getRespawnLocation().getBlockY() + " " + dude.getRespawnLocation().getBlockZ();
            msg = msg.append(suggestCommand(hover(gray(loc), aqua(tp)), tp)).appendNewline();
        }

        String health = String.format("%.2f", dude.getHealth()).replace(",", ".");
        msg = msg.append(single(yellow("Здоровье: "), gray(health + " / " + dude.getHealthScale() + " HP")).appendNewline());

        msg = msg.appendNewline();

        msg = msg.append(single(yellow("IP-адрес: "), gray(dude.getServer().getIp())).appendNewline());
        msg = msg.append(single(yellow("Адрес подключения: "), gray(plugin.getServer().getIp())).appendNewline());
        msg = msg.append(single(yellow("Пинг: "), green(dude.getPing())).appendNewline());

        msg = msg.appendNewline();

        msg = msg.append(single(yellow("Дальность прорисовки: "), green(dude.getClientViewDistance()), gray(" чанков")).appendNewline());
        msg = msg.append(single(yellow("Клиент: "), gray(dude.getClientBrandName())).appendNewline());

        msg = msg.append(aqua("------------------"));
        sender.sendMessage(msg);
    }
}
