package net.team.mxumod.minecraftxundertale.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.team.mxumod.minecraftxundertale.networking.packets.BoneWallC2SPacket;
import net.team.mxumod.minecraftxundertale.skill.CameraLock;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;


public class EnterCombatmode {
    private static boolean combatMode = false;
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static boolean isCombatMode() {return combatMode;}
    public static void toggleCombatMode(Player player) {
        combatMode = !combatMode;

        // If the player leaves combat mode, disable blocking
        if (!combatMode) {
            PacketDistributor.sendToServer(new BoneWallC2SPacket());
        }
    }
    public static void enableEvents() {
        NeoForge.EVENT_BUS.register(EnterCombatmode.class);}
    public static void disableEvents() {NeoForge.EVENT_BUS.register(EnterCombatmode.class);}

    public static void enterCombatmode() {
        enableEvents();
        combatMode = true;
        CameraLock.enableEvent();
        minecraft.player.sendSystemMessage(Component.literal("entering combatmode"));
    }

    public static void leaveCombatmode() {
        disableEvents();
        CameraLock.disableEvent();
        combatMode = false;
        minecraft.player.sendSystemMessage(Component.literal("leaving combatmode"));
        PacketDistributor.sendToServer(new BoneWallC2SPacket());
    }
    @SubscribeEvent
    public static void onLeftClickBlock (PlayerInteractEvent.LeftClickBlock event) {event.setCanceled(combatMode);}
    @SubscribeEvent
    public static void onRightClickBlock (PlayerInteractEvent.RightClickBlock event) {event.setCanceled(combatMode);}
    @SubscribeEvent
    public static void onAttackEntity (AttackEntityEvent event) {event.setCanceled(combatMode);}
    @SubscribeEvent
    public static void onRightClickEntityInteract (PlayerInteractEvent.EntityInteract event) {event.setCanceled(combatMode);}
    @SubscribeEvent
    public static void onRightClickItem (PlayerInteractEvent.RightClickItem event) {event.setCanceled(combatMode);}
    @SubscribeEvent
    public static void onOpenInventory (ScreenEvent.Opening event) {
        if (event.getScreen() instanceof InventoryScreen) {event.setCanceled(combatMode);}
    }
    @SubscribeEvent
    public static void disabledDropItem(ItemTossEvent event) {if (combatMode) {event.getPlayer().addItem(event.getEntity().getItem());event.setCanceled(true);}}

}
