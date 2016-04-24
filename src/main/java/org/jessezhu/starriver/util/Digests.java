/*
 * The MIT License
 *
 * Copyright 2016  jesse.zwd@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jessezhu.starriver.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import org.apache.commons.lang3.Validate;

public class Digests {
    
    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";
    
    private static SecureRandom random = new SecureRandom();
    
    public static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations){
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            
            if(salt != null){
                digest.update(salt);
            }
            
            byte[] result = digest.digest(input);
            
            for(int i = 1; i <iterations; i++){
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }
    
    public static byte[] sha1(byte[] input){
        return digest(input, SHA1, null, 1);
    }
    
    public static byte[] sha1(byte[] input, byte[] salt){
        return digest(input, SHA1, salt, 1);
    }
    
    public static byte[] sha1(byte[] input, byte[] salt, int iterations){
        return digest(input, SHA1, salt, iterations);
    }
    
    public static byte[] generateSalt(int numBytes){
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
        
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }
    
    private static byte[] digest(InputStream input, String algorithm) throws IOException{
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);
            
            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }
            return messageDigest.digest();
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }
    
    public static byte[] md5(InputStream input) throws IOException{
        return digest(input, MD5);
    }
    
    public static byte[] sha1(InputStream input) throws IOException{
        return digest(input, SHA1);
    }
}
