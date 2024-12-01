package net.mxumod.mxumod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinding {
    public static  final String KEY_CATEGORY_MXU = "key.category.minecraftxundertalemod.minecraftxundertale";
    public  static final String KEY_COMBAT_MODE = "key.minecraftxundertalemod.combatmode";

    public static final KeyMapping COMBATMODE_KEY = new KeyMapping(KEY_COMBAT_MODE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_MXU);

}
