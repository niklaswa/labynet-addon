package top.niklas.labynet.widget;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.lss.property.annotation.AutoWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.widget.Widget;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.VerticalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import top.niklas.labynet.model.Texture;

public class TextureWidget extends VerticalListWidget<Widget> {

  private final Texture texture;

  public TextureWidget(Texture texture) {
    this.addId("texture");
    this.texture = texture;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    Icon previewImage = Icon.url(this.texture.getPreviewUrl());
    super.addChild(new IconWidget(previewImage));

    ComponentWidget useCount = ComponentWidget.component(
        Component.text(this.texture.getUseCount() + " users")
    );
    super.addChild(useCount);
  }
}