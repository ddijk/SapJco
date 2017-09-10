package com.brother.icss;

import com.sap.mw.jco.JCO;

import nl.common.SapClient;

public class AliasRetriever {

	public static void main(String[] args) {
		//Y_E_ICSS_ALIAS_FROM_EMAIL_PWRD
		 SapClient sapClient = null; 
		    try {
		    	
		    	sapClient = new SapClient();

		    	 // Open the connection
		    	 System.out.println("Trying to connect...");
		        sapClient.connect();
		        System.out.println("Connected.");

		        // Get the attributes of the connection and print them

		        JCO.Attributes attributes = sapClient.getAttributes();
//		        System.out.println("Connection attributes:\n" + "----------------------\n" + attributes);
//		        boolean is_backend_unicode = attributes.getPartnerCodepage().equals("4102") ||
		                                     attributes.getPartnerCodepage().equals("4103");

		        // Create metadata definition of the input parameter list
	        JCO.MetaData input_md = new JCO.MetaData("INPUT");
//		        input_md.addInfo("IM_KUNNR", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
//		        input_md.addInfo("IM_VKORG", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);

	        input_md.addInfo("IV_E_MAIL", JCO.TYPE_CHAR, 255, 255 * 2, -1, 0, null, null, 0, null, null);
		        // Create the input parameter list from the metadata object
		        JCO.ParameterList input = JCO.createParameterList(input_md);

		        // Set the first (and only) input parameter
		        // "dickdev@abc.nl"
		        String emailAddress = "TYMROVA@DAMEDIS.CZ";
		        input.setValue(emailAddress, "IV_E_MAIL");
		     

		        // Create metadata definition of the output parameter list
		        JCO.MetaData output_md = new JCO.MetaData("OUTPUT");
		        output_md.addInfo("EV_ALIAS", JCO.TYPE_CHAR, 255, 255 * 2, -1, 0, null, null, 0, null, null);

		        // Create the output parameter list from the metadata object
		        JCO.ParameterList output = JCO.createParameterList(output_md);

		        String functionName = "Y_E_ICSS_ALIAS_FROM_EMAIL_PWRD";
		        // Call the function
		        System.out.println("Calling "+ functionName+" for "+ emailAddress);
		        sapClient.execute(functionName, input, output);

		        // Print the result
		        System.out.println("The function '"+functionName+"' returned the following parameters (aantal is "+output.getFieldCount() +":\n" +
		                           "-----------------------------------------------------------------");
		        for (int i = 0; i < output.getFieldCount(); i++) {
		            System.out.println("Name: " +  output.getName(i) + " Value: " + output.getString(i));
		        }//for

		        // All done
		        System.out.println("\n\nCongratulations! It worked.");
		      }
		      catch (Exception ex) {
		    	  
		        System.out.println("Caught an exception: \n" + ex);
		        ex.printStackTrace();
		      }
		      finally {
		          // do not forget to close the client connection
		          if (sapClient != null) sapClient.disconnect();
		      }
		    }


	}


