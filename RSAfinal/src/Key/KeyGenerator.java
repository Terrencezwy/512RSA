package Key;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
public class KeyGenerator {
    private static final int MIN_BIT_LENGTH = 1024;

    private BigInteger phi;

    private BigInteger p;

    private BigInteger q;

    SecureRandom rand = new SecureRandom();

    private int bitLength;

    public KeyGenerator(keyPrivate prk, keyPublic puk){  //keyPrivate prk, keyPublic puk
//        keyPrivate prk = new keyPrivate();
//        keyPublic puk = new keyPublic();
        setBitLength(2000);
        Generate_key(puk, prk);
    }
    public final boolean isNull(Object obj){
        return !(obj != null);
    }

    public final boolean isPositive(BigInteger a){
        return (a.compareTo(BigInteger.ZERO) > 0);
    }

    public void setBitLength(int bitLength) {
        this.bitLength = (bitLength >= MIN_BIT_LENGTH ? bitLength : MIN_BIT_LENGTH);
        return;
    }

    private void computePhi(){
        phi = multiplyBig(pMinorsOne(),qMinorsOne());           //
    }

    private BigInteger pMinorsOne(){
        return p.subtract(BigInteger.ONE);
    }

    private BigInteger qMinorsOne(){
        return q.subtract(BigInteger.ONE);
    }

    private BigInteger multiplyBig(BigInteger a, BigInteger b){
        return a.multiply(b);
    }

    private void setPQ(BigInteger a, BigInteger b){
        if(isNull(a) || !isPositive(a) || isNull(b) || !isPositive(b))
            return;
        else{
            if(isPositive(a.subtract(b))){
                p = a;
                q = b;
            } else if(isPositive(b.subtract(a))){
                p = b;
                q = a;
            }else{
                return;
            }
        }
    }



    public void Generate_key(keyPublic puk, keyPrivate prk){
        setPQ(BigInteger.probablePrime(75 * bitLength / 100, rand),
                BigInteger.probablePrime(25 * bitLength / 100, rand) );
        puk.setN(p, q);
        prk.setN(p, q);
        computePhi();
        puk.setE(bitLength, rand, phi);
        prk.setD(puk.getE(), phi);
    }

}