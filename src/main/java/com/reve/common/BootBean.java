package com.reve.common;

import com.reve.multiThread.EatManjyu;
import com.reve.multiThread.Manjyu;
import com.reve.multiThread.ManjyuShop;

public class BootBean extends BaseBean {
	
	public void manjyuBoot() {
		// Lock Object
		Manjyu manjyu = new Manjyu();

		int maxCnt = 2;
		EatManjyu eatManjyuThread = new EatManjyu(manjyu, log, maxCnt);
		ManjyuShop manjyuShopThread = new ManjyuShop(manjyu, log, maxCnt);
		
		eatManjyuThread.start();
		manjyuShopThread.start();
	}
}
