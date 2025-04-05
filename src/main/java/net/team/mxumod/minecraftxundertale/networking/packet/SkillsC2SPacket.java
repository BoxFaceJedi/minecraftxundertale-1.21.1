package net.team.mxumod.minecraftxundertale.networking.packet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.ServerSideSkillManager;

import java.util.function.Supplier;

public class SkillsC2SPacket {
    private static final Gson GSON = new Gson();
    private final String skillName;
    private final JsonElement jsonData; // 讓 Forge 能解析 JSON

    public SkillsC2SPacket(String skillName, Object data) {
        this.skillName = skillName;
        this.jsonData = GSON.toJsonTree(data); // 轉換為 JSON
    }

    public SkillsC2SPacket(String skillName) {
        this.skillName = skillName;
        this.jsonData = JsonParser.parseString("{}"); // 預設為空 JSON
    }

    public SkillsC2SPacket(FriendlyByteBuf buf) {
        this.skillName = buf.readUtf();
        this.jsonData = JsonParser.parseString(buf.readUtf());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.skillName);
        buf.writeUtf(this.jsonData.toString());
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                try {
                    // **把 JSON 轉回 Object**
                    Object data = GSON.fromJson(jsonData, Object.class);
                    ServerSideSkillManager.playerUseSkillRequire(skillName, player, data);
                } catch (Exception e) {
                    System.err.println("❌ JSON 解析錯誤：" + e.getMessage());
                }
            }
        });
        context.setPacketHandled(true);
    }
}