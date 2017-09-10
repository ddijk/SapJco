package com.brother.webshop;

import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Table;

import nl.common.SapClient;

public class JcoTester {
	public static void main(String[] args) {
		//Y_E_ICSS_ALIAS_FROM_EMAIL_PWRD
		 SapClient sapClient = null; 
		    try {
		    	
		    	sapClient = new SapClient();

		    	 // Open the connection
		        sapClient.connect();

//		        // Get the attributes of the connection and print them
//
//		        JCO.Attributes attributes = sapClient.getAttributes();
////		        System.out.println("Connection attributes:\n" + "----------------------\n" + attributes);
//		        boolean is_backend_unicode = attributes.getPartnerCodepage().equals("4102") ||
//		                                     attributes.getPartnerCodepage().equals("4103");
//
//		        // Create metadata definition of the input parameter list
//	       JCO.MetaData input_md = new JCO.MetaData("INPUT");
//		        input_md.addInfo("IV_EMAIL", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
////		        input_md.addInfo("IM_VKORG", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
//
//		        // Create the input parameter list from the metadata object
//		        JCO.ParameterList input = JCO.createParameterList(input_md);
		        

		        // Set the first (and only) input parameter
		        
		     String email = "DICK.DIJK@PERFECTFORPEOPLE.NL";
		     
		 //       String email = "servicedesk@perfectforpeople.nl";
		      

		        String functionName = "Y_E_ICSS_ALIAS_FROM_EMAIL_PWRD";
		        Function function  =  sapClient.createFunction(functionName);
		        
		      
		        boolean hasError = false;

				// Gets importing, exporting and table variable
				JCO.ParameterList impList = function.getImportParameterList();

				impList.setValue(email.toUpperCase(), "IV_E_MAIL");
		 
				// Executes the function module
				sapClient.executeFunction(function);

				
				// Get the export tables
				ParameterList exportTables = function.getTableParameterList();
				
				// get export params
				JCO.ParameterList expList = function.getExportParameterList();
				

				// Get RFC-Table ET_RETURN
				Table returnCodeList = exportTables.getTable("ET_RETURN");
				String ret = null;
				if (returnCodeList.getNumRows() > 0) {
					do {
						  ret = (String)returnCodeList.getValue("TYPE");
						  if ( ret.equals("E")){
						  	  hasError = true;
						  	  break; }
					} while (returnCodeList.nextRow());
				}
				
				String alias;
				if (!hasError){
					alias = expList.getString("EV_ALIAS");
					ret = alias;
				}
		        // All done
		        System.out.println("\n\nCongratulations! It worked. Alias:"+ret);
		      }
		      catch (Exception ex) {
		        System.out.println("Caught an exception: \n" + ex);
		      }
		      finally {
		          // do not forget to close the client connection
		    	  System.out.println("About to disconnect");
		          if (sapClient != null) sapClient.disconnect();
		          System.out.println(".. disconnected");
		      }
		    }
}
