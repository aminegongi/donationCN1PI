/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.utils;

import com.codename1.ui.Image;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Hedi
 */
public class AideImgUpload {
    
FileUploader file ;
String fns;
String imgp;

    public AideImgUpload() {
        
    }


public String upload(String img){

String l = img;
int p = l.indexOf("/",2);
String n = l.substring(p+2 , l.length());
FileUploader f = new FileUploader("https://www.amine.ukla.tn/uploads/imagesAide/");
try {
fns = f.upload(n);
System.out.println("image uploaded successfully");
System.out.println("******"+fns);
return fns;
} catch (Exception ex) {
System.out.println("erreur image upload");
return " ";
}


}

//imgp = Capture.capturePhoto();

    
}
