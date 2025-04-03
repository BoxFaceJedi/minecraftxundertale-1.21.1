package net.team.mxumod.minecraftxundertale.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import org.lwjgl.glfw.GLFW;

public class Keybinding {
    public static final String KEY_CATEGORY_MXU = "key.category.minecraftxundertalemod.minecraftxundertale";
    public static final String KEY_COMBAT_MODE = "key.minecraftxundertalemod.combatmode";
    public static final String KEY_DODGE = "key.minecraftxundertalemod.skill_dodge";
    public static final String KEY_SPECIAL_ATTACK = "key.minecraftxundertalemod.skill_special_attack";
    public static final String KEY_ULTIMATE_ATTACK = "key.minecraftxundertalemod.skill_ultimate_attack";
    public static final String KEY_BASIC_ATTACK = "key.minecraftxundertalemod.skill_basic_attack";
    public static final String KEY_BLOCKING = "key.minecraftxundertalemod.skill_block";
    public static final String KEY_LOCK_ON = "key.minecraftxundertalemod.skill_lock_on";

    public static final KeyMapping COMBAT_MODE = new KeyMapping(KEY_COMBAT_MODE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_MXU);
    public static final KeyMapping DODGE = new KeyMapping(KEY_DODGE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, KEY_CATEGORY_MXU);
    public static final KeyMapping SPECIAL_ATTACK = new KeyMapping(KEY_SPECIAL_ATTACK, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_E, KEY_CATEGORY_MXU);
    public static final KeyMapping ULTIMATE_ATTACK = new KeyMapping(KEY_ULTIMATE_ATTACK, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Q, KEY_CATEGORY_MXU);
    public static final KeyMapping BASIC_ATTACK = new KeyMapping(KEY_BASIC_ATTACK, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_LEFT, KEY_CATEGORY_MXU);
    public static final KeyMapping BLOCKING = new KeyMapping(KEY_BLOCKING, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE,  GLFW.GLFW_MOUSE_BUTTON_RIGHT, KEY_CATEGORY_MXU);
    public static final KeyMapping LOCK_ON = new KeyMapping(KEY_LOCK_ON, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_MIDDLE, KEY_CATEGORY_MXU);

    @Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public  static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinding.COMBAT_MODE);
            event.register(Keybinding.DODGE);
            event.register(Keybinding.SPECIAL_ATTACK);
            event.register(Keybinding.ULTIMATE_ATTACK);
            event.register(Keybinding.BASIC_ATTACK);
            event.register(Keybinding.BLOCKING);
            event.register(Keybinding.LOCK_ON);
        }
    }
}
