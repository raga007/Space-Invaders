/*     */ package com.spaceinvader.timer;
/*     */ 
/*     */ /*     */ import com.spaceinvader.timer.nanotimer.NanoTimer;
import com.spaceinvader.timer.windows.WindowsTimer;

import java.io.PrintStream;
/*     */ 
/*     */ public class AdvancedTimer
/*     */ {
/*     */   private static long resolution;
/*  17 */   private long start = 0L;
/*  18 */   private long ticks = 0L;
/*     */ 
/*  20 */   private boolean running = false;
/*     */   private static NativeTimer wintimer;
/*     */ 
/*     */   public AdvancedTimer()
/*     */   {
/*  26 */     if (resolution == 0L) init();
/*     */   }
/*     */ 
/*     */   private static void init()
/*     */   {
/*  31 */     long l1 = System.currentTimeMillis();
/*  32 */     long l2 = l1;
/*     */ 
/*  34 */     if (new NanoTimer().available())
/*     */     {
/*  36 */       System.out.println("1.5 NanoTimer selected.");
/*  37 */       wintimer = new NanoTimer();
/*  38 */       resolution = 1L;
/*     */     }
/*  40 */     else if (new WindowsTimer().available())
/*     */     {
/*  42 */       wintimer = new WindowsTimer();
/*  43 */       resolution = 1L;
/*     */     }
/*     */     else
/*     */     {
/*  47 */       while (l1 == l2) l1 = System.currentTimeMillis();
/*     */ 
/*  49 */       resolution = l1 - l2;
/*     */ 
/*  51 */       l1 = System.currentTimeMillis();
/*  52 */       l2 = l1;
/*     */ 
/*  54 */       while (l1 == l2) l1 = System.currentTimeMillis();
/*     */ 
/*  56 */       if (l1 - l2 < resolution) resolution = l1 - l2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private long getTime()
/*     */   {
/*  62 */     if (wintimer != null) return wintimer.getClockTicks();
/*  63 */     return System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public void start()
/*     */   {
/*  73 */     long l1 = getTime();
/*  74 */     long l2 = l1;
/*     */ 
/*  76 */     this.ticks = 0L;
/*  77 */     this.running = true;
/*     */ 
/*  80 */     while (l1 == l2) l1 = getTime();
/*     */ 
/*  82 */     this.start = getTime();
/*     */   }
/*     */ 
/*     */   public long getClockTicks()
/*     */   {
/*  95 */     if (this.running) this.ticks = ((getTime() - this.start) / resolution);
/*     */ 
/*  97 */     return this.ticks;
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 106 */     getClockTicks();
/*     */ 
/* 108 */     this.running = false;
/*     */   }
/*     */ 
/*     */   public void sleep()
/*     */     throws IllegalStateException
/*     */   {
/* 120 */     sleep(1L);
/*     */   }
/*     */ 
/*     */   public void sleep(long paramLong)
/*     */     throws IllegalStateException
/*     */   {
/* 135 */     long l = getClockTicks();
/*     */ 
/* 137 */     if (!this.running) throw new IllegalStateException("Timer not running!");
/*     */ 
/* 139 */     while (getClockTicks() < l + paramLong) Thread.yield();
/*     */   }
/*     */ 
/*     */   public void sleepUntil(long paramLong)
/*     */     throws IllegalStateException
/*     */   {
/* 172 */     if (!this.running) throw new IllegalStateException("Timer not running!");
/*     */ 
/* 174 */     while (getClockTicks() < paramLong) Thread.yield();
/*     */   }
/*     */ 
/*     */   public static long getResolution()
/*     */   {
/* 185 */     if (resolution == 0L) init();
/*     */ 
/* 187 */     return resolution;
/*     */   }
/*     */ 
/*     */   public static long getTicksPerSecond()
/*     */   {
/* 199 */     if (resolution == 0L) init();
/* 200 */     if (wintimer != null) return wintimer.getResolution();
/*     */ 
/* 202 */     return 1000L / getResolution();
/*     */   }
/*     */ }
