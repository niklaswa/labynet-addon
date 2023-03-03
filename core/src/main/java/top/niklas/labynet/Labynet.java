package top.niklas.labynet;

import net.labymod.api.util.io.web.request.Request;
import net.labymod.api.util.io.web.result.ResultCallback;
import org.jetbrains.annotations.Nullable;
import top.niklas.labynet.model.TextureResult;

public class Labynet {

  public void loadTextures(Type type, @Nullable String search, Order order,
      ResultCallback<TextureResult> callback) {
    String url = String.format("https://laby.net/api/texture/search?type=%s&order=%s", type.name(),
        order.name().toLowerCase());
    if (search == null || !search.isEmpty()) {
      url += "&search=" + search;
    }
    Request.ofGson(TextureResult.class)
        .url(url)
        .async().execute(textureResultResponse -> {
          if (textureResultResponse.hasException()) {
            callback.acceptException(textureResultResponse.exception());
          } else {
            callback.acceptRaw(textureResultResponse.get());
          }
        });

  }

  public enum Type {
    SKIN,
    CAPE,
    ITEM1,
    ITEM22
  }

  public enum Order {
    TRENDING,
    MOST_USED,
    LATEST
  }

}
