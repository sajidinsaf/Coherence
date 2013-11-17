package contacts;

public class Contact {

	
	public static void main(String[] args) {
		String[] array = new String[] {"dsf", "sf", null, "sdf", "sdf"};
		
		String a = null;
		
		for (int i=0; i<array.length || (a=array[i]) != null ; i++) {
			System.out.println(a);
		}
	}
}
