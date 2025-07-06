package com.adveerr.immortalcats;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(ImmortalCatsMod.MODID)
public class ImmortalCatsMod {
    public static final String MODID = "immortalcats";

    public ImmortalCatsMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Cat cat) {
            event.setCanceled(true); // Cancel to prevent damage
            Level level = cat.getLevel();
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.HEART,
                        cat.getX(), cat.getY() + 1.0, cat.getZ(),
                        1, 0.0, 0.2, 0.0, 0.0);
                serverLevel.playSound(null, cat.getX(), cat.getY(), cat.getZ(),
                        SoundEvents.CAT_AMBIENT, cat.getSoundSource(), 1.0F, 1.0F);
            }
        }
    }
}