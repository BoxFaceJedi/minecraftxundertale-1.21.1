package net.team.mxumod.minecraftxundertale.entities.models;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.bone.BoneWallEntity;
import org.jetbrains.annotations.NotNull;

public class BoneWallEntityModel<T extends BoneWallEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "bone_wall_entity"), "main");
	private final ModelPart bone1;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bone10;
	private final ModelPart bone4;
	private final ModelPart bone9;
	private final ModelPart bone5;
	private final ModelPart bone8;
	private final ModelPart bone7;

	public BoneWallEntityModel(ModelPart root) {
		this.bone1 = root.getChild("bone1");
		this.bone2 = root.getChild("bone2");
		this.bone3 = root.getChild("bone3");
		this.bone10 = root.getChild("bone10");
		this.bone4 = root.getChild("bone4");
		this.bone9 = root.getChild("bone9");
		this.bone5 = root.getChild("bone5");
		this.bone8 = root.getChild("bone8");
		this.bone7 = root.getChild("bone7");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone1 = partdefinition.addOrReplaceChild("bone1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bone1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = bone1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r3 = bone1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r4 = bone1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 24.0F, 0.0F));

		PartDefinition cube_r5 = bone2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r6 = bone2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r7 = bone2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r8 = bone2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 24.0F, 0.0F));

		PartDefinition cube_r9 = bone3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r10 = bone3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r11 = bone3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r12 = bone3.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(20.0F, 24.0F, 0.0F));

		PartDefinition cube_r13 = bone10.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r14 = bone10.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r15 = bone10.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r16 = bone10.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-15.0F, 24.0F, 0.0F));

		PartDefinition cube_r17 = bone4.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r18 = bone4.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r19 = bone4.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r20 = bone4.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone9 = partdefinition.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(15.0F, 24.0F, 0.0F));

		PartDefinition cube_r21 = bone9.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r22 = bone9.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r23 = bone9.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r24 = bone9.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-20.0F, 24.0F, 0.0F));

		PartDefinition cube_r25 = bone5.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r26 = bone5.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r27 = bone5.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r28 = bone5.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone8 = partdefinition.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 24.0F, 0.0F));

		PartDefinition cube_r29 = bone8.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r30 = bone8.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r31 = bone8.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r32 = bone8.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone7 = partdefinition.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 24.0F, 0.0F));

		PartDefinition cube_r33 = bone7.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r34 = bone7.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -32.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r35 = bone7.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r36 = bone7.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(@NotNull BoneWallEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone9.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone8.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}