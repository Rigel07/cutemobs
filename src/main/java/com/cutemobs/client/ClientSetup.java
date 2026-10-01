package com.cutemobs.client;

import com.cutemobs.CuteMobs;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuteMobs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(CuteMobs.MOD_ID, "cute"), "main");

	@SubscribeEvent
	public static void layers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(LAYER, CuteModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void renderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CuteMobs.MOCHI.get(), ctx -> new CuteRenderer(ctx, "mochi"));
		event.registerEntityRenderer(CuteMobs.NUBE.get(), ctx -> new CuteRenderer(ctx, "nube"));
		event.registerEntityRenderer(CuteMobs.BROTE.get(), ctx -> new CuteRenderer(ctx, "brote"));
	}
}
