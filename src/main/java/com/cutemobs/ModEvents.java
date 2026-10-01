package com.cutemobs;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuteMobs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
	@SubscribeEvent
	public static void attributes(EntityAttributeCreationEvent event) {
		AttributeSupplier attrs = Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).build();
		event.put(CuteMobs.MOCHI.get(), attrs);
		event.put(CuteMobs.NUBE.get(), attrs);
		event.put(CuteMobs.BROTE.get(), attrs);
	}

	@SubscribeEvent
	public static void spawns(SpawnPlacementRegisterEvent event) {
		for (var type : java.util.List.of(CuteMobs.MOCHI, CuteMobs.NUBE, CuteMobs.BROTE)) {
			event.register(type.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		}
	}
}
