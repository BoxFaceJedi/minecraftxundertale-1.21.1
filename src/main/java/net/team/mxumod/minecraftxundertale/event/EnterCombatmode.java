package net.team.mxumod.minecraftxundertale.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.team.mxumod.minecraftxundertale.networking.BoneWallC2SPacket;
import net.team.mxumod.minecraftxundertale.skill.CameraLock;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;


public class EnterCombatmode {
    private static boolean combatmode = false;
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static boolean isCombatmode() {return combatmode;}
    public static void setCombatmode(boolean bool) {combatmode = bool;}
    public static void enableEvents() {
        NeoForge.EVENT_BUS.register(EnterCombatmode.class);}
    public static void disableEvents() {NeoForge.EVENT_BUS.register(EnterCombatmode.class);}

    public static void enterCombatmode() {
        enableEvents();
        combatmode = true;
        CameraLock.enableEvent();
        minecraft.player.sendSystemMessage(Component.literal("entering combatmode"));
    }

    public static void leaveCombatmode() {
        disableEvents();
        CameraLock.disableEvent();
        if (BoneWallSkill.isBlocking()) {
            PacketDistributor.sendToServer(new BoneWallC2SPacket());
        }
        combatmode = false;
        minecraft.player.sendSystemMessage(Component.literal("leaving combatmode"));
    }
    @SubscribeEvent
    public static void onLeftClickBlock (PlayerInteractEvent.LeftClickBlock event) {event.setCanceled(combatmode);}
    @SubscribeEvent
    public static void onRightClickBlock (PlayerInteractEvent.RightClickBlock event) {event.setCanceled(combatmode);}
    @SubscribeEvent
    public static void onAttackEntity (AttackEntityEvent event) {event.setCanceled(combatmode);}
    @SubscribeEvent
    public static void onRightClickEntityInteract (PlayerInteractEvent.EntityInteract event) {event.setCanceled(combatmode);}
    @SubscribeEvent
    public static void onRightClickItem (PlayerInteractEvent.RightClickItem event) {event.setCanceled(combatmode);}
    @SubscribeEvent
    public static void onOpenInventory (ScreenEvent event) {if (event.getScreen() instanceof InventoryScreen && combatmode) {event.getScreen().removed();}}
    @SubscribeEvent
    public static void disabledDropItem(ItemTossEvent event) {if (combatmode) {event.getPlayer().addItem(event.getEntity().getItem());event.setCanceled(true);}}

}
