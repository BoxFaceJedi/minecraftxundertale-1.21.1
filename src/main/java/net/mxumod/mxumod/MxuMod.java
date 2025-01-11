package net.mxumod.mxumod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mxumod.mxumod.networking.ModMessages;
import net.mxumod.mxumod.shaders.GlowPostProcessor;
import net.mxumod.mxumod.shaders.LightingFx;
import net.mxumod.mxumod.skill.CameraLock;
import org.joml.Vector3f;
import org.slf4j.Logger;
import team.lodestar.lodestone.systems.postprocess.PostProcessHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MxuMod.MOD_ID)
public class MxuMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "minecraftxundertale";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public MxuMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        CameraLock.ThreadOfLockingOn.main();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

        Vector3f center = new Vector3f(8, -60, 8);
        Vector3f color = new Vector3f(1, 0, 1);
        GlowPostProcessor.INSTANCE.addFxInstance(new LightingFx(center, color));

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            PostProcessHandler.addInstance(GlowPostProcessor.INSTANCE);
        }
    }
}
