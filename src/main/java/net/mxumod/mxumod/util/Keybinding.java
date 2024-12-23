package net.mxumod.mxumod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.ToggleKeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinding {
    public static  final String KEY_CATEGORY_MXU = "key.category.minecraftxundertalemod.minecraftxundertale";
    public  static final String KEY_COMBAT_MODE = "key.minecraftxundertalemod.combatmode";
    public  static final String KEY_LEFT_CLICK = "key.minecraftxundertalemod.skill_left_click";
    public  static final String KEY_RIGHT_CLICK = "key.minecraftxundertalemod.skill_right_click";
    public  static final String KEY_MIDDLE_CLICK = "key.minecraftxundertalemod.skill_middle_click";

    public static final KeyMapping COMBATMODE_KEY = new KeyMapping(KEY_COMBAT_MODE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_MXU);
    public static final KeyMapping LEFTCLICK_KEY = new KeyMapping(KEY_LEFT_CLICK, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_LEFT, KEY_CATEGORY_MXU);
    public static final KeyMapping RIGHTCLICK_KEY = new KeyMapping(KEY_RIGHT_CLICK, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE,  GLFW.GLFW_MOUSE_BUTTON_RIGHT, KEY_CATEGORY_MXU);
    public static final KeyMapping MIDDLECLICK_KEY = new KeyMapping(KEY_MIDDLE_CLICK, KeyConflictContext.IN_GAME, InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_MIDDLE, KEY_CATEGORY_MXU);

}
