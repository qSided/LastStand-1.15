package com.qsided.laststandmod.config;

import com.qsided.laststandmod.LastStandMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import static net.minecraft.util.text.TextFormatting.*;

@Mod.EventBusSubscriber(modid = LastStandMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static class Common
    {
        public final ForgeConfigSpec.IntValue lastStandChance;
        public final ForgeConfigSpec.IntValue strengthDuration;
        public final ForgeConfigSpec.IntValue resistanceDuration;
        public final ForgeConfigSpec.IntValue strengthLevel;
        public final ForgeConfigSpec.IntValue resistanceLevel;
        public final ForgeConfigSpec.IntValue maximumHealth;
        public final ForgeConfigSpec.EnumValue textColor;
        public final ForgeConfigSpec.BooleanValue hookPMMO;
        public final ForgeConfigSpec.IntValue enduranceReq;
        public final ForgeConfigSpec.BooleanValue doActivationXP;
        public final ForgeConfigSpec.IntValue activationXP;
        public final ForgeConfigSpec.BooleanValue enduranceBased;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Last Stand configurations")
                    .push("laststand");

            lastStandChance = builder
                    .comment("Chance for last stand to activate.")
                    .translation("laststand.configgui.lastStandChance")
                    .worldRestart()
                    .defineInRange("lastStandChance", 50, 0, 100);

            maximumHealth = builder
                    .comment("The maximum health at which Last Stand can be activated.")
                    .translation("laststand.configgui.maximumHealth")
                    .worldRestart()
                    .defineInRange("maximumHealth", 6, 0, 200);

            strengthDuration = builder
                    .comment("How long should the strength potion effect last? (In ticks.)")
                    .translation("laststand.configgui.strengthDuration")
                    .worldRestart()
                    .defineInRange("strengthDuration", 200, 0, 2147483647);

            resistanceDuration = builder
                    .comment("How long should the resistance potion effect last? (In ticks.)")
                    .translation("laststand.configgui.resistanceDuration")
                    .worldRestart()
                    .defineInRange("resistanceDuration", 200, 0, 2147483647);

            strengthLevel = builder
                    .comment("What level should strength be? (The level in-game = strengthLevel + 1, so putting 4 here would give you Strength 5 in-game.)")
                    .translation("laststand.configgui.strengthLevel")
                    .worldRestart()
                    .defineInRange("strengthLevel", 2, 0, 99);

            resistanceLevel = builder
                    .comment("What level should resistance be? (The level in-game = resistanceLevel + 1, so putting 4 here would give you Resistance 5 in-game.)")
                    .translation("laststand.configgui.resistanceLevel")
                    .worldRestart()
                    .defineInRange("resistanceLevel", 4, 0, 99);

            textColor = builder
                    .comment("What color should the last stand activation message be?")
                    .translation("laststand.configgui.textColor")
                    .worldRestart()
                    .defineEnum("textColor", GOLD, RED, DARK_RED, GOLD, YELLOW, GREEN, DARK_GREEN, BLUE, DARK_BLUE, AQUA, DARK_AQUA, LIGHT_PURPLE, DARK_PURPLE, WHITE, GRAY, DARK_GRAY, BLACK);

            hookPMMO = builder
                    .comment("Should Last Stand hook into the PMMO api if PMMO is installed? (Allows you to set requirements for Last Stand to activate through PMMO.)")
                    .comment("This will also make it so that Last Stand will last longer based on the player's endurance level.")
                    .translation("laststand.configgui.hookPMMO")
                    .worldRestart()
                    .define("hookPMMO", true);

            doActivationXP = builder
                    .comment("Should Last Stand activation give skill xp for PMMO?")
                    .translation("laststand.configgui.doActivationXP")
                    .worldRestart()
                    .define("doActivationXP", true);

            activationXP = builder
                    .comment("PMMO - How much skill xp should be given when Last Stand activates?")
                    .translation("laststand.configgui.activationXP")
                    .worldRestart()
                    .defineInRange("activationXP", 10, 0, 2147483647);

            enduranceReq = builder
                    .comment("PMMO - What endurance level should the player be required to have for Last Stand to activate?")
                    .translation("laststand.configgui.enduranceReq")
                    .worldRestart()
                    .defineInRange("enduranceReq", 10, 0, 999);

            enduranceBased = builder
                    .comment("PMMO - Should Last Stand's activation chance be relative to the player's endurance level?")
                    .comment("This will also make it so that Last Stand will last longer based on the player's endurance level.")
                    .translation("laststand.configgui.enduranceBased")
                    .worldRestart()
                    .define("enduranceBased", false);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {

    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading event) {

    }
}
