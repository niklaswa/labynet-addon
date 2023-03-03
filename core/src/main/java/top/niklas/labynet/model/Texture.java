package top.niklas.labynet.model;

import com.google.gson.annotations.SerializedName;

public class Texture {

  @SerializedName("image_hash")
  private String imageHash;
  private String tags;
  @SerializedName("use_count")
  private int useCount;

  public String getImageHash() {
    return this.imageHash;
  }

  public String getTags() {
    return this.tags;
  }

  public int getUseCount() {
    return this.useCount;
  }

  public String getDownloadUrl() {
    return String.format("https://laby.net/texture/download/%s.png", this.getImageHash());
  }

  public String getPreviewUrl() {
    return String.format("https://skin.laby.net/api/render/texture/%s.png?shadow=true",
        this.getImageHash());
  }
}
