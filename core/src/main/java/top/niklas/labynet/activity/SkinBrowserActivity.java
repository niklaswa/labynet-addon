package top.niklas.labynet.activity;

import net.labymod.api.Textures.SpriteCommon;
import net.labymod.api.client.gui.screen.LabyScreen;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.TilesGridWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.util.ThreadSafe;
import org.jetbrains.annotations.Nullable;
import top.niklas.labynet.Labynet;
import top.niklas.labynet.Labynet.Order;
import top.niklas.labynet.Labynet.Type;
import top.niklas.labynet.model.Texture;
import top.niklas.labynet.widget.TextureWidget;
import java.util.ArrayList;
import java.util.List;

@AutoActivity
@Link("skin-browser.lss")
public class SkinBrowserActivity extends SimpleActivity {

  private final Labynet labynet;
  private final TilesGridWidget<TextureWidget> tilesGridWidget = new TilesGridWidget<>();
  private Order order = Order.TRENDING;
  private TextFieldWidget textFieldWidget;

  private final List<Texture> textures = new ArrayList<>();

  public SkinBrowserActivity(Labynet labynet) {
    this.labynet = labynet;

    this.textFieldWidget = new TextFieldWidget();
    this.textFieldWidget.maximalLength(128);
    this.tilesGridWidget.addId("texture-grid");
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    HorizontalListWidget horizontalListWidget = new HorizontalListWidget();
    horizontalListWidget.addId("search-bar");
    horizontalListWidget.addEntry(this.textFieldWidget);

    // Search button
    ButtonWidget searchButton = ButtonWidget.icon(SpriteCommon.SUBMIT);
    searchButton.setPressable(() -> {
      this.searchTextures(Type.SKIN, this.textFieldWidget.getText());
    });
    horizontalListWidget.addEntry(searchButton);

    // Order dropdown
    DropdownWidget<Order> orderDropdownWidget = new DropdownWidget<>();
    orderDropdownWidget.addAll(Order.values());
    orderDropdownWidget.setSelected(this.order);
    orderDropdownWidget.setChangeListener(order -> {
      this.order = order;
      this.searchTextures(Type.SKIN, this.textFieldWidget.getText());
    });
    horizontalListWidget.addEntry(orderDropdownWidget);

    super.document.addChildInitialized(horizontalListWidget);

    for (Texture texture : this.textures) {
      TextureWidget textureWidget = new TextureWidget(texture);
      this.tilesGridWidget.addTile(textureWidget);
    }

    super.document.addChild(this.tilesGridWidget);
  }

  private void searchTextures(Type type, String query) {
    this.tilesGridWidget.removeChildIf(widget -> true);
    this.labynet.loadTextures(type, query, this.order, textureResponse -> {
      this.textures.clear();
      this.textures.addAll(textureResponse.get().getTextures());
      ThreadSafe.executeOnRenderThread(this::reload);
    });
  }

  @Override
  public <T extends LabyScreen> @Nullable T renew() {
    return (T) new SkinBrowserActivity(this.labynet);
  }
}
