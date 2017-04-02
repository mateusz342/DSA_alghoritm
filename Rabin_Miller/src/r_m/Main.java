package r_m;
import java.math.BigInteger;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		Random generateRandom=new Random();
		int p=1024;
		int q=160;
		int seedlen;
		do{
		seedlen=generateRandom.nextInt(q*2);
		}while(seedlen<q);
		
		Primes_Generator generator=new Primes_Generator();
		generator.generatePrimes(p, q, seedlen);
		BigInteger qtoDSA=generator.getq();
		BigInteger ptoDSA=generator.getp();
		byte[] pp=ptoDSA.toByteArray();
		byte[] qq=qtoDSA.toByteArray();
		//a1.R_M();

	}

}
