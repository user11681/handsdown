package user11681.handsdown.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.BooleanOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.handsdown.HandsDownConfiguration;
import user11681.optional.client.Optional;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    private static final HandsDownConfiguration CONFIGURATION = new HandsDownConfiguration("handsdown");

    @Inject(method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At("HEAD"),
            cancellable = true)
    private void cancelRenderHand(final float tickDelta, final MatrixStack matrices, final VertexConsumerProvider.Immediate vertexConsumers,
                                  final ClientPlayerEntity player, final int light, final CallbackInfo info) {
        if (!CONFIGURATION.renderArm && player.getMainHandStack().isEmpty() && player.getHandSwingProgress(tickDelta) == 0) {
            info.cancel();
        }
    }

    static {
        Optional.addVideoOption(new BooleanOption(
                "handsdown.toggle",
                (final GameOptions options) -> CONFIGURATION.renderArm,
                (final GameOptions options, final Boolean value) -> CONFIGURATION.setRenderArm(value)
        ));
    }
}
