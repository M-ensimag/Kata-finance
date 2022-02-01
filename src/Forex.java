import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 */

/**
 * @author Mohamed SABI
 *
 */
public class Forex {
	private Map<String, Float> inEuro;

	public Forex() {
		this.inEuro = new HashMap<String, Float>();
		try {
			this.convector();
			this.inEuro.put("EUR", (float) 1);
		} catch (FileNotFoundException e) {
			System.out.println(e);
	        System.exit(1);
		}
		
	}

	/**
	 * @return the inEuro
	 */
	public Map<String, Float> getInEuro() {
		return inEuro;
	}

	/**
	 * @throws FileNotFoundException
	 */
	public void convector() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("./src/main/ressources/Forex.csv"));
		
		for (int i = 0; i < 5; i++) scanner.nextLine();
		
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(",");

			float val;
			
			if (line[0].equals("EUR")) {
				val = 1 / Float.parseFloat(line[2]);
				this.inEuro.put(line[1], val);
			}
			else {
				val = Float.parseFloat(line[2]);
				this.inEuro.put(line[0], val);
			}
		}
		scanner.close();
	}
	
	/**
	 * Affiche les clès et le valeurs
	 */
	public void affiche() {
		  Set<String> cles = inEuro.keySet();
		  System.out.println("Les clés sont: " + cles);

		  Collection<Float> val = inEuro.values();
		  System.out.println("Les valeurs sont: " + val);
	}
}
