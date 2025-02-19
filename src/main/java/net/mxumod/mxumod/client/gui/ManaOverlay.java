package net.mxumod.mxumod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.skill.PlayerSkillManager;

@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaOverlay {

    @SubscribeEvent
    public static void onRenderGui(CustomizeGuiOverlayEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        int screenWidth = event.getWindow().getGuiScaledWidth();
        int screenHeight = event.getWindow().getGuiScaledHeight();

        int currentMana = PlayerSkillManager.getCurrentMana();
        int maxMana = PlayerSkillManager.getMaxMana();

        float manaPercentage = 1.0F;

        int barWidth = 100;  // 魔力條長度
        int barHeight = 10;   // 魔力條高度
        int x = (screenWidth - barWidth) / 2; // 置中
        int y = screenHeight - 40; // 放在血條上方

        GuiGraphics guiGraphics = event.getGuiGraphics();
        RenderSystem.enableBlend();

        guiGraphics.fill(x, y ,x + barWidth, y + barHeight , 696969);

        int filledWidth = (int) (barWidth * manaPercent);
        guiGraphics.fill(x, y, x + filledWidth, y + barHeight, 0xFF0088FF);
    }
}
