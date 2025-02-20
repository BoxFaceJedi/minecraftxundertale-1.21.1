package net.mxumod.mxumod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.jna.platform.win32.WinDef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.skill.PlayerSkillManager;

import java.awt.font.FontRenderContext;


/*
The center (0, 0) is on the top right of the screen.
The screen is in the fourth quadrant.

Coordinate diagram can be drawn like the following schematic diagram:

    ●------------------------→ X
    |
    |
    |   The screen is here.
    |
    |
    -Y
 */


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
