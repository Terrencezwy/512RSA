package Key;

public class RSAtest {
    public static void main(String []args){
        /*
        test for encryption and decryption
         */

//        String plaintext_s = "adsfadsfaafsdf,asdasfasdFFSFSDF1235416asdfadf";
//        KeyGenerator k = new KeyGenerator();
//        String PublicKey = k.getPublicKey();
//        String PrivateKey = k.getPrivateKey();
//        System.out.println("public key is : " + PublicKey);
//        System.out.println("private key is : "+ PrivateKey);
//        String cypotext = k.encrypt(plaintext_s);
//        System.out.println(cypotext);
//        String decrypt_text = k.getDecryptedText(cypotext);
//        System.out.println("decrypted text is :" + decrypt_text);

        /*
        test for sign
         */
        String plaintext_s = "adsfadsfaafsdf,asdasfasdFFSFSDF1235416asdfadf";
        keyPrivate prk = new keyPrivate();
        keyPublic puk = new keyPublic();
        KeyGenerator k = new KeyGenerator(prk, puk);
        String PublicKey = puk.getPublicKey();
        String PrivateKey = prk.getPrivateKey();
        System.out.println("public key is : " + PublicKey);
        System.out.println("private key is : "+ PrivateKey);
        String sign = prk.Sign(plaintext_s);
        System.out.println("The sign is: " + sign);
        String veri_text = puk.verifySign(sign);
        System.out.println("verify sign text is :" + veri_text);
    }
}
