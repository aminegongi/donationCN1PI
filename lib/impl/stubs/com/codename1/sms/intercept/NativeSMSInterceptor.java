package com.codename1.sms.intercept;


/**
 * 
 *  @author Shai, Diamond
 */
public interface NativeSMSInterceptor extends com.codename1.system.NativeInterface {

	public void bindSMSListener();

	public void unbindSMSListener();
}
