package com.qsided.laststandmod.events;

import com.qsided.laststandmod.LastStandMod;
import com.qsided.laststandmod.config.Config;
import harmonised.pmmo.skills.Skill;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import org.lwjgl.system.CallbackI;

import java.util.Random;

public class EventHandler 
{
    @SubscribeEvent
    public static void lastStand(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        Random rng = new Random();
        int rand_int = rng.nextInt(100);
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            World world = player.getEntityWorld();
            if (rand_int > 100 - Config.COMMON.lastStandChance.get() && livingEntity.getHealth() <= Config.COMMON.maximumHealth.get()) {
                if (ModList.get().isLoaded("pmmo")) {
                    if (Config.COMMON.hookPMMO.get()) {
                        LastStandMod.LOGGER.info("Project MMO was found and hooked into successfully!");
                        if (Skill.ENDURANCE.getLevel(player.getUniqueID()) >= Config.COMMON.enduranceReq.get()) {
                            LastStandMod.LOGGER.info("Last Stand fired.");
                            if (Config.COMMON.enduranceBased.get()) {
                                if (rand_int > 100 - Skill.ENDURANCE.getLevel(player.getUniqueID())) {
                                    player.sendMessage(new StringTextComponent(Config.COMMON.textColor.get() + "Activated last stand! Fight for your life!"));
                                    livingEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH, Skill.ENDURANCE.getLevel(player.getUniqueID()) * 5, Config.COMMON.strengthLevel.get()));
                                    livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, Skill.ENDURANCE.getLevel(player.getUniqueID()) * 5, Config.COMMON.resistanceLevel.get()));
                                }
                                if (Config.COMMON.doActivationXP.get()) {
                                    Skill.ENDURANCE.addXp(player.getUniqueID(), Config.COMMON.activationXP.get(), "lastStand", false, false);
                                } else {
                                    player.sendMessage(new StringTextComponent(Config.COMMON.textColor.get() + "Activated last stand! Fight for your life!"));
                                    livingEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH, Config.COMMON.strengthDuration.get(), Config.COMMON.strengthLevel.get()));
                                    livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, Config.COMMON.resistanceDuration.get(), Config.COMMON.resistanceLevel.get()));
                                }
                            } else {
                                LastStandMod.LOGGER.info("Project MMO was not found.");
                                LastStandMod.LOGGER.info("Last Stand fired.");
                                player.sendMessage(new StringTextComponent(Config.COMMON.textColor.get() + "Activated last stand! Fight for your life!"));
                                livingEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH, Config.COMMON.strengthDuration.get(), Config.COMMON.strengthLevel.get()));
                                livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, Config.COMMON.resistanceDuration.get(), Config.COMMON.resistanceLevel.get()));
                            }
                        }
                    }
                }
            }
        }
    }
}
