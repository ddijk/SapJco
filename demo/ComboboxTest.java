import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JOptionPane;


public class ComboboxTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

//		Create the combo box, select item at index 4.
//		Indices start at 0, so 4 specifies the pig.
	//	JComboBox petList = new JComboBox(petStrings);
		//JOptionPane.showConfirmDialog(petList);
		
		File dir = new File(".");
		String[] propFiles = dir.list(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if ( name.endsWith(".properties")) {
					return true;
				}
				return false;
			}});
		Object[] possibleValues = { "First", "Second", "Third" };
		Object selectedValue = JOptionPane.showInputDialog(null,

		            "Choose one", "Input",

		            JOptionPane.INFORMATION_MESSAGE, null,

		            propFiles, possibleValues[0]);
		
		System.out.println("Selected filename is " + selectedValue);

	}

}
