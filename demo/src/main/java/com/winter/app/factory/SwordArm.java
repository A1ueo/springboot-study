package com.winter.app.factory;

// @Component
public class SwordArm implements Arm {

	@Override
	public void attack() {
		System.out.println("Sword");
	}
}
