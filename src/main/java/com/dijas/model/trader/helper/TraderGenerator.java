package com.dijas.model.trader.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class TraderGenerator {
	
	
	private static final Random random = new Random();

	public static void main(String[] args) throws IOException {
		String fileName = args.length > 0 ? args[1] : DEFAULT_OUTPUT_FILE_PATH;
		PrintWriter writer = new PrintWriter(new File(fileName));
		writer.println("First Name,LastName,Gender,DateOfBirth,Home Street,Home PostCode,Home Country,Work Street,Work PostCode,Work Country,Home Phone,Work Phone");
		
		for (int i=0; i<100; i++) {
			String lastName = getRandomSurname();
			String firstName = firstNames.get(random(firstNames.size()));
			String gender = firstName.split("-")[1];
			firstName = firstName.split("-")[0];

			StringBuffer c  = new StringBuffer();
			c.append(firstName)
			.append(",")
			.append(lastName)
			.append(",")
			.append(gender)
			.append(",")
			.append(getRandomDateOfBirth())
			.append(",")
			.append(getAddress())
			.append(",")
			.append(getAddress())
			.append(",")
			.append(getPhoneNumber())
			.append(",")
			.append(getPhoneNumber());
			
			writer.println(c);
		}

		writer.flush();
		writer.close();

	}
	
	private static int random(int limit) {
		return random.nextInt(limit);
	}
	
	private static long getRandomDateOfBirth() {
		int year = 1955 + random(29);
		int month = random(12) + 1;
		int day = random(28);
		
		return new GregorianCalendar(year, month, day).getTime().getTime();
	}
	
	private static String getAddress() {
		return getRandomStreetName() + "," + postCodes[random(postCodes.length)] +"," + countries[random(countries.length)];
	}
	
	private static String getPhoneNumber() {
		
		String start = "" + random(999999);
		char[] c = new char[9-start.length()];
		Arrays.fill(c, (random(10)+"").toCharArray()[0]);
		return "'+" + random(99) + "-" + start + new String(c)+"'";
	}
	
	
	
	private static String getRandomStreetName() {
		return random(200) + " " + streetNames[random(streetNames.length)] + " " + streetNames[random(streetNames.length)];
	}
	
	private static String getRandomSurname() {
		
		char[] c = new char[4 + random.nextInt(7)];
		
		c[0] = (char) ('A' + random.nextInt(26));
		
		//if the first is a vowel then the next should be a consonant, inverse otherwise
		boolean consonant = upperCaseVowels.contains(String.valueOf(c[0])); 
		
		for (int i=1; i<c.length; i++) {
			//add a random of one or two vowels/consonants
			int chars = random.nextInt(2) + 1;
			char[] array = consonant ? consonants : vowels;
			--i;
			for (int j=0; j<chars && i<c.length-1; j++) {
				++i;
				c[i] = array[random.nextInt(array.length)];
			}
			consonant = !consonant;
		}
		return new String(c);
	}

	
	private static final List<String> firstNames = Arrays.asList("Lisa-F", "Abi-F", "Julia-F", "Kylie-F", "Martha-F", "Tony-M",  "Jon-M", "Ray-M", "Jason-M", "Cameron-M", "Dave-M", "Joe-M", "Tim-M", "Ed-M", "Gabs-M", "Samuel-M", "Kit-M", "Victoria-F", "Grace-F", "Toby-M","Kiran-M", "Shamsu-M", "Arif-M", "Maya-F", "Hoshi-M", "Rita-F");
	
	private static final List<String> upperCaseVowels = Arrays.asList("A", "E", "I", "O", "U");
	
	private static final char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
	
	private static final char[] consonants = new char[] {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
		
    private static final String[] postCodes = new String[] {"BS98 1TL","BX1 1LT","BX2 1LB","BX3 2BB","BX5 5AT","CF10 1BH","CF99 1NA","DE99 3GG","DH98 1BT","DH99 1NS","E16 1XL","E20 2AQ","E20 2BB","E20 2ST","E20 3BS","E20 3EL","E20 3ET","E20 3HB","E20 3HY","E98 1SN","E98 1ST","E98 1TT","EC2N 2DB","EC4Y 0HQ","EH99 1SP","G58 1SB","GIR 0AA","IV21 2LR","L30 4GB","LS98 1FD","N1 9GU","N81 1ER","NG80 1EH","NG80 1LH","NG80 1RH","NG80 1TH","PH1 5RB","S2 4SU","S6 1SW","SE1 8UJ","SN38 1NW","SW1A 0AA","SW1A 0PW","SW1A 1AA","SW1A 2AA","SW1P 3EU","SW1W 0DT","TW8 9GS","W1A 1AA","W1D 4FA","W1N 4DJ","W7 1DR"};

	private static final String[] streetNames = new String[] {"Upper", "Lower", "Union", "Grace", "Hill", "Tower", "Bromwood", "Avenue", "Close", "Church", "Rover", "High", "Spring", "Nightingale", "London", "Bristol"};

	private static final String[] countries = new String[] {"GB", "USA", "IND", "AUS", "CHI"};
	
	public static final String DEFAULT_OUTPUT_FILE_PATH = "/Users/sajid/Documents/workspace/CoherenceGIT/coherence/src/main/resources/com/dijas/model/trader/Traders.csv";
}
