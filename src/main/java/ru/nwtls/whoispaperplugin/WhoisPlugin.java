package ru.nwtls.whoispaperplugin;

import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.nwtls.whoispaperplugin.command.WhoisCommand;
import ru.nwtls.whoispaperplugin.listening.EventListener;
import ru.nwtls.whoispaperplugin.listening.PlayersDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

import static ru.nwtls.whoispaperplugin.Constants.*;

public class WhoisPlugin extends JavaPlugin {
    private static PlayersDatabase db;

    @Override
    public void onEnable() {
        PaperCommandManager<CommandSender> commandManager;

        EventListener eventListener = new EventListener();
        Bukkit.getPluginManager().registerEvents(eventListener, this);
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

    public static @NotNull PlayersDatabase getDatabase() {
        return db;
    }
}
