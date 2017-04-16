package r_m;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DSA {
	BigInteger p,q,g;
	BigInteger k,x,y;
	BigInteger r,s;
	byte[] message;
	boolean found=false;
	BigInteger h;
	BigInteger two=new BigInteger("2");
	int checkvalid;
	BigInteger M;
	BigInteger m;
	byte[] readdocument;
	BigInteger wynik;
	BigInteger c;
	BigInteger c1; 
	
	public DSA(BigInteger p, BigInteger q){
		this.p=p;
		this.q=q;
	}
	//generation of generator g
	public void generator(){
		BigInteger e;
		e=p.subtract(BigInteger.ONE).divide(q);
		do{
		do{
		h=new BigInteger(p.bitCount()-1,new SecureRandom());
		g=h.modPow(e, p);
		if(!g.equals(BigInteger.ONE)){
			found=true;
		}
		}while(!found);
		
		this.g=g;
		checkvalid=validategenerator();
		}while(checkvalid==0);
		//return g;
	}
	
	//Assurance of the Validity of the Generator g
	public int validategenerator(){
		if (g.compareTo(two)<0 && ( g.compareTo(p.subtract(BigInteger.ONE))>0)){
			getInvalid();
			return 0;
		}
		if(g.modPow(q, p).equals(BigInteger.ONE)){
			getPartiallyvalid();
		return 1;}
		getInvalid();
		return 0;
	}
	//KeySet of DSA
	public void KeySet(){
		do{
			c1=new BigInteger(q.bitCount()+64,new SecureRandom());
		}while(c1.compareTo(BigInteger.ZERO)<0);
		//k=new BigInteger(q.bitCount()-1 ,new SecureRandom());
		k=(c1.mod(q.subtract(BigInteger.ONE))).add(BigInteger.ONE);
		do{
		c= new BigInteger(q.bitCount()+64, new SecureRandom());
		}while(c.compareTo(BigInteger.ZERO)<0);
		x=(c.mod(q.subtract(BigInteger.ONE))).add(BigInteger.ONE);
		//x=new BigInteger(q.bitCount()-1 ,new SecureRandom());
		y=g.modPow(x, p);
		
		this.k=k;
		this.x=x;
		this.y=y;
}
	//calculation of the parameters for DSA
	public void Compute() throws IOException{
		r=(g.modPow(k, p)).mod(q);
		BigInteger s1=k.modInverse(q);
		readdocument=readDocument();
		byte[] SHA2out=SHA256(readdocument);
		m=new BigInteger(SHA2out);
		//BigInteger message1= new BigInteger(message);
		s=(s1.multiply(m.add(x.multiply(r)))).mod(q);
		
		this.r=r;
		this.s=s;
		this.readdocument=readdocument;
		
	}
	//DSA verification 
	public void Verification(BigInteger r, BigInteger s, byte[] readdocument){
		byte[] Message=SHA256(readdocument);
		BigInteger m1=new BigInteger(Message);
		BigInteger w=s.modInverse(q).mod(q);
		BigInteger u1=w.multiply(m1).mod(q);
		BigInteger u2=w.multiply(r).mod(q);
		BigInteger v= ((g.modPow(u1, p).multiply(y.modPow(u2, p))).mod(p)).mod(q);
		
		if(v.compareTo(r)==0){
			getSignatureCorrect();
		}
		else
			getSignatureinCorrect();
		
	}
	//read document method
	public byte[] readDocument() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("D:/git/Rabin_Miller/src/document.txt"));
		String everything;
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		  everything = sb.toString();
		} finally {
		    br.close();
		}
		message=everything.getBytes();
		return message;
	}
	
	//SHA256 hash function
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
	
	public BigInteger pow(BigInteger base, BigInteger exponent) {
		  BigInteger result = BigInteger.ONE;
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
		}
	
	public BigInteger getr(){
		return r;
	}
	
	public BigInteger gets(){
		return s;
	}
	public BigInteger getg(){
		return g;
	}
	
	public byte[] getReadDocument(){
		return readdocument;
	}
	public void getInvalid(){
		System.out.println("Valid generator!");
	}
	
	public void getPartiallyvalid(){
		System.out.println("Partially valid generator!");
	}
	public void getSignatureCorrect(){
		System.out.println("Signature is correct!!");
	}
	public void getSignatureinCorrect(){
		System.out.println("Signature is incorrect!!");
	}
	
}
