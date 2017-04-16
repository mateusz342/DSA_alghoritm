package r_m;

import java.awt.List;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Primes_Generator {
	BigInteger p,q;
	int l=0;
	BigInteger two=new BigInteger("2");
	int iterations=56;
	BigInteger l1=new BigInteger("1024");
	BigInteger l2=new BigInteger("2048");
	BigInteger l3=new BigInteger("3072");
	BigInteger n1=new BigInteger("160");
	BigInteger n2=new BigInteger("224");
	BigInteger n3=new BigInteger("256");
	int outlen;
	BigInteger domain_seed;
	int licznik=0;
	
	//generation of probable primes p and q using SHA-256 hash function and Rabin-Miller test
	public Object generatePrimes(int L,int N, int seedlen){
		if(L==1024 && N==160){System.out.println("Bits length, ok!");}
		else if(L==2048 && N==224){System.out.println("Bits length, ok!");}
		else if(L==2048 && N==256){System.out.println("Bits length, ok!");}
		else if(L==3072 && N==256){System.out.println("Bits length, ok!");}
		else{
			getInvalid();
			return null;
			}
		
		if(seedlen<N){
			getInvalid();
			return null;
		}
		Random generateRandom=new Random();
	
		do{
		outlen=generateRandom.nextInt(N*2);
		}while(outlen<N);
		double ceil=Math.ceil((double) L/outlen);
		int n=(int) ceil-1;
		int b=L-1-(n*outlen);
		//domain parameter seed
		int checkq;
		int checkp;
		//q generation
		do{
		do{
		do{
		domain_seed=new BigInteger(seedlen,new SecureRandom());
		byte[] domain_parameter_seed=domain_seed.toByteArray();
		byte[] hash=SHA256(domain_parameter_seed);
		BigInteger Hash=new BigInteger(hash);
		int exponent=N-1;
		BigInteger U=Hash.mod(two.pow(exponent));
		q=(two.pow(exponent)).add(U).add(BigInteger.ONE).subtract(U.mod(two));
		}while(q.mod(two).equals(BigInteger.ZERO));
		//Rabin-Miller test
		Rabin_Miller testq=new Rabin_Miller(q,iterations);
		checkq=testq.R_M();
		}while(checkq==0);
		
		//p generation
		
		BigInteger[] V = new BigInteger[n];
		BigInteger offset=BigInteger.ONE;
		
		for(int counter=0;counter<4*L-1;counter++){
			do{
			
			for(int j=0;j<n;j++){
			
			BigInteger tohash=domain_seed.add(offset).add(BigInteger.valueOf(j)).mod(two.pow(seedlen));
			byte[] bytehash=tohash.toByteArray();
			byte[] hash=SHA256(bytehash);
			BigInteger Hash=new BigInteger(hash);
			V[j]=Hash;
			
			}
			
		BigInteger W;
		W=V[0];
		int n1=1;
		for(int j=0;j<V.length-1;j++){
		
			W=W.add(V[j+1].multiply(two.pow(n1*outlen)));
			n1++;
		}
		W=W.add(V[V.length-1].mod(two.pow(b)).multiply(two.pow(n*outlen)));
		BigInteger X;
		X=W.add(two.pow(L-1));
		BigInteger c;
		c=X.mod(two.multiply(q));
		p=X.subtract(c.subtract(BigInteger.ONE));
		}while(p.mod(two).equals(BigInteger.ZERO));
		
			if(p.compareTo(two.pow(L-1))<0){
			offset=offset.add(BigInteger.valueOf(n)).add(BigInteger.ONE);
			continue;
		}
		//RabinMiller test for p
		Rabin_Miller testp=new Rabin_Miller(p,iterations);
		checkp=testp.R_M();
		if(checkp>0){
			getValid();
			this.p=p;
			this.q=q;
			boolean tru=p.isProbablePrime(100);
			return 1;
		}
			offset=offset.add(BigInteger.valueOf(n)).add(BigInteger.ONE);
		}
		}while(licznik==0);
		return 0;
	}

//SHA-256 hash function
	private static byte[] SHA256(byte[] input){
		MessageDigest md=null;
		
		try{
			md=MessageDigest.getInstance("SHA-256");
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return md.digest(input);
		
	}
//pow for (BigInteger,BigInteger)
	public BigInteger pow(BigInteger base, BigInteger exponent) {
		  BigInteger result = BigInteger.ONE;
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
		}

	public void getValid(){
		System.out.println("VALID!");
	}
	
	public void getInvalid(){
		System.out.println("INVALID!");
}
	public BigInteger getp(){
		return p;
	}
	public BigInteger getq(){
		return q;
	}
	
}