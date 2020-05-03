#import <Foundation/Foundation.h>
#import <GCDWebServer.h>

@interface com_codename1_webserver_NativeWebServerImpl : NSObject {
    GCDWebServer* webServer;
}

-(BOOL)start:(NSString*)param param1:(int)param1;
-(BOOL)stop;
-(BOOL)isRunning;
-(BOOL)isSupported;
@end
