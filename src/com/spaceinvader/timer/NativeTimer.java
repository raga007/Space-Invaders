package com.spaceinvader.timer;

public abstract interface NativeTimer
{
  public abstract boolean available();

  public abstract long getResolution();

  public abstract long getClockTicks();
}
