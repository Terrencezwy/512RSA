package Key;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class keyPublic extends Key{
    private BigInteger e;


    BigInteger getE(){
        return e;
    }

    public void setE(int bitLength, SecureRandom rand, BigInteger phi){
        BigInteger i;
        for (i = BigInteger.probablePrime((bitLength / 10), rand);
             i.compareTo(getN()) < 0;
             i = i.nextProbablePrime()){
            if (i.gcd(phi).equals(BigInteger.ONE)){
                e = i;
                break;
            }
        }
    }


    public String encrypt(String s){
        BigInteger cypotext = new BigInteger("0");
        try{
            byte b[] = convert(s);
            BigInteger plaintext = new BigInteger(b);
            cypotext = plaintext.modPow(e, getN());
        }
        catch(UnsupportedEncodingException ex){
            System.out.println("Exception throws:" + ex);
        }
        return cypotext.toString();
    }
    public String verifySign(String s){
        BigInteger verify_text;
        BigInteger cypotext = new BigInteger(s);
        verify_text = cypotext.modPow(e, getN());
        byte[] a = verify_text.toByteArray();
        try {
            String sa = new String(a, "utf-8");
//            System.out.println(sa);
            return sa;
        }
        catch(UnsupportedEncodingException ex){
            System.out.println("Exception throw" + ex);
        }
        return null;
    }

    public String getPublicKey(){
        StringBuffer s = new StringBuffer();
        System.out.println("This is public key:");
        System.out.println("N: " + getN() + " " + "E:" + getE());
        s.append("N:");
        s.append(getN());
        s.append("   E:");
        s.append(getE());
        return s.toString();
    }


}
