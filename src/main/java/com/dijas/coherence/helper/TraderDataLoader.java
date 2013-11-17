package com.dijas.coherence.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dijas.model.trader.Address;
import com.dijas.model.trader.PhoneNumber;
import com.dijas.model.trader.Trader;
import com.dijas.model.trader.helper.TraderGenerator;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * Following configuration is required to be set-up for this to run
 * 
 * -Dtangosol.coherence.cluster=<clustername>   
 * -Dtangosol.coherence.clusterport=<clusterport>
 * -Dtangosol.pof.enabled=true
 * -Dtangosol.coherence.cacheconfig=<path to the cacheconfig xml file>  
 * (if cacheconfig is on the classpath then ensure that the directory is explicitly 
 * on the classpath even if running from within that eclipse project that contains 
 * it because the cache instance that this process creates does not have a reference 
 * to the project)
 * 
 * 
 * optional settings
 * -Dtangosol.log.level=<0-10>   (0 is off and 10 is most verbose)
 * -Dtangosol.coherence.distributed.localstorage=false   (turn off local storage for this client's instance)
 * 
 * The above configuration items can either be set-up and end vars in the launch profile or
 * be set as env vars in the main method just before calling ensure cluster
 * 
 * @author sajid
 *
 */
public class TraderDataLoader {

	public static void main(String[] args) throws IOException {
		String dataFile = args.length > 0 ? args[0] : TraderGenerator.DEFAULT_OUTPUT_FILE_PATH;
		BufferedReader reader = new BufferedReader(new FileReader(dataFile));
		
		CacheFactory.ensureCluster();
		NamedCache cache = CacheFactory.getCache("trades");
		
		int batchSize = 1024;
		Map<Integer, Trader> mapBatch = new HashMap<Integer, Trader>(batchSize);
		
		//skip the header line
		reader.readLine();
		String line = null;
		int traderId = 0;
		int count = 0;
		while((line = reader.readLine()) != null) {
			++traderId;
			Trader trader = createTrader(traderId, line);
			mapBatch.put(traderId, trader);
			
			if (mapBatch.size() % batchSize == 0) {
				++count;
				cache.putAll(mapBatch);
				mapBatch.clear();
				System.out.print(".");
				System.out.flush();
				if (count == 10) {
					System.out.println();
					count = 0;
				}
				
			}
			
		}
		
		if (mapBatch.size() > 0) {
			cache.putAll(mapBatch);
			mapBatch.clear();
		}

		reader.close();
		
		System.out.println("Uploaded "+cache.size() +" trader details into cache");
		CacheFactory.shutdown();
		

	}

	// "First Name,LastName,Gender,DateOfBirth,Home Street,Home PostCode,Home Country,Work Street,Work PostCode,Work Country,Home Phone,Work Phone"
	private static Trader createTrader(int traderId, String line) {
		int i = 0;
		String[] t = line.split(",");
		String firstName = t[i++];
		String lastName = t[i++];
		String gender = t[i++];
		Date dateOfBirth = new Date(Long.parseLong(t[i++]));
		Address homeAddress = new Address(t[i++], t[i++], t[i++]);
		Address workAddress = new Address(t[i++], t[i++], t[i++]);
		PhoneNumber homePhone = new PhoneNumber(t[i++]);
		PhoneNumber workPhone = new PhoneNumber(t[i++]);
		
		Map<String, PhoneNumber> phoneNumbers = new HashMap<String, PhoneNumber>();
		phoneNumbers.put("home", homePhone);
		phoneNumbers.put("work", workPhone);
		
		return new Trader(traderId, firstName, lastName, gender, homeAddress, workAddress, phoneNumbers, dateOfBirth);
	}

}
