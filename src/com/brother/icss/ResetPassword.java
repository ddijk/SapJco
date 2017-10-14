package com.brother.icss;

import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;

import nl.common.SapClient;

// DF417A516E470E68E1000000AC1B10D3
public class ResetPassword {
	public static void main(String[] args) {

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

			String functionName = "CRM_ICSS_USER_PASS_RESET_JAVA";
			Function function = sapClient.createFunction(functionName);

			importParamList = function.getImportParameterList();

			String emailAddress = "DICK.DIJK@PERFECTFORPEOPLE.NL";
			String loginName = "DF417A516E470E68E1000000AC1B10D3";

			importParamList.setValue(emailAddress, "IV_RECEIVER_ADDR");
			importParamList.setValue(loginName, "IV_ALIAS");

			sapClient.executeFunction(function);

			exportParamList = function.getTableParameterList();
			returnCodeList = exportParamList.getTable("ET_RETURN");

			String ret = (String) returnCodeList.getValue("TYPE");
			System.out.println("Return waarde: " + ret);

		} catch (Exception bex) {
			System.err.println("Z_ForgotPasswordBEImpl - Error in Forgotten Password Backend Call" + bex);
			// the following line needs to be reinstated once the stub is out

		}

		System.out.println("End of resetPassword call");

	}

}
