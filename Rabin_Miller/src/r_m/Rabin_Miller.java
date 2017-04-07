package r_m;
import java.util.*;
import java.math.*;
public class Rabin_Miller {
	Random generator=new Random();
	BigInteger two=new BigInteger("2");
	ArrayList<Integer> table = new ArrayList<Integer>();
	public Rabin_Miller(BigInteger n,int iterations){
		this.n=n;
		this.iterations=iterations;
		//this.a=a;
	}
	public int R_M(){
		BigInteger nminus1=n.subtract(BigInteger.ONE);
		do{
			if(nminus1.mod(two.pow(a)).equals(BigInteger.ZERO)){
				table.add(a);
			}
			iterator++;
			a++;
		}while(!(two.pow(a).compareTo(n)>0));
		
		a=table.get(table.size()-1);
		BigInteger m=nminus1.divide(two.pow(a));
		int wlen=n.bitLength();
		BigInteger b;
		BigInteger z;
		for(int i=0;i<iterations;i++){
			int straznik=0;
			int straznik1=0;
			do{
			 b=new BigInteger(wlen,new Random());
			}while(!(b.compareTo(BigInteger.ONE)>0) && !(b.compareTo(nminus1)<0));
			z=b.modPow(m, n);
			if(z.equals(BigInteger.ONE) || z.equals(nminus1)){
				continue;
			}
			else{
				for(int j=0;j<a-1;j++){
					z=z.pow(2).mod(n);
					if(z.equals(nminus1)){
						straznik++;
						break;
					}
					if(z.equals(BigInteger.ONE)){
						straznik1++;
						break;
					}
				}
				if(straznik==1){
					continue;
				}
				
				if(straznik1==1){
					getComposite();
					return 0;
				}
				}
			getComposite();
			return 0;
		}
		getProbablyPrime();
		return 1;
		}

	
	
	
	public long gcd(long a1,long b){
//		if(b>a){
//			tmp=a;
//			a=b;
//			b=tmp;
//			
//		}
		do{
		q=a1/b;
		c=b;
		b=a1-q*b;
		a1=c;
	}
	while(b>0);
		return a1;
		}
	
	
	public void getComposite(){
		System.out.println("Testowana liczba jest z³o¿ona!");
	}
	
	public void getProbablyPrime(){
		System.out.println("Prawdopodobnie pierwsza!");
	}
	
	long q;
	long c;
	long b;
	long a1;
	long k=1;
	double q1=0;
	long wyk;
	BigInteger n;
	int iterations;
	int a=0;
	int iterator=0;
	int i=0;
	int l=0;
	BigInteger a2;
	int g=0;
	int comp1;
	BigInteger a3;
	private int comp3;
	private int l1=0;
}
