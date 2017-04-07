package r_m;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		Random generateRandom=new Random();
		int p=2048;
		int q=256;
		int seedlen;
		do{
		seedlen=generateRandom.nextInt(q*2);
		}while(seedlen<q);
		
		Primes_Generator generator=new Primes_Generator();
		generator.generatePrimes(p, q, seedlen);
		BigInteger qtoDSA=generator.getq();
		BigInteger ptoDSA=generator.getp();
		DSA dsa=new DSA(ptoDSA,qtoDSA);
		dsa.generator();
		dsa.KeySet();
		dsa.Compute();
		BigInteger r=dsa.getr();
		BigInteger s=dsa.gets();
		byte[] readdocument=dsa.getReadDocument();
		dsa.Verification(r, s, readdocument);
		//a1.R_M();

	}

}
