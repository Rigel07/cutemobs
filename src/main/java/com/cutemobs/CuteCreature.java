package com.cutemobs;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

/** Criatura adorable: sigue a quien lleve bayas dulces y suelta corazones al acariciarla. */
public class CuteCreature extends PathfinderMob {
	public CuteCreature(EntityType<? extends CuteCreature> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1.6D));
		goalSelector.addGoal(2, new TemptGoal(this, 1.1D, Ingredient.of(Items.SWEET_BERRIES), false));
		goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (level() instanceof ServerLevel server) {
			server.sendParticles(ParticleTypes.HEART, getX(), getY() + getBbHeight() + 0.2D, getZ(), 6, 0.3D, 0.2D, 0.3D, 0.02D);
			playSound(SoundEvents.RABBIT_AMBIENT, 1.0F, 1.6F);
		}
		return InteractionResult.SUCCESS;
	}

	@Override protected SoundEvent getAmbientSound() { return SoundEvents.RABBIT_AMBIENT; }
	@Override protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.RABBIT_HURT; }
	@Override protected SoundEvent getDeathSound() { return SoundEvents.RABBIT_DEATH; }
}
