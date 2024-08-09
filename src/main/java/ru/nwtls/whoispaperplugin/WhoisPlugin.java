package ru.nwtls.whoispaperplugin;

import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.chat.ChatModule;
import ru.nwtls.whoispaperplugin.command.WhoisCommand;
import ru.nwtls.whoispaperplugin.gui.GuiListener;
import ru.nwtls.whoispaperplugin.gui.GuiManager;
import ru.nwtls.whoispaperplugin.listening.EventListener;
import ru.nwtls.whoispaperplugin.player.ProfileCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

import static ru.nwtls.whoispaperplugin.Constants.*;

public class WhoisPlugin extends JavaPlugin {
    private static PlayersDatabase db;
    private static @NotNull GuiManager manager;

    @Override
    public void onEnable() {
        PaperCommandManager<CommandSender> commandManager;
        manager = new GuiManager();

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatModule(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);

        try {
            commandManager = new PaperCommandManager<>(
                    this,
                    CommandExecutionCoordinator.simpleCoordinator(),
                    Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        db = new PlayersDatabase(databaseConnection());
        new WhoisCommand(commandManager);
        new ProfileCommand(commandManager);
    }

    @Override
    public void onDisable() {

    }

    public static @NotNull WhoisPlugin getInstance() {
        return WhoisPlugin.getPlugin(WhoisPlugin.class);
    }

    private static @NotNull Connection databaseConnection() {
        Connection con;
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Database connection established");
        return con;
    }

    public static @NotNull GuiManager getManager() {
        return manager;
    }

    public static @NotNull PlayersDatabase getDatabase() {
        return db;
    }
}
