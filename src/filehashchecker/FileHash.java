/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehashchecker;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author juan
 */
public class FileHash {
    private String filehash;
    
     public String getHashValue(String vfilePath,String vhashType) throws NoSuchAlgorithmException{
      filehash="";
      MessageDigest md = MessageDigest.getInstance(vhashType);
        try (InputStream is = Files.newInputStream(Paths.get(vfilePath))) {
            DigestInputStream dis = new DigestInputStream(is, md);
              byte[] buffer = new byte[1024];
              int numRead;
                 do {
           numRead = is.read(buffer);
                       if (numRead > 0) {
                           md.update(buffer, 0, numRead);
                       }
                 } while (numRead != -1);
       is.close();
       byte[] digest = md.digest();
       for (int i=0; i < digest.length; i++) {
           filehash += Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
       }
            } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    return filehash;
    }
     
}