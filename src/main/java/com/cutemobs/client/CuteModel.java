package com.cutemobs.client;

import com.cutemobs.CuteCreature;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

/** Modelo chibi: cuerpo redondito, cabezota, orejas flexibles y patitas cortas. */
public class CuteModel extends EntityModel<CuteCreature> {
	private final ModelPart root, head, leftEar, rightEar, leftFoot, rightFoot;

	public CuteModel(ModelPart root) {
		this.root = root;
		head = root.getChild("head");
		leftEar = head.getChild("left_ear");
		rightEar = head.getChild("right_ear");
		leftFoot = root.getChild("left_foot");
		rightFoot = root.getChild("right_foot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -6F, -4F, 8F, 6F, 8F), PartPose.offset(0F, 22F, 0F));
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-3F, -6F, -3F, 6F, 6F, 6F), PartPose.offset(0F, 16F, -3F));
		head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(32, 0).addBox(-1F, -4F, -0.5F, 2F, 4F, 1F), PartPose.offset(2F, -6F, 0F));
		head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1F, -4F, -0.5F, 2F, 4F, 1F), PartPose.offset(-2F, -6F, 0F));
		root.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(32, 8).addBox(-1F, 0F, -1F, 2F, 2F, 2F), PartPose.offset(2.5F, 22F, -1.5F));
		root.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(32, 8).addBox(-1F, 0F, -1F, 2F, 2F, 2F), PartPose.offset(-2.5F, 22F, -1.5F));
		root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(32, 14).addBox(-1.5F, -3F, 0F, 3F, 3F, 3F), PartPose.offset(0F, 22F, 4F));
		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public void setupAnim(CuteCreature e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
		head.xRot = headPitch * Mth.DEG_TO_RAD;
		float flop = Mth.sin(ageInTicks * 0.15F) * 0.12F;
		leftEar.zRot = 0.15F + flop;
		rightEar.zRot = -0.15F - flop;
		float swing = Mth.cos(limbSwing * 0.9F) * 1.2F * limbSwingAmount;
		leftFoot.xRot = swing;
		rightFoot.xRot = -swing;
	}

	@Override
	public void renderToBuffer(PoseStack pose, VertexConsumer buffer, int light, int overlay, float r, float g, float b, float a) {
		root.render(pose, buffer, light, overlay, r, g, b, a);
	}
}
