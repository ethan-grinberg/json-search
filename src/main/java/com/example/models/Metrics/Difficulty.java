package com.example.models.Metrics;

import com.google.gson.annotations.SerializedName;

public class Difficulty {
  @SerializedName("automated readability index")
  private float readabilityIndex;

  public Difficulty() {}

  /**
   * @return readabilityIndex as a float
   */
  public float getReadabilityIndex() {
    return readabilityIndex;
  }
}