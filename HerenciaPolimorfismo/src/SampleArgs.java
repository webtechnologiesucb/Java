
public class SampleArgs {

	public static void main(String[] args) {
		if(args.length > 0) {
			System.out.println("Argumentos recibidos");
			int cont = 1;
			for(String res : args) {
				String result = String.format("Argumento %d: %s", cont, res);
				System.out.println(result);
				cont++;
			}
		} else {
			System.out.println("No tiene ningun argumento");
		}
	}
}
