package Key;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public abstract class Key {
    private BigInteger N;

    public Key(){

    }
    public Key(BigInteger n) {
        N = n;
    }

    protected BigInteger getN(){
        return N;
    }
    protected byte[] convert(String s) throws UnsupportedEncodingException {
        byte[] b = s.getBytes("utf-8");
        return b;
    }

    protected void setN(BigInteger p, BigInteger q){
        N = p.multiply(q);
    }
}
