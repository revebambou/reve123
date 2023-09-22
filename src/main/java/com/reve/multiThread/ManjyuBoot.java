package com.reve.multiThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ManjyuBoot {

	public static void main(String[] args) {
		// Lock Object
		Manjyu manjyu = new Manjyu();
		Logger log = LoggerFactory.getLogger("ManjyuBoot");
		EatManjyu eatManjyuThread = new EatManjyu(manjyu, log, 10);
		ManjyuShop manjyuShopThread = new ManjyuShop(manjyu, log, 10);
		eatManjyuThread.start();
		manjyuShopThread.start();
	}

}
