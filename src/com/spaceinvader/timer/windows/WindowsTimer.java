/*    */ package com.spaceinvader.timer.windows;
/*    */ 
/*    */ import com.spaceinvader.timer.NativeTimer;

import java.io.PrintStream;
/*    */ 
/*    */ public class WindowsTimer
/*    */   implements NativeTimer
/*    */ {
/*  7 */   private static boolean loaded = false;
/*  8 */   private static boolean available = false;
/*    */ 
/*    */   public boolean available()
/*    */   {
/* 12 */     if (loaded) return available;
/*    */ 
/* 14 */     System.out.println(System.getProperty("os.name"));
/*    */ 
/* 16 */     if (System.getProperty("os.name").indexOf("Win") >= 0)
/*    */     {
/* 18 */       if (!loaded) available = loadLibrary();
/*    */     }
/*    */ 
/* 21 */     loaded = true;
/*    */ 
/* 23 */     return available;
/*    */   }
/*    */ 
/*    */   private static boolean loadLibrary()
/*    */   {
/*    */     try
/*    */     {
/* 30 */       System.loadLibrary("timer");
/* 31 */       return true;
/*    */     }
/*    */     catch (Throwable localThrowable)
/*    */     {
/* 35 */       localThrowable.printStackTrace();
/* 36 */     }return false;
/*    */   }
/*    */ 
/*    */   public native long getResolution();
/*    */ 
/*    */   public native long getClockTicks();
/*    */ }
