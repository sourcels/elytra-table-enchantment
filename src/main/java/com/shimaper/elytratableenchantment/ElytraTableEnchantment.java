package com.shimaper.elytratableenchantment;

import net.fabricmc.api.ModInitializer;


public class ElytraTableEnchantment implements ModInitializer {
    public static final String MOD_ID = "elytra-table-enchantment";

    public static int enchantabilityValue = 10;

    @Override
    public void onInitialize() {
        ElytraTableEnchantmentConfig.load();
    }
}