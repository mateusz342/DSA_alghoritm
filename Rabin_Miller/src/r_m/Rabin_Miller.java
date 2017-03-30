package r_m;
import java.util.*;
import java.math.*;
public class Rabin_Miller {
	Random generator=new Random();
	public Rabin_Miller(int n){
		this.n=n;
		//this.a=a;
	}
	Scanner in=new Scanner(System.in);
	
	public void R_M(){
		System.out.println("Ile testow chcesz przeprowadzic?");
		int test=in.nextInt();
		while(g<test){
		a=generator.nextInt(n)+1;
		if((n%2==0)||((gcd(n,a)>1)&&(gcd(n,a)<n))){
			zwroczlozona();
			break;
		}
		
		while(q1%2==0)
		{
			q1=(long)(n-1)/Math.pow(2, k);
			k++;
		} 
		a2=BigInteger.valueOf(a);
		a2=a2.modPow(BigInteger.valueOf((long)q1),BigInteger.valueOf(n));//(Math.pow(a, q1))));
		//a2= a2.remainder(BigDecimal.valueOf(n));
		comp1=a2.compareTo(BigInteger.valueOf(1));
		if(comp1==0){
			zwroctestfails();
			l1++;
		}
		a3=BigInteger.valueOf(a);
		a3=a3.modPow(BigInteger.valueOf((long)q1),BigInteger.valueOf(n));
		while((i<=k-1)&&(l<1)){
			//a3=BigInteger.valueOf(a);
			//a3=a3.modPow(BigInteger.valueOf((long)q1),BigInteger.valueOf(n));
			comp3=a3.compareTo(BigInteger.valueOf(n-1));
			if(comp3==0){
				zwroctestfails();
				l++;}
			a3=BigInteger.valueOf(a);
			wyk=(long)Math.pow(2, i);
			a3=a3.modPow(BigInteger.valueOf((long)q1).multiply(BigInteger.valueOf(wyk)),BigInteger.valueOf(n));
			//a3=(long)((Math.pow(a, wyk*q1)));
			//a3=a3%n;
			i++;
		}
		if((l==0)&&(l1==0)){
			zwroczlozona();
		}
		g++;
		i=0;
		l=0;
		}
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
	
	
	public void zwroczlozona(){
		System.out.println("Testowana liczba jest z³o¿ona!");
	}
	
	public void zwroctestfails(){
		System.out.println("Test fails!Prawdopodobnie pierwsza!");
	}
	
	long q;
	long c;
	long b;
	long a1;
	long k=1;
	double q1=0;
	long wyk;
	int n;
	long a;
	int i=0;
	int l=0;
	BigInteger a2;
	int g=0;
	int comp1;
	BigInteger a3;
	private int comp3;
	private int l1=0;
}
