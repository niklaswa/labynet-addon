package top.niklas.labynet;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.navigation.NavigationElement;
import net.labymod.api.client.gui.navigation.NavigationWrapper;
import net.labymod.api.client.gui.navigation.elements.ScreenNavigationElement;
import net.labymod.api.client.gui.screen.LabyScreen;
import net.labymod.api.client.gui.screen.ScreenInstance;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.Widget;
import org.jetbrains.annotations.Nullable;
import top.niklas.labynet.activity.SkinBrowserActivity;

import java.util.List;

public class SkinBrowserNavigationEntry extends ScreenNavigationElement {

  private final Labynet labynet;

  protected SkinBrowserNavigationEntry(Labynet labynet) {
    super(new SkinBrowserActivity(labynet));

    this.labynet = labynet;
  }

  @Override
  public Component getDisplayName() {
    return Component.text("Skin Browser");
  }

  @Override
  public Icon getIcon() {
    return null;
  }

  @Override
  public String getWidgetId() {
    return "skin-browser";
  }
}
