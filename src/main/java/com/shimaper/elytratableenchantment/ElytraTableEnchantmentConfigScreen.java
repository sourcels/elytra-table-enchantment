package com.shimaper.elytratableenchantment;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ElytraTableEnchantmentConfigScreen extends Screen {
    private final Screen parent;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 40;

    public ElytraTableEnchantmentConfigScreen(Screen parent) {
        super(Component.translatable("elytra-table-enchantment.config.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(new StringWidget(
                centerX - 100,
                centerY - 45,
                200,
                20,
                Component.translatable("elytra-table-enchantment.config.title"),
                this.font
        ));

        this.addRenderableWidget(new AbstractSliderButton(
                centerX - 100,
                centerY - 20,
                200,
                20,
                Component.empty(),
                normalizeValue(ElytraTableEnchantment.enchantabilityValue)
        ) {
            {
                this.updateMessage();
            }

            @Override
            protected void updateMessage() {
                this.setMessage(Component.translatable("elytra-table-enchantment.config.value", denormalizeValue(this.value)));
            }

            @Override
            protected void applyValue() {
                ElytraTableEnchantment.enchantabilityValue = denormalizeValue(this.value);
            }
        });

        this.addRenderableWidget(new MultiLineTextWidget(
                centerX - 100,
                centerY + 10,
                Component.translatable("elytra-table-enchantment.config.restart_warning")
                        .withStyle(style -> style.withColor(0xFF5555)),
                this.font)
                .setMaxWidth(200)
                .setCentered(true)
        );

        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            ElytraTableEnchantmentConfig.save();
            this.minecraft.setScreen(this.parent);
        }).bounds(centerX - 100, centerY + 40, 98, 20).build());

        this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, (button) -> {
            this.minecraft.setScreen(this.parent);
        }).bounds(centerX + 2, centerY + 40, 98, 20).build());
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    private double normalizeValue(int value) {
        return (double) (value - MIN_VALUE) / (MAX_VALUE - MIN_VALUE);
    }

    private int denormalizeValue(double value) {
        return (int) (MIN_VALUE + value * (MAX_VALUE - MIN_VALUE));
    }
}