/*    */ package com.spaceinvader.timer.nanotimer;
/*    */ 
/*    */ import com.spaceinvader.timer.NativeTimer;
/*    */ 
/*    */ public class NanoTimer
/*    */   implements NativeTimer
/*    */ {
/*    */   public boolean available()
/*    */   {
/*  9 */     String str1 = System.getProperty("java.vm.version");
/* 10 */     String str2 = str1.substring(str1.indexOf(".") + 1);
/*    */ 
/* 12 */     if (Integer.parseInt(str2.charAt(0) + "") >= 5) return true;
/*    */ 
/* 14 */     return false;
/*    */   }
/*    */ 
/*    */   public long getResolution()
/*    */   {
/* 19 */     return 1000000000L;
/*    */   }
/*    */ 
/*    */   public long getClockTicks()
/*    */   {
/* 24 */     return System.nanoTime();
/*    */   }
/*    */ }
