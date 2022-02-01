import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Mohamed SABI
 *
 */
public class ValoCB {
	private Map<String, Float> portfolios;
	private Map<String, Float> products;
	private Map<String, Float> clientsCapital;
	
	/**
	 * Constructeur
	 */
	public ValoCB(Map<String, Float> inEuro) {
		this.portfolios = new HashMap<String, Float>();
		this.products = new HashMap<String, Float>();
		this.clientsCapital = new HashMap<String, Float>();
		
		try {
			this.parsePrices(inEuro);
			this.parseProduct(inEuro);
			this.generate(inEuro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param inEuro
	 * @throws IOException
	 */
	private void parsePrices(Map<String, Float> inEuro) throws IOException {
		Scanner scanner = new Scanner(new File("./src/main/ressources/Prices.csv"));
		
		for (int i = 0; i < 4; i++) scanner.nextLine();
		
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(",");

			String pft = line[0];
			String pro = line[1];
			float  pri = Float.parseFloat(line[4]) * inEuro.get(line[3]);
			
			if (!portfolios.containsKey(pft)) portfolios.put(pft, pri);
			else portfolios.put(pft, portfolios.get(pft) + pri);
			
			if (!products.containsKey(pro)) products.put(pro, pri);
			else products.put(pro, products.get(pro) + pri);	
		}
		
		scanner.close();
	}
	
	/**
	 * @param inEuro
	 * @throws IOException
	 */
	private void parseProduct(Map<String, Float> inEuro) throws IOException {
		Scanner scanner = new Scanner(new File("./src/main/ressources/Product.csv"));
		
		for (int i = 0; i < 5; i++) scanner.nextLine();
		
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(",");

			String cli = line[1];
			float pri = Float.parseFloat(line[2]) * products.get(line[0]);
			
			if (!clientsCapital.containsKey(cli)) clientsCapital.put(cli, pri);
			else clientsCapital.put(cli, clientsCapital.get(cli) + pri);
				
		}
		
		scanner.close();
	}
	
	/**
	 * @param inEuro
	 * @throws IOException 
	 */
	private void generate(Map<String, Float> inEuro) throws IOException {
	    PrintWriter writerPortfolio = new PrintWriter("./src/main/resultats/Reporting-portfolio.csv", "UTF-8");
	    PrintWriter writerClient = new PrintWriter("./src/main/resultats/Reporting-client.csv", "UTF-8");
	    
	    writerPortfolio.println("Portfolio,Price");
	    writerClient.println("Client,Capital");
	    
	    for (String pft : this.portfolios.keySet()) {
	    	writerPortfolio.println(pft + "," + this.portfolios.get(pft));
	    }
	    
	    for (String cli : this.clientsCapital.keySet()) {
	    	writerClient.println(cli + "," + this.clientsCapital.get(cli));
	    }
		

		writerPortfolio.close();
	    writerClient.close();
	}	
}
