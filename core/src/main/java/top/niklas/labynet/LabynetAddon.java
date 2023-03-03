package top.niklas.labynet;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class LabynetAddon extends LabyAddon<LabynetConfiguration> {


  @Override
  protected void enable() {
    this.registerSettingCategory();

    Labynet labynet = new Labynet();
    this.labyAPI().navigationService().register(new SkinBrowserNavigationEntry(labynet));
  }

  @Override
  protected Class<LabynetConfiguration> configurationClass() {
    return LabynetConfiguration.class;
  }
}
