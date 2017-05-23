package algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.security.SecureRandom;

public class algorithm {
	vector v,tmp;
	BigDecimal x,y;
	BigDecimal norm,normsqrt;
	BigDecimal m;
	BigDecimal mmultv1x,mmultv1y,newxv2,newyv2;
	BigDecimal mint=BigDecimal.ONE;
	public vector choosevectors(){
		x=new BigDecimal(new BigInteger(20,new SecureRandom()));
		y=new BigDecimal(new BigInteger(20, new SecureRandom()));
		v=new vector(x,y);
		return v;
	}
	//check if vectors is lineary independent
	public boolean checkvectors(vector v1,vector v2){
		BigDecimal result1;
		//BigDecimal result2;
		//result1=v2.getX().divide(v1.getX(),MathContext.DECIMAL128);
		//result2=v2.getY().divide(v1.getY(),MathContext.DECIMAL128);
		//checking det
		result1=v1.getX().multiply(v2.getY()).subtract(v2.getX().multiply(v1.getY()));
		if(result1.equals(BigDecimal.ZERO)){
			return false;
		}
		else{
			return true;
		}
	}
	
	public BigDecimal euclideannorm(vector v){
		norm=v.getX().multiply(v.getX()).add(v.getY().multiply(v.getY()));
		//normsqrt=sqrt(norm);
		return norm;
	}
	
	public vector GaussLegendre(vector v1,vector v2){
		
		while(true){
			m=InnerProduct(v2,v1).divide(euclideannorm(v1),MathContext.DECIMAL128);
			m=m.add(BigDecimal.valueOf(0.5));
			mint=new BigDecimal(m.toBigInteger());
			if(mint.equals(BigDecimal.ZERO)){
				break;
			}
			mmultv1x=mint.multiply(v1.getX());
			mmultv1y=mint.multiply(v1.getY());
			v1=new vector(mmultv1x,mmultv1y);
			newxv2=v2.getX().subtract(v1.getX());
			newyv2=v2.getY().subtract(v1.getY());
			v2= new vector(newxv2,newyv2);
			
			if(euclideannorm(v2).compareTo(euclideannorm(v1))<0){
			tmp=v1;
			v1=v2;
			v2=tmp;
			}
		}
		return v2;
	}
	
	public BigDecimal InnerProduct(vector v1,vector v2){
		BigDecimal result;
		result=(v1.getX().multiply(v2.getX())).add(v1.getY().multiply(v2.getY()));
		return result;
	}
	
	public static BigInteger sqrt(BigInteger x) {
	    BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
	    BigInteger div2 = div;
	    // Loop until we hit the same value twice in a row, or wind
	    // up alternating.
	    for(;;) {
	        BigInteger y = div.add(x.divide(div)).shiftRight(1);
	        if (y.equals(div) || y.equals(div2))
	            return y;
	        div2 = div;
	        div = y;
	    }
	}
}
