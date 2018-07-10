/**
 * 
 */
package fr.homedev.main;



import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.sellsy.apientities.Pagination;
import com.sellsy.apientities.SearchFilter;
import com.sellsy.apientities.SellsyResponseInfo;
import com.sellsy.coreConnector.SellsyApiException;
import com.sellsy.coreConnector.SellsyApiRequest;
import com.sellsy.coreConnector.SellsyApiResponse;
import com.sellsy.coreConnector.SellsySpringRestExecutor;
import com.sellsy.objectentities.SellsyClient;




/**
 * @author eric
 *
 */
public class App {
	
	
	
    private  static final Logger logger = LoggerFactory.getLogger(App.class);
  
    // Keys Make sure you change them to have valid keys
    private  String consumerToken = "ab8938dc426dedd781ba93dd7cf27e9e09367265";
    private  String consumerSecret = "2e7216237c7237a50f16ca9c249f32d00717d7b2";
    private  String userToken = "af6a9658b021816d7c5350c180a336d48e949c09";
    private  String userSecret = "27fffccee7806a73a35e0435e1b5455308927ea3";
  
    private  SellsySpringRestExecutor underTest = new SellsySpringRestExecutor(consumerToken, consumerSecret,
            userToken, userSecret);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App app = new App();
		
		try {
			app.getClient();
		} catch (SellsyApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


    public void getInfos() throws SellsyApiException {

        SellsyApiRequest request = new SellsyApiRequest("Infos.getInfos", new HashMap<String, Object>());
        SellsyApiResponse result = underTest.process(request);
        logger.info("Retour : {}", result.toString());
       
    }
    
    public void getClient() throws SellsyApiException {
    	HashMap<String, Object> params = new HashMap<String, Object>();
    	
    	params.put("search", new SearchFilter("corporation"));
    	params.put("pagination", new Pagination(1, 5));
	
    	SellsyApiRequest request = new SellsyApiRequest("Client.getList", params);
    	SellsyApiResponse result = underTest.process(request);
    	System.out.println("Retour : {}" + result.toString());
    	
    	SellsyResponseInfo infos = result.getInfos();
        System.out.println("Infos : {} " + infos.toString());
        
        
        List<SellsyApiResponse> responseList = result.extractResponseList();
        
        System.out.println("First element of response : {}" + responseList.get(0).toString());
        
        for(SellsyApiResponse response : responseList) {
        SellsyClient client = new SellsyClient(response);
        String name = client.getAttributeValue("name");
        String mainContactName = client.getAttributeValue("mainContactName");
        System.out.println("Client : {}  " + name + " => Contact : " + mainContactName);
        }
        
       
    }
}




