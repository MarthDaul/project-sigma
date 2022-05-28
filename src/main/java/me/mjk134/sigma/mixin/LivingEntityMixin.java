package me.mjk134.sigma.mixin;

import me.mjk134.sigma.ProjectSigma;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinty {

    @Shadow public abstract boolean isBaby();

    @Shadow public abstract Identifier getLootTable();

    @Shadow private BlockPos lastBlockPos;

    @Inject(method = "onKilledBy", at = @At(value = "HEAD"))
    public void onAnimalDeath(LivingEntity entity, CallbackInfo ci) {
        try {
            if (entity != null) {
                if ((LivingEntity) (Object) this instanceof AnimalEntity) {
                    if (entity instanceof ServerPlayerEntity) {
                        StatusEffect effect = StatusEffect.byRawId(18);
                        entity.addStatusEffect(new StatusEffectInstance(effect, 1200));
                    }
                } else if ((LivingEntity) (Object) this instanceof VillagerEntity) {
                    if (this.isBaby()) {
                       // entity.getWorld().spawnEntity(new ExperienceOrbEntity(entity.getWorld(),(Entity) (Object) this.getX(), (Entity) (Object) this.getY(),  );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
