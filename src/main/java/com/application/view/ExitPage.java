package com.application.view;

public class ExitPage implements IView {

	@Override
	public void render() {
		System.out.println("++++++++Thanks for the visit++++++++");
		System.exit(0);
	}
}
