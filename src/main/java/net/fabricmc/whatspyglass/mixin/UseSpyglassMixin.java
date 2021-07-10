package net.fabricmc.whatspyglass.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SpyglassItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.fabricmc.whatspyglass.UseSpyglassCallback;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpyglassItem.class)
public class UseSpyglassMixin {
	@Inject(method = "use", at = @At("HEAD"),  cancellable = true)
	private void useSpyglass(final World world, final PlayerEntity player, final Hand hand, final CallbackInfoReturnable<Boolean> info) {
        ActionResult result = UseSpyglassCallback.EVENT.invoker().interact(world, player, hand);
 
        if(result == ActionResult.FAIL) {
            info.cancel();
        }
	}
}
