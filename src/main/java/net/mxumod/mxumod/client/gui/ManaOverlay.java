package net.mxumod.mxumod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.jna.platform.win32.WinDef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
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


@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaOverlay {

    @SubscribeEvent
    public static void onRenderGui(CustomizeGuiOverlayEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        int y = event.getWindow().getGuiScaledHeight();

        int currentMana = PlayerSkillManager.getCurrentMana();
        int maxMana = PlayerSkillManager.getMaxMana();

        String manaText = currentMana + "/" + maxMana;

        int barWidth = 100;
        int barHeight = 10;

        GuiGraphics guiGraphics = event.getGuiGraphics();
        RenderSystem.enableBlend();

        int textWidth = mc.font.width(manaText);

        guiGraphics.fill(0, y, 100 * PlayerSkillManager.getCurrentMana() / PlayerSkillManager.getMaxMana(), y - barHeight, 0xFF0088FF);
        guiGraphics.drawCenteredString(mc.font, manaText, barWidth/2, y - 9, 000000);

        RenderSystem.disableBlend();
    }
}
