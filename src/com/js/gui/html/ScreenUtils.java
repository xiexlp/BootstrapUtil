package com.js.gui.html;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenUtils {
	
	public static Dimension screenDimension() {
		
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		return screensize;
		
	}

}
