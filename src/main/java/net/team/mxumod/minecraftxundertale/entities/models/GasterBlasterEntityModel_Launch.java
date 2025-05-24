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

public class GasterBlasterEntityModel_Launch<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "gaster_blaster_launch"), "main");
	private final ModelPart Main;
	private final ModelPart RightJaw;
	private final ModelPart LeftJaw;

	public GasterBlasterEntityModel_Launch(ModelPart root) {
		this.Main = root.getChild("Main");
		this.RightJaw = this.Main.getChild("RightJaw");
		this.LeftJaw = this.Main.getChild("LeftJaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Main = partdefinition.addOrReplaceChild("Main", CubeListBuilder.create().texOffs(0, 16).addBox(-5.001F, 1.2871F, -4.4921F, 10.002F, 2.002F, 6.002F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-5.0F, 3.2881F, -4.4911F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(32, 9).addBox(-5.0F, -2.7019F, -1.4911F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(18, 48).addBox(-5.01F, 1.2881F, 1.5089F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 48).addBox(4.01F, 1.2881F, 1.5089F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 40).addBox(-1.501F, -6.3113F, 4.5986F, 3.002F, 4.002F, 2.002F, new CubeDeformation(0.0F))
		.texOffs(16, 46).addBox(-2.5F, -0.7119F, -2.4911F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.7119F, 2.4911F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r1 = Main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -4.8223F, -3.915F, 3.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-1.5F, -3.8223F, -8.9151F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(42, 26).addBox(5.5F, -2.5591F, 4.9223F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(46, 46).addBox(-7.5F, -3.0591F, 0.9223F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 26).addBox(-7.5F, -2.5591F, 4.9223F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(46, 39).addBox(4.5F, -3.0591F, 0.9223F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 39).addBox(-2.5F, -2.8223F, 3.085F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2389F, -1.0245F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 31).addBox(-3.5236F, -4.9508F, -3.4764F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(32, 17).addBox(-2.67F, -2.9508F, -3.33F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(28, 31).addBox(-1.0842F, 2.0492F, -5.9882F, 7.0725F, 1.0F, 7.0725F, new CubeDeformation(0.0F))
		.texOffs(30, 0).addBox(-1.0842F, 0.0492F, -5.9882F, 7.0725F, 2.0F, 7.0725F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2389F, -1.0245F, 0.0F, 0.7854F, 0.0F));

		PartDefinition RightJaw = Main.addOrReplaceChild("RightJaw", CubeListBuilder.create(), PartPose.offset(0.0F, 4.2881F, 1.5089F));

		PartDefinition cube_r3 = RightJaw.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 48).addBox(4.5F, -1.0F, 0.0F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 40).addBox(4.5F, 7.0F, 1.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 47).addBox(4.0F, -1.0F, 1.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 47).addBox(4.0F, 4.0F, 2.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition LeftJaw = Main.addOrReplaceChild("LeftJaw", CubeListBuilder.create(), PartPose.offset(-8.0F, 4.2881F, 1.5089F));

		PartDefinition cube_r4 = LeftJaw.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 47).addBox(3.5F, -1.0F, 0.0F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 9).addBox(3.5F, 7.0F, 1.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 47).mirror().addBox(3.0F, 4.0F, 2.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 47).addBox(3.0F, -1.0F, 1.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}