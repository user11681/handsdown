package user11681.handsdown.configuration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class HandsDownConfigScreen extends Screen {
    private final Screen parent;

    public HandsDownConfigScreen(final Screen parent) {
        super(new TranslatableText("handsdown.title"));

        this.parent = parent;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void init() {
        super.init();

        final int x = (this.width - 100) >> 1;
        final int y = (this.height - 20) >> 1;

        this.addButton(HandsDownConfiguration.HANDS_DOWN_TOGGLE.cast().createButton(this.client.options, x, y, 100));
        this.addButton(new ButtonWidget(x, y + 50, 100, 20, new TranslatableText("mco.selectServer.close"), (final ButtonWidget button) -> this.client.openScreen(this.parent)));
    }

    @Override
    public void render(final MatrixStack matrices, final int mouseX, final int mouseY, final float delta) {
        this.renderBackground(matrices);

        super.render(matrices, mouseX, mouseY, delta);

        this.drawCenteredString(matrices, this.textRenderer, this.title.getString(), this.width >> 1, 50, 0xFFFFFF);
    }
}
