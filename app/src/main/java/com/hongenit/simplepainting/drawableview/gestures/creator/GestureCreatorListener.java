package com.hongenit.simplepainting.drawableview.gestures.creator;

import com.hongenit.simplepainting.drawableview.draw.SerializablePath;

public interface GestureCreatorListener {
  void onGestureCreated(SerializablePath serializablePath);

  void onCurrentGestureChanged(SerializablePath currentDrawingPath);
}
