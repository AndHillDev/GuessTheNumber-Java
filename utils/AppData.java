package com.germandolllover.guessthenumber.utils;

public class AppData {

	private String appName;
	private String appVersion;
	private int appWidth;
	private int appHeight;
	private boolean isFullScreen;
	private boolean isMaximized;
	private boolean isResizeable;
	
	public AppData() {
		this.appName = "Unknown";
		this.appVersion = "0.0";
		this.appWidth = 800;
		this.appHeight = 600;
		this.isFullScreen = false;
		this.isMaximized = false;
		this.isResizeable = false;
	}
	
	public AppData(String _appName, String _appVersion, int _appWidth, int _appHeight, boolean _isFullScreen, boolean _isMaximized, boolean _isResizeable) {
		this.appName = _appName;
		this.appVersion = _appVersion;
		this.appWidth = _appWidth;
		this.appHeight = _appHeight;
		this.isFullScreen = _isFullScreen;
		this.isMaximized = _isMaximized;
		this.isResizeable = _isResizeable;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getAppWidth() {
		return appWidth;
	}

	public void setAppWidth(int appWidth) {
		this.appWidth = appWidth;
	}

	public int getAppHeight() {
		return appHeight;
	}

	public void setAppHeight(int appHeight) {
		this.appHeight = appHeight;
	}

	public boolean isFullScreen() {
		return isFullScreen;
	}

	public void setFullScreen(boolean isFullScreen) {
		this.isFullScreen = isFullScreen;
	}

	public boolean isMaximized() {
		return isMaximized;
	}

	public void setMaximized(boolean isMaximized) {
		this.isMaximized = isMaximized;
	}

	public boolean isResizeable() {
		return isResizeable;
	}

	public void setResizeable(boolean isResizeable) {
		this.isResizeable = isResizeable;
	}
	
	
}