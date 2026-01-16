package com.shimaper.elytratableenchantment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ElytraTableEnchantmentConfig {
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("elytra-table-enchantment.json").toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static ConfigData data = new ConfigData();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                data = GSON.fromJson(reader, ConfigData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            save();
        }

        ElytraTableEnchantment.enchantabilityValue = data.enchantabilityValue;
    }

    public static void save() {
        data.enchantabilityValue = ElytraTableEnchantment.enchantabilityValue;
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ConfigData {
        public int enchantabilityValue = 10;
    }
}