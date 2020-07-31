package user11681.handsdown.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.handsdown.configuration.HandsDownConfiguration;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @Inject(method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At("HEAD"),
            cancellable = true)
    private void cancelRenderHand(final float tickDelta, final MatrixStack matrices, final VertexConsumerProvider.Immediate vertexConsumers,
                                  final ClientPlayerEntity player, final int light, final CallbackInfo info) {
        if (!HandsDownConfiguration.INSTANCE.renderArm && player.getMainHandStack().isEmpty() && player.getHandSwingProgress(tickDelta) == 0) {
            info.cancel();
        }
    }

    static {
        HandsDownConfiguration.load();
    }
}
