package net.mxumod.mxumod;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.options.controls.ControlsScreen;
import net.minecraft.network.chat.Component;

public class CustomSetting extends ControlsScreen {
    public static OptionInstance<Boolean> CameraLock;

    public CustomSetting(ControlsScreen parentScreen) {
        super(parentScreen, parentScreen.getMinecraft().options);
    }

//    @Override
//    protected void init() {
//        super.init();
//
//        // 獲取控制設定的列表
//        OptionsList optionsList = this.options.r;
//
//        // 計算按鈕位置
//        int buttonX = this.width / 2 - 155; // 按鈕的 X 座標
//        int buttonY = this.height / 6 + 72; // 按鈕的 Y 座標
//
//        // 為布爾選項新增按鈕
//        optionsList.addSmall(
//                ModOptions.TOGGLE_FEATURE_OPTION.createButton(
//                        this.minecraft.options, // 遊戲選項管理器
//                        buttonX,                // X 座標
//                        buttonY,                // Y 座標
//                        310                     // 按鈕寬度
//                )
//        );
//    }

    public static void registerOptions(Options options) {
        CameraLock = new OptionInstance<>(
                "options.minecraftxundertale.Camera Lock",
                OptionInstance.cachedConstantTooltip(Component.translatable("Determine whether your camera will be locked on the target you chose.")),
                (component, value) -> value ? Component.translatable("options.generic.on") : Component.translatable("options.generic.off"), // 顯示的文字
                OptionInstance.BOOLEAN_VALUES,
                true, // 預設值
                (value) -> {
                    // 當值變更時執行的操作
                    System.out.println("Feature toggled: " + (value ? "Enabled" : "Disabled"));
                }
        );
    }
}
