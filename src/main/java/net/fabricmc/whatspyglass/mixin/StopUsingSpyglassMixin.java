package net.fabricmc.whatspyglass.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpyglassItem;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.fabricmc.whatspyglass.StopUsingSpyglassCallback;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpyglassItem.class)
public class StopUsingSpyglassMixin {
	@Inject(method = "onStoppedUsing()V", at = @At("HEAD"),  cancellable = true)
	private void stopUsingSpyglass(final ItemStack stack, final World world, final LivingEntity user, final int remainingUseTicks, final CallbackInfo info) {
        ActionResult result = StopUsingSpyglassCallback.EVENT.invoker().interact(stack, world, user, remainingUseTicks);

        if(result == ActionResult.FAIL) {
            info.cancel();
        }
	}
}
