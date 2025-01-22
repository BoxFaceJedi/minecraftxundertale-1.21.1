package net.mxumod.mxumod.util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.libraries.ObservableValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class Stamina {
    private static final HashMap<String, Integer> MaxStaminaDictionary = new HashMap<>() {{
        put("Sans", 100);
        put("Chara", 100);
    }};
    private static ObservableValue<Integer> MaxStamina = new ObservableValue<>(100);
    private static ObservableValue<Integer> Stamina = new ObservableValue<>(MaxStamina.getValue());
    private static Minecraft mc = Minecraft.getInstance();
    private static boolean isCurrupted = false;
    private static boolean isRecovering = false;
    private static boolean isWaiting = false;
    private static Integer lastActionId = null;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static void setMaxStamina(Integer Value) {MaxStamina.setValue(Value);}
    public static void setStamina(Integer Value) {Stamina.setValue(Value);}
    public static Integer getStamina() {return Stamina.getValue();}
    public static Integer getMaxStamina(){return MaxStamina.getValue();}

    private static final ArrayList<Integer> WaitingList = new ArrayList<>();

    public static void startRecover(Integer Id) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                while (Stamina.getValue() < MaxStamina.getValue() && lastActionId == Id) {
                    Stamina.setValue(Stamina.getValue() + 5, false);
                    Thread.sleep(1000);
                }
                WaitingList.remove(Id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void staminaHandler(Integer oldValue, Integer newValue) {
        if (newValue < oldValue) {

            Integer tmp_Id = null;
            while (tmp_Id == null && WaitingList.contains(tmp_Id)) {
                tmp_Id = new Random().nextInt(1000);
            }
            WaitingList.add(tmp_Id);
            lastActionId = tmp_Id;
            startRecover(tmp_Id);
        }
    }

    @SubscribeEvent
    public static void startClass(PlayerEvent.PlayerLoggedInEvent event) {
        Stamina.addChangeListener((net.mxumod.mxumod.util.Stamina::staminaHandler));
    }
}
