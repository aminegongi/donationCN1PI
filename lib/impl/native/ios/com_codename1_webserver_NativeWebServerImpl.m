#import "com_codename1_webserver_NativeWebServerImpl.h"

#import "GCDWebServer.h"

@implementation com_codename1_webserver_NativeWebServerImpl

-(BOOL)start:(NSString*)param param1:(int)param1{
    param = [param substringFromIndex:7];
    
    dispatch_async(dispatch_get_main_queue(), ^(void) {
        webServer = [[GCDWebServer alloc] init];
        
        [webServer addGETHandlerForBasePath:@"/" directoryPath:param indexFilename:@"index.html" cacheAge:3600 allowRangeRequests:YES];
        //[webServer d
        [webServer startWithPort:param1 bonjourName:nil];
    });
    
    return YES;
}

-(BOOL)stop{
    dispatch_async(dispatch_get_main_queue(), ^(void) {
        [webServer stop];
    });
    return YES;
}

-(BOOL)isRunning{
    return webServer != nil && webServer.running;
}

-(BOOL)isSupported{
    return YES;
}

-(void) dealloc {
    [super dealloc];
    [webServer dealloc];
    webServer = nil;
}

@end
