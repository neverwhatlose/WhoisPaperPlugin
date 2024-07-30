package ru.nwtls.whoispaperplugin.util;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class TimeFormat {
    public static @NotNull String formatTime(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yy");
        return format.format(millis);
    }
}
