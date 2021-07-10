package net.fabricmc.whatspyglass.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class CancelRenderMixin {
    private final MinecraftClient client = MinecraftClient.getInstance();
	@Inject(method = "render", at = @At(value = "INVOKE", target="Lnet/minecraft/client/gui/hud/InGameHud;renderSpyglassOverlay(F)V", shift = At.Shift.AFTER), cancellable = true)
	private void cancelRender(final MatrixStack matrices, final float tickDelta, final CallbackInfo info) {
        if(client.player.isUsingSpyglass()) {
            info.cancel();
        }
	}
}
