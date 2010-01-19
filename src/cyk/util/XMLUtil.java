package cyk.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Sammlung nützlicher XML-Operationen.
 * 
 * @author Stephan
 */
public class XMLUtil {
	private static XMLOutputter outputter = new XMLOutputter(Format
			.getPrettyFormat());
	private static SAXBuilder builder = new SAXBuilder(false);

	/**
	 * Gibt das gegebene Element in eine Datei aus.
	 * 
	 * @param e Auszugebendes Element
	 * @param f Datei, in die ausgegeben werden soll
	 * @throws IOException Ein-/Ausgabefehler
	 * @throws FileNotFoundException Datei wurde nicht gefunden
	 */
	public static void output(Element e, File f) throws IOException,
			FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(f);
		outputter.output(e, fos);
		try{
			fos.close();
		} catch (Exception ex) {
			// kein problem
		}
	}

	/**
	 * Gibt ein XML-Element auf die Console aus.
	 * @param e Auszugebendes Element
	 */
	public static void print(Element e) {
		try {
			outputter.output(e, System.out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Liest ein XML-Element aus einer Datei ein.
	 * @param f Datei, aus der eingelesen werden soll
	 * @return Gelesenes XMl-Element
	 * @throws JDOMException Datei enthält kein gültiges XML
	 * @throws IOException Ein-/Ausgabefehler
	 */
	public static Element read(File f) throws JDOMException, IOException {
		return builder.build(f).getRootElement();
	}

	/**
	 * Wandelt ein XML-Element in einen String um.
	 * @param e XML-Element
	 * @return String
	 */
	public static String toString(Element e) {
		return outputter.outputString(e);
	}
}
