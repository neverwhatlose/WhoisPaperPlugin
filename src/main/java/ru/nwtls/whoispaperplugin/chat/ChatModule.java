package ru.nwtls.whoispaperplugin.chat;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static ru.nwtls.whoispaperplugin.util.StyleUtils.*;

public class ChatModule implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Player sender = event.getPlayer();

        Component nickname = yellow(sender.getName());
        Component lmc = single(yellow("ЛКМ: "), gray("Открыть профиль " + sender.getName()));
        Component slmc = single(yellow("Shift+ЛКМ: "), gray("Добавить /msg " + sender.getName() + " во ввод"));

        Component nicknameHover = runCommand(hover(gray(sender.getName()),
                newlined(nickname, empty(), lmc, slmc)),
                "/profile " + sender.getName()).insertion("/msg " + sender.getName() + " ");

        Bukkit.broadcast(single(nicknameHover, gray(" > "), event.message()));
        event.setCancelled(true);
    }
}
