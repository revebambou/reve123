package com.reve.multiThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reve.common.StringUtils;

public class ManjyuShop extends Thread {
	private Manjyu manjyu;
	private Logger log;
	private final int maxEatTimes;
	
	public ManjyuShop(Manjyu manjyu, Logger log, int maxEatTimes) {
		this.manjyu = manjyu;
		this.log = log;
		this.maxEatTimes = maxEatTimes;
	}
	
	public void run() {
		int count = 0;
		while(true) {
			log.info("ManjyuShop: out of synchronized:饅頭の店スレッドを開始する：" + count);
			synchronized(manjyu) {
				log.info(manjyu.toString());
				log.info("ManjyuShop: in synchronized:饅頭の店スレッドがActiveｄ： " + count + "---饅頭status： " + manjyu.isManjyuExist());
				// 包子存在的时候
				if (manjyu.isManjyuExist()) {
					// 包子铺进入等待状态
					try {
						// 饅頭の店スレッドは待機になる前に、必ず饅頭を食べるのスレッドをnotify
						manjyu.notify();
						log.info("after ManjyuShop notify: 饅頭が存在するので、饅頭の店が待機。饅頭を食べるのスレッドをnotify" + count);
						// 包子铺生产max次后结束
						if (count >= maxEatTimes) {
							log.info(count + "個饅頭が作るので、終了する");
							break;
						}
						// 必须要用锁对象调用wait方法
						manjyu.wait();
						log.info("after ManjyuShop wait: 饅頭が食べられたので、饅頭の店の待機が終了する" + count);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						StringUtils.outputExceptionLog(log, e);
					}
				}
				// 包子铺生产max次后结束
				if (count >= maxEatTimes) {
					log.info(count + "個饅頭が作るので、終了する");
					break;
				}

				// 被唤醒之后执行，包子铺生产包子
				// 锁对象执行notify方法后，执行wait之后的代码
				// 包子铺进入生产模式
				String majyuKawa = "皮" + count;
				String manjyuNiku = "肉" + count;
				
				manjyu.setManjyuKawa(majyuKawa);
				manjyu.setManjyuNiku(manjyuNiku);
				count++;
				long start = System.currentTimeMillis();
				log.info("饅頭の店の作業を開始する。饅頭の皮： " + majyuKawa + "， 饅頭の肉： " + manjyuNiku);
				// time for get manjyu
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long end = System.currentTimeMillis();
				log.info("饅頭の店の作業が終了する。掛かった時間: " + (end - start));
				manjyu.setManjyuExist(true);
				
				// 唤醒包子线程--吃包子
				// ここでnotifyを利用すると、饅頭を食べるスレッドと饅頭の店のスレッドは、同時に待機の状態になる可能性がある
				//manjyu.notify();
				log.info("饅頭の店の作業作業回数: " + count);
			}
		}
		
	}
}
