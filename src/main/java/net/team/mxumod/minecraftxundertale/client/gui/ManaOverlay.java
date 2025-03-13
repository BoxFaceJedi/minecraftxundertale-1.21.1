package net.team.mxumod.minecraftxundertale.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;

import java.awt.*;

public class ManaOverlay {
    private static GuiGraphics guiGraphics;
    private static int transparentColor = 0x00000000;
    static Minecraft mc = Minecraft.getInstance();

    static boolean isVisible = false;

    @SubscribeEvent
    public static void onRenderGui(CustomizeGuiOverlayEvent event) {
        if (mc.player == null) return;

        int y = event.getWindow().getGuiScaledHeight();

        int currentMana = PlayerSkillManager.getCurrentMana();
        int maxMana = PlayerSkillManager.getMaxMana();

        String manaText = currentMana + "/" + maxMana;

        int barWidth = 100;
        int barHeight = 10;

        guiGraphics = event.getGuiGraphics();
        RenderSystem.enableBlend();

        guiGraphics.fill(0, y, 100, y - barHeight, 0xFFbbbbbb);
        guiGraphics.fill(0, y, 100 * PlayerSkillManager.getCurrentMana() / PlayerSkillManager.getMaxMana(), y - barHeight, 0xFF0088FF);
        guiGraphics.drawCenteredString(mc.font, manaText, barWidth/2, y - 9, 000000);

        RenderSystem.disableBlend();
    }

    public static void showManaOverlay() {
        MinecraftForge.EVENT_BUS.register(ManaOverlay.class);
    }

    public static void hideManaOverlay() {
        MinecraftForge.EVENT_BUS.unregister(ManaOverlay.class);
        guiGraphics.fill(0, 0, 0, 0, transparentColor);
        guiGraphics.drawCenteredString(mc.font, "", 0, 0, transparentColor);
    }

    public static boolean IsVisible() {
        return isVisible;
    }
}
