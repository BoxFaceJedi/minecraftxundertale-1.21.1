package net.team.mxumod.minecraftxundertale.entities.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import org.jetbrains.annotations.NotNull;

public class GasterBlasterEntityModel_Idle<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "gaster_blaster_idle"), "main");
	private final ModelPart Main;
	private final ModelPart RightJaw;
	private final ModelPart LeftJaw;

	public GasterBlasterEntityModel_Idle(ModelPart root) {
		this.Main = root.getChild("Main");
		this.RightJaw = this.Main.getChild("RightJaw");
		this.LeftJaw = this.Main.getChild("LeftJaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Main = partdefinition.addOrReplaceChild("Main", CubeListBuilder.create().texOffs(32, 9).addBox(-5.0F, -0.0592F, -0.4666F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-5.0F, -3.0492F, -3.4666F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-5.001F, -2.0502F, -3.4676F, 10.002F, 2.002F, 6.002F, new CubeDeformation(0.0F))
		.texOffs(34, 48).addBox(-2.5F, 0.9508F, -1.4666F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 40).addBox(-1.501F, 3.5482F, 5.6231F, 3.002F, 4.002F, 2.002F, new CubeDeformation(0.0F))
		.texOffs(38, 50).addBox(4.01F, -3.0492F, 2.5334F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 50).addBox(-5.01F, -3.0492F, 2.5334F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0492F, 1.4666F));

		PartDefinition cube_r1 = Main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 26).addBox(-7.5F, 0.5591F, 4.9223F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 58).addBox(-7.5F, 0.0591F, 0.9223F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(42, 26).addBox(5.5F, 0.5591F, 4.9223F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(46, 47).addBox(-1.5F, -0.1777F, -8.9151F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.5F, 3.8223F, -6.915F, -3.0F, -1.0F, -2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.5F, 0.8223F, -3.915F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(46, 39).addBox(-2.5F, -1.1777F, 3.085F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 57).addBox(4.5F, 0.0591F, 0.9223F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 31).addBox(-3.5236F, 2.9508F, -3.4764F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(32, 17).addBox(-2.67F, -0.0492F, -3.33F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r3 = Main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(30, 0).addBox(-1.2676F, -2.0502F, -5.8773F, 7.0725F, 2.0F, 7.0725F, new CubeDeformation(0.0F))
		.texOffs(28, 31).addBox(-1.2676F, -3.0502F, -5.8773F, 7.0725F, 1.0F, 7.0725F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0513F, 0.001F, -0.2081F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r4 = Main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 1.5F, -3.5F, -7.0F, -1.0F, -7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.8995F, -3.5492F, -3.2595F, 0.0F, 0.7854F, 0.0F));

		PartDefinition RightJaw = Main.addOrReplaceChild("RightJaw", CubeListBuilder.create().texOffs(0, 40).addBox(-5.0F, -5.0492F, -4.4666F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 49).addBox(-4.5F, -4.0492F, -4.4666F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = RightJaw.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(34, 56).addBox(-5.4555F, -6.0492F, -7.282F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r6 = RightJaw.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 56).addBox(-0.2912F, -0.5F, -3.5F, 0.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9F, -4.5492F, -5.2734F, 0.0F, -0.3927F, 0.0F));

		PartDefinition LeftJaw = Main.addOrReplaceChild("LeftJaw", CubeListBuilder.create().texOffs(18, 48).addBox(4.5F, -4.0492F, -4.4666F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(28, 39).addBox(4.0F, -5.0492F, -4.4666F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r7 = LeftJaw.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(50, 56).addBox(4.8793F, -5.0492F, -6.8994F, 0.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(56, 26).addBox(4.3793F, -6.0492F, -6.8994F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}