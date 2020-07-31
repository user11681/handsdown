package user11681.handsdown.configuration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.BooleanOption;
import net.minecraft.client.options.GameOptions;
import user11681.bason.BasonConfiguration;
import user11681.optional.asm.accessor.OptionalOption;
import user11681.optional.client.Optional;

@Environment(EnvType.CLIENT)
public class HandsDownConfiguration extends BasonConfiguration {
    public static final HandsDownConfiguration INSTANCE = new HandsDownConfiguration("handsdown");

    public static final OptionalOption HANDS_DOWN_TOGGLE = Optional.addVideoOption(new BooleanOption(
            "handsdown.toggle",
            (final GameOptions options) -> INSTANCE.renderArm,
            (final GameOptions options, final Boolean value) -> INSTANCE.setRenderArm(value)
    ));

    public boolean renderArm;

    public HandsDownConfiguration(final String path) {
        super(path);
    }

    public void setRenderArm(final boolean renderArm) {
        this.renderArm = renderArm;

        super.write();
    }

    @Override
    public void init() {
        this.renderArm = true;
    }

    /**
     * a convenient alternative to {@link Class#forName}
     */
    public static void load() {}
}
