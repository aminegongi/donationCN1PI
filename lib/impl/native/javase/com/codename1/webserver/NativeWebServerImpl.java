package com.codename1.webserver;
import com.codename1.io.FileSystemStorage;

public class NativeWebServerImpl implements com.codename1.webserver.NativeWebServer{
    private SimpleWebServer server;
    
    
    
    public boolean start(String param, int param1) {
        if (isRunning()) {
            return false;
        }
        server = new SimpleWebServer(param1, 
                new java.io.File(FileSystemStorage.getInstance().toNativePath(param)));
        server.start();
        return true;
    }

    public boolean stop() {
        server.stop();
        return true;
        
    }

    public boolean isRunning() {
        return server != null && server.isRunning();
    }

    public boolean isSupported() {
        return true;
    }

}
