package com.reve.multiThread;

import org.slf4j.Logger;

import com.reve.common.StringUtils;

public class EatManjyu extends Thread {
	private Manjyu manjyu;
	private Logger log;
	private final int maxEatTimes;
	
	public EatManjyu(Manjyu manjyu, Logger log, int maxEatTimes) {
		this.manjyu = manjyu;
		this.log = log;
		this.maxEatTimes = maxEatTimes;
	}
	
	public void run() {
		int count = 0;
		while(true) {
			log.info("EatManjyu: out of synchronized：饅頭を食べるスレッドを開始する：" + count);
			synchronized(manjyu) {
				log.info(manjyu.toString());
				log.info("EatManjyu: synchronized：饅頭を食べるスレッドがActiveｄ： " + count + "---饅頭status： " + manjyu.isManjyuExist());
				// 包子不存在的时候
				if (!manjyu.isManjyuExist()) {
					// 吃包子进入等待状态
					try {
						// 饅頭を食べるスレッドは待機になる前に、必ず饅頭の店のスレッドをnotify
						manjyu.notify();
						log.info("after EatManjyu notify: 饅頭が存在しないので、饅頭を食べるスレッドを待っている。饅頭の店のスレッドをnotify" + count);
						if (count >= maxEatTimes) {
							log.info(count + "個饅頭が食べたので、終了する");
							break;
						}
						// 必须要用锁对象调用wait方法
						manjyu.wait();
						log.info("after EatManjyu wait: 饅頭が作られたので、饅頭を食べるスレッドをスタートする" + count);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						StringUtils.outputExceptionLog(log, e);
					}
				}
				if (count >= maxEatTimes) {
					log.info(count + "個饅頭が食べたので、終了する");
					break;
				}
				
				// 被唤醒之后执行，吃包子
				// 锁对象执行notify方法后，执行wait之后的代码
				// 开始吃包子
				count++;
				long start = System.currentTimeMillis();
				log.info("饅頭の食べる： 饅頭の皮： " + manjyu.getManjyuKawa() + "， 饅頭の肉： " + manjyu.getManjyuNiku());
				// time for eating manjyu
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					StringUtils.outputExceptionLog(log, e);
				}
				long end = System.currentTimeMillis();
				log.info("饅頭が食べた。掛かった時間: " + (end - start));
				manjyu.setManjyuExist(false);
				
				log.info("食べた饅頭の数: " + count);
				log.info("-----------------------------------------------------------------------------------");
				// 唤醒包子线程--生产包子
				// ここでnotifyを利用すると、饅頭を食べるスレッドと饅頭の店のスレッドは、同時に待機の状態になる可能性がある
				//manjyu.notify();
			}
		}
		
	}
}
