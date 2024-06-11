package com.example.accapplication.Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Random;

public class AccountUtils {
    public static int generateAccountNumber(){
        Random random = new Random();
        int accountNumber =  random.nextInt(900000000) + 1000000000;
        return accountNumber;
    }


    public static KeyPair generateRsaKey(){

        KeyPair keyPair;

        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch(Exception e){
            throw new IllegalStateException();
        }

        return keyPair;
    }

}
