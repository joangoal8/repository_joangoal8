package org.dressTech.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
	
	public static String HashSHA1(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
	    digest.reset();
	    byte[] hash = digest.digest(value.getBytes("UTF-8"));
	    
	    return new String(hash, "UTF-8");
	}
}
