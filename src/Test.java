import java.util.Map;


/**
 * @author Mohamed SABI
 *
 */

class ResultIncorrectException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResultIncorrectException() {
        super("Faux résultat");
    }
}

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ResultIncorrectException{
		Forex forex = new Forex();
		Map<String, Float> inEuro = forex.getInEuro();
		
		if (inEuro.get("EUR") != (float) 1.0) throw new ResultIncorrectException();
		if (inEuro.get("CHF") != (float) 4.0) throw new ResultIncorrectException();
		if (inEuro.get("JPY") != (float) 2.0) throw new ResultIncorrectException();
		if (inEuro.get("USD") != (float) 0.5) throw new ResultIncorrectException();
		if (inEuro.get("GPB") != (float) 8.0) throw new ResultIncorrectException();
		if (inEuro.get("TND") != (float) 0.1) throw new ResultIncorrectException();
		
		else System.out.println("OOOK!");
		
		ValoCB application = new ValoCB(inEuro);
		

	}

}

