package com.brother.webshop;


import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;


import nl.common.SapClient;

public class GetEmailSender {

	public static void main(String[] args) {

		GetEmailSender instance = new GetEmailSender();
		String country = "nl";
		String language = "nl";
		instance.getEmailSender(country, language);
	}

	public String getEmailSender(String country, String language) {
		String emailSender = "";
		String error = "";

		JCO.ParameterList importParamList = null;
		JCO.ParameterList exportParamList = null;

		SapClient sapClient = null;
		try {

			sapClient = new SapClient();

			// Open the connection
			sapClient.connect();

			com.sap.mw.jco.JCO.ParameterList impList = null;
			com.sap.mw.jco.JCO.Table returnCodeList = null;
			com.sap.mw.jco.JCO.ParameterList exportTables = null;
			String ret = null;

			String functionName = "Y_E_FORGOT_PASSWORD_GET_SENDER";
			Function function = sapClient.createFunction(functionName);

			importParamList = function.getImportParameterList();

			if ( country != null) {
				importParamList.setValue(country.toUpperCase(), "IV_COUNTRY");
			}
			
			
			if ( language != null) {
				importParamList.setValue(language.toUpperCase(), "IV_LANGUAGE");
			}
		

			sapClient.executeFunction(function);

			exportParamList = function.getExportParameterList();
			emailSender = exportParamList.getString("EV_SENDER");
			error = exportParamList.getString("EV_ERROR");

		} catch (Exception bex) {
			System.err.println("Z_ForgotPasswordBEImpl - Error in Forgotten Password Backend Call" + bex);
			// the following line needs to be reinstated once the stub is out
			
		}

		System.out.println("End of Z_ForgotPasswordBEImpl.getEmailSender():" + emailSender);

		return emailSender;

	}
}
