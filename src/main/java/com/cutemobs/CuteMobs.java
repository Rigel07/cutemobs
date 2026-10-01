package com.cutemobs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(CuteMobs.MOD_ID)
public class CuteMobs {
	public static final String MOD_ID = "cutemobs";

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

	public static final RegistryObject<EntityType<CuteCreature>> MOCHI = entity("mochi", 0.6F, 0.6F);
	public static final RegistryObject<EntityType<CuteCreature>> NUBE = entity("nube", 0.7F, 0.7F);
	public static final RegistryObject<EntityType<CuteCreature>> BROTE = entity("brote", 0.5F, 0.5F);

	public static final RegistryObject<Item> MOCHI_EGG = egg("mochi", MOCHI, 0xFFB6D5, 0xFF6FA8);
	public static final RegistryObject<Item> NUBE_EGG = egg("nube", NUBE, 0xEAF4FF, 0x9CCBFF);
	public static final RegistryObject<Item> BROTE_EGG = egg("brote", BROTE, 0xB8F2C0, 0x5BC77A);

	private static RegistryObject<EntityType<CuteCreature>> entity(String name, float w, float h) {
		return ENTITIES.register(name, () -> EntityType.Builder.of(CuteCreature::new, MobCategory.CREATURE)
			.sized(w, h).clientTrackingRange(8).build(MOD_ID + ":" + name));
	}

	private static RegistryObject<Item> egg(String name, RegistryObject<EntityType<CuteCreature>> type, int c1, int c2) {
		return ITEMS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(type, c1, c2, new Item.Properties()));
	}

	public CuteMobs() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ENTITIES.register(bus);
		ITEMS.register(bus);
		bus.addListener(CuteMobs::addToTabs);
	}

	private static void addToTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(MOCHI_EGG);
			event.accept(NUBE_EGG);
			event.accept(BROTE_EGG);
		}
	}
}
