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

public class XMLUtil {
	private static XMLOutputter outputter = new XMLOutputter(Format
			.getPrettyFormat());
	private static SAXBuilder builder = new SAXBuilder(false);

	public static void output(Element e, File f) throws IOException,
			FileNotFoundException {
		outputter.output(e, new FileOutputStream(f));
	}

	public static void print(Element e) {
		try {
			outputter.output(e, System.out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Element read(File f) throws JDOMException, IOException {
		return builder.build(f).getRootElement();
	}

	public static String toString(Element e) {
		return outputter.outputString(e);
	}
}
