package com.application;

import com.application.model.view.View;

/**
 * This is entry point of application.
 * Can be executed in standalone manner and it will prompt for user input.
 * With the input provided interactive checkout flow will begin.
 */
public class Application {

	public static void main(String[] args) {
		System.out.println("System is initializing, Please wait...");
		initialize();
		System.out.println("++++++++Welcome to the store++++++++");
		loadIndex();
	}

	private static void loadIndex() {
		ApplicationContext.getApplicationContext().getView(View.INDEX).render();
	}

	private static void initialize() {
		ApplicationContext.getApplicationContext().initialize();
	}
}
