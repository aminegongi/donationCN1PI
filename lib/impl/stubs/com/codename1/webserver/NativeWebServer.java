package com.codename1.webserver;


/**
 * 
 *  @author shannah
 */
public interface NativeWebServer extends com.codename1.system.NativeInterface {

	public boolean isRunning();

	public boolean start(String docRoot, int port);

	public boolean stop();
}
