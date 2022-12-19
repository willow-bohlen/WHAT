package net.fabricmc.whatspyglass;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class WhatSpyglass implements ModInitializer {
    public static final Identifier MUSIC_ID = new Identifier("what:sanctuary_guardian");
    public static final SoundEvent MUSIC_EVENT = SoundEvent.of(MUSIC_ID);

    public static boolean inSpyglass = false;
    
    private final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {

        Registry.register(Registries.SOUND_EVENT, MUSIC_ID, MUSIC_EVENT);
        
        UseSpyglassCallback.EVENT.register((world, player, hand) -> {
            if (inSpyglass) {
                this.client.getSoundManager().stopSounds(MUSIC_ID, SoundCategory.VOICE);
            }
            inSpyglass = true;

            player.playSound(
                MUSIC_EVENT,
                SoundCategory.VOICE, 
                1f,
                1f
            );

            return ActionResult.PASS;
        });

        StopUsingSpyglassCallback.EVENT.register((stack, world, user, remainingUseTicks) -> {
            this.client.getSoundManager().stopSounds(MUSIC_ID, SoundCategory.VOICE);
            inSpyglass = false;
            return ActionResult.PASS;
        });
    }
}
