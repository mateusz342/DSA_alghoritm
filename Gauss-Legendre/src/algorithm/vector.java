package algorithm;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.spec.ECPoint;

public class vector {
		//private long x;
		//private long y; 
		private  BigDecimal x;
		private BigDecimal y;
		public vector(){
			
		}
		public vector(BigDecimal x, BigDecimal y){
			this.x=x;
			this.y=y;
		}
		public BigDecimal getX(){
			return x;
		}
		
		public BigDecimal getY(){
			return y;
		}
		
		public void setX(BigDecimal x){
			this.x=x;
		}
		
		public void setY(BigDecimal y){
			this.y=y;
		}
}
