package com.brother.icss;

import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Field;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Record;
import com.sap.mw.jco.JCO.Structure;

import nl.common.SapClient;

public class SmtpConfig2 {
	public static void main(String[] args) {
		// Y_E_ICSS_ALIAS_FROM_EMAIL_PWRD
		SapClient sapClient = null;
		try {

			sapClient = new SapClient();

			// Open the connection
			System.out.println("Trying to connect...");
			sapClient.connect();
			System.out.println("Connected.");
			//
			// // Get the attributes of the connection and print them
			//
			// JCO.Attributes attributes = sapClient.getAttributes();
			//// System.out.println("Connection attributes:\n" +
			// "----------------------\n" + attributes);
			// boolean is_backend_unicode =
			// attributes.getPartnerCodepage().equals("4102") ||
			// attributes.getPartnerCodepage().equals("4103");
			//
			// // Create metadata definition of the input parameter list
			// JCO.MetaData input_md = new JCO.MetaData("INPUT");
			//// input_md.addInfo("IM_KUNNR", JCO.TYPE_CHAR, 255, 255 *
			// (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
			//// input_md.addInfo("IM_VKORG", JCO.TYPE_CHAR, 255, 255 *
			// (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
			//
			// // input_md.addInfo("IV_E_MAIL", JCO.TYPE_CHAR, 255, 255 * 2, -1,
			// 0, null, null, 0, null, null);
			// // Create the input parameter list from the metadata object
			// JCO.ParameterList input = JCO.createParameterList(input_md);
			//
			//
			// // Create metadata definition of the output parameter list
			// JCO.MetaData output_md = new JCO.MetaData("OUTPUT");
			// // output_md.addInfo("ES_MAILING", JCO.TYPE_STRUCTURE, 144, 0, 0,
			// JCO.EXPORT_PARAMETER, null);
			// output_md.addInfo("ES_MAILING", JCO.TYPE_CHAR, 255, 255 *
			// (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
			//
			// // Create the output parameter list from the metadata object
			// JCO.ParameterList output = JCO.createParameterList(output_md);

			String functionName = "CRM_ICSS_MAIL_SEL_CB";
			// Call the function

			JCO.Function fie = sapClient.createFunction(functionName);

			// Print the result
			// System.out.println("The function '"+functionName+"' returned the
			// following parameters (aantal is "+output.getFieldCount() +":\n" +
			// "-----------------------------------------------------------------");
			// for (int i = 0; i < output.getFieldCount(); i++) {
			// System.out.println("Name: " + output.getName(i) + " Value: " +
			// output.getString(i));
			// }//for
			System.out.println("Calling " + functionName);
			sapClient.executeFunction(fie);
			System.out.println("Size of paramter list:" + fie.getExportParameterList().getTabLength());
			Structure struct = fie.getExportParameterList().getStructure(0);
			ParameterList pl = fie.getTableParameterList();
			System.out.println("pl non-null?:" + (pl != null));
			Structure esMailing = fie.getExportParameterList().getStructure("ES_MAILING");
			// All done
			System.out.println("es_mailing:"+esMailing.getNumFields());
			System.out.println("struct non null?" + (struct != null));

			System.out.println("ES_MAILING:" + struct.getFieldCount());

			for (int i = 0; i < 5; i++) {
				Field field = struct.getField(i);

				System.out.println("field name:" + field.getName()+ ", value:"+ field.getValue());
			}

			// smtp server:
			Field smtpServer = struct.getField(4);
			System.out.println("smtpServer:" + smtpServer.getString());

		} catch (Exception ex) {

			System.out.println("Caught an exception: \n" + ex);
			ex.printStackTrace();
		} finally {
			// do not forget to close the client connection
			if (sapClient != null)
				sapClient.disconnect();
		}
	}
}
