package user11681.handsdown.integration;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import user11681.handsdown.configuration.HandsDownConfigScreen;

@Environment(EnvType.CLIENT)
public class HandsDownModMenuEntry implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return HandsDownConfigScreen::new;
    }
}
