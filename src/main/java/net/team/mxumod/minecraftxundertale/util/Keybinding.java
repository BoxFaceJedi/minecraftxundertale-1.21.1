package net.team.mxumod.minecraftxundertale.util;

import com.mojang.blaze3d.platform.InputConstants;
import jdk.jfr.Category;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
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
    public static final String KEY_SETTINGS = "key.minecraftxundertalemod.settings";

    public static final KeyMapping COMBAT_MODE = new KeyMapping(KEY_COMBAT_MODE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_MXU);
    public static final KeyMapping DODGE = new KeyMapping(KEY_DODGE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, KEY_CATEGORY_MXU);
    public static final KeyMapping SPECIAL_ATTACK = new KeyMapping(KEY_SPECIAL_ATTACK, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_E, KEY_CATEGORY_MXU);
    public static final KeyMapping ULTIMATE_ATTACK = new KeyMapping(KEY_ULTIMATE_ATTACK, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Q, KEY_CATEGORY_MXU);
    public static final KeyMapping BASIC_ATTACK = new KeyMapping(KEY_BASIC_ATTACK, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_LEFT, KEY_CATEGORY_MXU);
    public static final KeyMapping BLOCKING = new KeyMapping(KEY_BLOCKING, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE,  GLFW.GLFW_MOUSE_BUTTON_RIGHT, KEY_CATEGORY_MXU);
    public static final KeyMapping LOCK_ON = new KeyMapping(KEY_LOCK_ON, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_MIDDLE, KEY_CATEGORY_MXU);
    public static final KeyMapping SETTINGS = new KeyMapping(KEY_SETTINGS, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_MXU);
}
