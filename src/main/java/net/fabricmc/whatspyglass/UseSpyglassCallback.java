package net.fabricmc.whatspyglass;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface UseSpyglassCallback {
    Event<UseSpyglassCallback> EVENT = EventFactory.createArrayBacked(UseSpyglassCallback.class,
        (listeners) -> (world, player, hand) -> {
            for (UseSpyglassCallback event : listeners) {
                ActionResult result = event.interact(world, player, hand);

                if (result != ActionResult.PASS) {
                    return result;
                }
            }

        return ActionResult.PASS;
    });

    ActionResult interact(World world, PlayerEntity player, Hand hand);
}
