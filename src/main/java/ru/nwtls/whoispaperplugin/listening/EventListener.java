package ru.nwtls.whoispaperplugin.listening;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.nwtls.whoispaperplugin.PlayersDatabase;
import ru.nwtls.whoispaperplugin.WhoisPlugin;

public class EventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayersDatabase db = WhoisPlugin.getDatabase();
        if (!(db.isExists(player.getName()))) {
            db.addFirstPlayerJoin(player.getName(), System.currentTimeMillis(), System.currentTimeMillis());
        } else {
            db.addPlayerJoin(player.getName(), System.currentTimeMillis());
        }
    }
    //todo: onLeave() hover!
}
