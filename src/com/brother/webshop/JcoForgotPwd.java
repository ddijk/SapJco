package com.brother.webshop;

import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Table;

import nl.common.SapClient;

public class JcoForgotPwd {

	public static void main(String[] args) {

		//Y_E_ICSS_ALIAS_FROM_EMAIL_PWRD
		 SapClient sapClient = null; 
		    try {
		    	
		    	sapClient = new SapClient();

		    	 // Open the connection
		        sapClient.connect();
		        String e_mail_address = "dickdev@abc.nl";
		  //      String e_mail_address = "DICKDEV@ABC.NL";
			     
			     
		        com.sap.mw.jco.JCO.ParameterList impList = null;
		        com.sap.mw.jco.JCO.Table returnCodeList = null;
		        com.sap.mw.jco.JCO.ParameterList exportTables = null;
		        String ret = null;
			      

		        String functionName = "CRM_ICSS_B2B_IDENTITY_CHECK";
		        Function function  =  sapClient.createFunction(functionName);
		        
		        String scenario = "icss_b2b";
		        
		        String first_name = null;
		        String companyId = "9100083061";
		        String last_name = null;
		        String postal_code = null;
		        String birth_place = null;
		        String country = "NL";
//		        String po_box = null;
//		        String po_box = null;
//		        String po_box = null;
//		        String po_box = null;
//		        String po_box = null;

		        String login_name = "020FDF56C0EA342FE1000000AC1B10D3";
		            impList = function.getImportParameterList();
	            impList.setValue(login_name, "IV_ALIAS");
	            impList.setValue(e_mail_address, "IV_EMAIL");
//	            impList.setValue(first_name, "IV_FIRSTNAME");
//	            impList.setValue(last_name, "IV_LASTNAME");
//	            impList.setValue(postal_code, "IV_ZIPCODE");
//	            impList.setValue(birth_place, "IV_BIRTHPLACE");
//	            impList.setValue(birth_date, "IV_BIRTHDATE");
//	            impList.setValue(po_box, "IV_PO_BOX");
//	            impList.setValue(street, "IV_STREET");
//	            impList.setValue(house_number, "IV_HOUSE_NUM");
	 //           impList.setValue(country, "IV_COUNTRY");
//	            impList.setValue(city, "IV_CITY");
//	            impList.setValue(region, "IV_REGION");
//	            impList.setValue(phone, "IV_TEL_NUM");
	            if(scenario != null && scenario.equalsIgnoreCase("icss_b2b"))
	                impList.setValue(companyId, "IV_ORGANIZATION");
	            sapClient.executeFunction(function);
	            exportTables = function.getTableParameterList();
	            returnCodeList = exportTables.getTable("ET_RETURN");
	            ret = null;
	            if(returnCodeList.getNumRows() > 0)
	                do
	                    ret = (String)returnCodeList.getValue("TYPE");
	                while(!ret.equals("E") && returnCodeList.nextRow());
		        System.out.println("\n\nCongratulations! It worked. Check returns:"+ret);
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
