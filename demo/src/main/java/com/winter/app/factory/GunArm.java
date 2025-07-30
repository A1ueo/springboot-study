package com.winter.app.factory;

// @Component
// @Primary // 같은 타입이 여러개일 경우 우선적으로 들어감
public class GunArm implements Arm {
	
	@Override
	public void attack() {
		System.out.println("Gun");
	}
}
