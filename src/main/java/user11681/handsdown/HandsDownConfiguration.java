package user11681.handsdown;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import user11681.bason.BasonConfiguration;

@Environment(EnvType.CLIENT)
public class HandsDownConfiguration extends BasonConfiguration {
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
}
