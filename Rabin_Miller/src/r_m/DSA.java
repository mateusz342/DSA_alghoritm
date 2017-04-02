package r_m;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DSA {
	BigInteger p,q,g;
	BigInteger k,x,y;
	BigInteger r,s;
	byte[] message;
	boolean found=true;
	BigInteger h;
	public void generator(){
		do{
		g=new BigInteger(p.bitCount()-1,new SecureRandom());
		h=new BigInteger(p.bitCount()-1,new SecureRandom());
		BigInteger h1=(p.subtract(BigInteger.ONE)).divide(q);
		if((g.modPow(q, p).equals(BigInteger.ONE))&&(g.mod(p).equals(h1))){
			found=false;
		}
		}while(found);
	}
	
	public void KeySet(){
		k=new BigInteger(q.bitCount()-1 ,new SecureRandom());
		x=new BigInteger(q.bitCount()-1 ,new SecureRandom());
		y=g.modPow(x, p);
		
		this.k=k;
		this.x=x;
		this.y=y;
}
	
	public void Compute(){
		r=(g.modPow(k, p)).mod(q);
		BigInteger s1=k.modInverse(q);
		BigInteger message1= new BigInteger(message);
		s=(s1.multiply(message1.add(x.multiply(r)))).mod(q);
		
		this.r=r;
		this.s=s;
		
	}
	
	
	
}
