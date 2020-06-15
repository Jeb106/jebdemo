package com.example.demothread.test;

public class StopThread extends Thread {
  private int i = 0, j = 0;

  @Override
  public void run() {
    synchronized (this) {
	    // 增加同步锁，确保线程安全
	    ++i;
	    try {
	      // 休眠10秒,模拟耗时操作
	      Thread.sleep(10000);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    ++j;
    }
  }

  /** * 打印i和j */
  public void print() {
  System.out.println("i=" + i + " j=" + j);

//	 OkHttpClient okHttpClient = new OkHttpClient();
//	 URL url1;
//	 Request request = new Request.Builder().url(url1).build();
//	 Response response = okHttpClient.newCall(request).execute();
//	 response;
//
//	 Okh



  }
}