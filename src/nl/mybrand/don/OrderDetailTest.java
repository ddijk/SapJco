package nl.mybrand.don;

import com.sap.mw.jco.JCO;

import nl.common.SapClient;

public class OrderDetailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 SapClient sapClient = null; 
		    try {
		    	
		    	sapClient = new SapClient();

		    	 // Open the connection
		        sapClient.connect();

		        // Get the attributes of the connection and print them

		        JCO.Attributes attributes = sapClient.getAttributes();
//		        System.out.println("Connection attributes:\n" + "----------------------\n" + attributes);
		        boolean is_backend_unicode = attributes.getPartnerCodepage().equals("4102") ||
		                                     attributes.getPartnerCodepage().equals("4103");

		        // Create metadata definition of the input parameter list
		        JCO.MetaData input_md = new JCO.MetaData("INPUT");
		        input_md.addInfo("IM_KUNNR", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
		        input_md.addInfo("IM_VKORG", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);

		        // Create the input parameter list from the metadata object
		        JCO.ParameterList input = JCO.createParameterList(input_md);

		        // Set the first (and only) input parameter
		        input.setValue("11066", "IM_KUNNR");
		        input.setValue("v440", "IM_VKORG");

		        // Create metadata definition of the output parameter list
		        JCO.MetaData output_md = new JCO.MetaData("OUTPUT");

		        // Specify the parameters types of the function will be returned
		        output_md.addInfo("EX_CUSTOMER", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);
		        output_md.addInfo("EX_PLANT", JCO.TYPE_CHAR, 255, 255 * (is_backend_unicode? 2 : 1 ), -1, 0, null, null, 0, null, null);

		        // Create the output parameter list from the metadata object
		        JCO.ParameterList output = JCO.createParameterList(output_md);

		        // Call the function
		        sapClient.execute("Z_ORDER_GETDETAIL", input, output);

		        // Print the result
		        System.out.println("The function 'Z_ORDER_GETDETAIL' returned the following parameters (aantal is "+output.getFieldCount() +":\n" +
		                           "-----------------------------------------------------------------");
		        for (int i = 0; i < output.getFieldCount(); i++) {
		            System.out.println("Name: " +  output.getName(i) + " Value: " + output.getString(i));
		        }//for

		        // All done
		        System.out.println("\n\nCongratulations! It worked.");
		      }
		      catch (Exception ex) {
		        System.out.println("Caught an exception: \n" + ex);
		      }
		      finally {
		          // do not forget to close the client connection
		          if (sapClient != null) sapClient.disconnect();
		      }
		    }

}
