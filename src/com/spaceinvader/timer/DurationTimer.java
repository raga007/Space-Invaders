/*     */ package com.spaceinvader.timer;
/*     */ 
/*     */ public class DurationTimer
/*     */ {
/*  28 */   private AdvancedTimer timer = new AdvancedTimer();
/*     */   private long duration;
/*  31 */   private boolean paused = false;
/*     */   private long adjust;
/*     */   private long pausetime;
/*     */ 
/*     */   public DurationTimer(long paramLong)
/*     */   {
/*  43 */     this.duration = paramLong;
/*     */   }
/*     */ 
/*     */   public static long getTicksPerSecond()
/*     */   {
/*  55 */     return AdvancedTimer.getTicksPerSecond();
/*     */   }
/*     */ 
/*     */   public void start()
/*     */   {
/*  64 */     this.timer.start();
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/*  73 */     reset(this.duration);
/*     */   }
/*     */ 
/*     */   public void reset(long paramLong)
/*     */   {
/*  84 */     stop();
/*     */ 
/*  86 */     this.duration = paramLong;
/*     */   }
/*     */ 
/*     */   public void pause()
/*     */   {
/*  96 */     if (this.paused) return;
/*     */ 
/*  98 */     this.paused = true;
/*  99 */     this.pausetime = this.timer.getClockTicks();
/*     */   }
/*     */ 
/*     */   public boolean isPaused()
/*     */   {
/* 109 */     return this.paused;
/*     */   }
/*     */ 
/*     */   public void resume()
/*     */   {
/* 117 */     if (!this.paused) return;
/*     */ 
/* 119 */     this.adjust += this.timer.getClockTicks() - this.pausetime;
/* 120 */     this.paused = false;
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 129 */     this.timer.stop();
/*     */   }
/*     */ 
/*     */   public long getTicksRemaining()
/*     */   {
/* 140 */     if (this.paused) return this.duration - this.pausetime;
/*     */ 
/* 142 */     return Math.max(0L, this.duration + this.adjust - this.timer.getClockTicks());
/*     */   }
/*     */ 
/*     */   public long getSecondsRemaining()
/*     */   {
/* 155 */     return getTicksRemaining() / getTicksPerSecond();
/*     */   }
/*     */ 
/*     */   public int getPercentRemaining()
/*     */   {
/* 167 */     return (int)(getTicksRemaining() * 100L / this.duration);
/*     */   }
/*     */ 
/*     */   public long getTicksElapsed()
/*     */   {
/* 178 */     if (this.paused) return this.pausetime;
/*     */ 
/* 180 */     return Math.min(this.duration, this.timer.getClockTicks() - this.adjust);
/*     */   }
/*     */ 
/*     */   public long getSecondsElapsed()
/*     */   {
/* 193 */     return getTicksElapsed() / getTicksPerSecond();
/*     */   }
/*     */ 
/*     */   public int getPercentElapsed()
/*     */   {
/* 205 */     return (int)(getTicksElapsed() * 100L / this.duration);
/*     */   }
/*     */ 
/*     */   public long getDuration()
/*     */   {
/* 216 */     return this.duration;
/*     */   }
/*     */ 
/*     */   public long scaleToRemainingTime(int paramInt)
/*     */   {
/* 229 */     return getTicksRemaining() * paramInt / getDuration();
/*     */   }
/*     */ 
/*     */   public long scaleToElapsedTime(int paramInt)
/*     */   {
/* 242 */     return getTicksElapsed() * paramInt / getDuration();
/*     */   }
/*     */ }