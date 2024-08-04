package ru.nwtls.whoispaperplugin;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//todo: database con not in WhoisPlugin.java
//todo: PlayersDatabase.kt

public class PlayersDatabase {
    private final Connection con;

    public PlayersDatabase(Connection con) {
        this.con = con;
    }

    public void addFirstPlayerJoin(@NotNull String playerName, long playerFirstjoin, long playerLastjoin) {
        String query = "INSERT INTO players (name, firstjoin, lastjoin) VALUES ('" + playerName + "', " + playerFirstjoin + ", " + playerLastjoin + ");";
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlayerJoin(@NotNull String name, long lastjoin) {
        String query = "UPDATE `players` SET `lastjoin` = " + lastjoin + " WHERE `name` = '" + name + "';";
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Adding player join " + name + " to " + lastjoin);
    }

    public boolean isExists(@NotNull String name) {
        String query = "SELECT * FROM `players` WHERE `name` = '" + name + "';";
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery(query);
            // КРАЙНЕ ненадежная хуетень, которая полетит от малейшего чиха
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public long getLastJoin(@NotNull String name) {
        String query = "SELECT * FROM `players` WHERE `name` = '" + name + "';";
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery(query);
            // КРАЙНЕ ненадежная хуетень, которая полетит от малейшего чиха
            while (rs.next()) {
                return rs.getLong("lastjoin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public long getFirstJoin(@NotNull String name) {
        String query = "SELECT * FROM `players` WHERE `name` = '" + name + "';";
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery(query);
            // КРАЙНЕ ненадежная хуетень, которая полетит от малейшего чиха
            while (rs.next()) {
                return rs.getLong("firstjoin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
