package ru.iot.screentextsaver.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "screenTextSaver")
public class ModConfig implements ConfigData {
    public String databaseUrl = "jdbc:postgresql://localhost:15432/screen_text_saver";
    public String databaseUser = "postgres";
    public String databasePassword = "postgres";
}
