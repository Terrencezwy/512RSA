package Key;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class keyPrivate extends Key{
    private BigInteger d;


    protected BigInteger getD(){
        return d;
    }

    protected void setD(BigInteger e, BigInteger phi){
        d = e.modInverse(phi);
    }

    private BigInteger decrypt(BigInteger cypotext){
        BigInteger plaintext;
        plaintext = cypotext.modPow(d, getN());
        return plaintext;
    }
    public String getDecryptedText(String s){
        BigInteger decryptedText;
        BigInteger cypotext = new BigInteger(s);

        decryptedText = decrypt(cypotext);
        byte[]a = decryptedText.toByteArray();
        try {
            String sa = new String(a, "utf-8");
//            System.out.println(sa);
            return sa;
        }
        catch(UnsupportedEncodingException ex) {
            System.out.println("Exception throws:" + ex);
        }
        return null;
    }

    public String Sign(String s){
        BigInteger cypotext = new BigInteger("0");
        try{
            byte b[] = convert(s);
            BigInteger plaintext = new BigInteger(b);
            cypotext = plaintext.modPow(d, getN());
        }
        catch(UnsupportedEncodingException ex){
            System.out.println("Exception throw:" + ex);
        }
        return cypotext.toString();
    }

    public String getPrivateKey(){
        StringBuffer s = new StringBuffer();
        System.out.println("This is private key:");
        System.out.println("N: " + getN() + " " + "D:" + getD());
        s.append("N:");
        s.append(getN());
        s.append("   D:");
        s.append(getD());
        return s.toString();
    }
}
