package com.cutemobs.client;

import com.cutemobs.CuteCreature;
import com.cutemobs.CuteMobs;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CuteRenderer extends MobRenderer<CuteCreature, CuteModel> {
	private final ResourceLocation texture;

	public CuteRenderer(EntityRendererProvider.Context ctx, String name) {
		super(ctx, new CuteModel(ctx.bakeLayer(ClientSetup.LAYER)), 0.35F);
		this.texture = new ResourceLocation(CuteMobs.MOD_ID, "textures/entity/" + name + ".png");
	}

	@Override
	public ResourceLocation getTextureLocation(CuteCreature entity) {
		return texture;
	}
}
