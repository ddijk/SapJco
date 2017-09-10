package nl.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Attributes;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;

public class SapClient {

	private JCO.Client client;
	JCO.Repository mRepository;

	public SapClient() {
		// String sapProps = JOptionPane.showInputDialog("Geef naam van
		// properties file");
		File dir = new File(".");
		String[] propFiles = dir.list(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (name.endsWith(".properties")) {
					return true;
				}
				return false;
			}
		});

		boolean doShowBackendSelector = Boolean.getBoolean("showBackendSelector");
		;

		Object selectedValue;
		if (doShowBackendSelector) {
			System.out.println("Prompting for backend");
			selectedValue = getBackendConnection(propFiles);
		} else {
			//selectedValue = "brother_icss_dev.properties";
			selectedValue = "brother_icss_qa.properties";
		//	 selectedValue = "brother_icss_prod.properties";
			System.out.println("Using fixed backend: " + selectedValue);

		}

		System.out.println("Using SAP properties from file: " + selectedValue);

		String sapProps = (String) selectedValue;
		try {

			// Print the version of the underlying JCO library
			System.out.println(
					"\n\nVersion of the JCO-library:\n" + "---------------------------\n" + JCO.getMiddlewareVersion());
			
			Properties props = new Properties();
			props.load(new FileInputStream(sapProps));

			client = JCO.createClient(props);
			setRepo();
		} catch (Exception ex) {
			System.out.println("Caught an exception: \n" + ex);
		}
	}

	private Object getBackendConnection(String[] propFiles) {
		Object selectedValue = JOptionPane.showInputDialog(null,

				"Kies een SAP systeem", "Input",

				JOptionPane.INFORMATION_MESSAGE, null,

				propFiles, propFiles[0]);

		if (selectedValue == null) {
			System.out.println("Geen SAP systeem gekozen, exiting");
			System.exit(0);
		}
		return selectedValue;
	}

	/*
	 * public JCO.Client getClient() { return client; }
	 */

	public void connect() {
		client.connect();
	}

	public void execute(String functionName, ParameterList paramList1, ParameterList paramList2) {
		client.execute(functionName, paramList1, paramList2);
	}

	public void executeFunction(Function function) {
		client.execute(function);
	}

	public Attributes getAttributes() {
		return client.getAttributes();
	}

	public void disconnect() {
		client.disconnect();

	}

	void setRepo() {

		mRepository = new JCO.Repository("ARAsoft", client);
	}

	public JCO.Function createFunction(String name) throws Exception {
		try {
			System.out.println("Getting template for: " + name);
			IFunctionTemplate ft = mRepository.getFunctionTemplate(name.toUpperCase());
			
			if (ft == null)
				return null;
			return ft.getFunction();
		} catch (Exception ex) {
			System.err.println("Ooops." + ex);
			throw ex;
		}
	}

}
