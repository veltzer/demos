package programming.solutions.sol0301syntax;

public class BasicJavaSyntax {

	public static void main(String[] args) {
		int[] arr=new int[100];
		
		for (int i = 0; i <arr.length; i++) {
			arr[i]=(int)(Math.random()*1000)+1;
		}
		double average=0;
		for(int i=0;i<arr.length;i++) {
			average+=arr[i];
		}
		System.out.println("The Average is "+(average/arr.length));
	}
}
