package net.fabricmc.whatspyglass;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public interface StopUsingSpyglassCallback {
    Event<StopUsingSpyglassCallback> EVENT = EventFactory.createArrayBacked(StopUsingSpyglassCallback.class,
        (listeners) -> (stack, world, user, remainingUseTicks) -> {
            for (StopUsingSpyglassCallback event : listeners) {
                ActionResult result = event.interact(stack, world, user, remainingUseTicks);

                if (result != ActionResult.PASS) {
                    return result;
                }
            }

        return ActionResult.PASS;
    });

    ActionResult interact(ItemStack stack, World world, LivingEntity user, int remainingUseTicks);
}
