package iot.safe.BackEnd;

import java.security.MessageDigest;

public class Hashing {

    public String SHA256(String password) {
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return hexString.toString();
    }
}