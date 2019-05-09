package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * The {@code Xml} implements the methods to validate and parse the XML file provided.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 */
public class Xml {

	/**
	 * Validates the xml file according to the dtd
	 * @param filename name of the file to be validated
	 * @return true if file is valid and false if not
	 */
	public static boolean validateFile(String filename) {
		try {
			validate(filename);
			return true;}
		catch (ParserConfigurationException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
		catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
		catch (SAXException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
		catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
	}
	
	/**
	 * Validates the file according to the dtd. If it not valid  throws exception.
	 * @param xmlFile name of the xml file
	 * @throws ParserConfigurationException 
	 * @throws FileNotFoundException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static void validate(String xmlFile) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
        
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		documentBuilder.setErrorHandler(new org.xml.sax.ErrorHandler() {

			@Override
			public void warning(SAXParseException exception) throws SAXException {throw exception;}
			@Override
			public void error(SAXParseException exception) throws SAXException {throw exception;}
			@Override
			public void fatalError(SAXParseException exception) throws SAXException {throw exception;}
        });
        
		documentBuilder.parse(new FileInputStream(xmlFile));
	}
	
	/**
	 * Parses the XML and saves it to InitialValues
	 * @param filename name of the file to be parsed
	 * @return true if successfull, false if not successfull
	 */
	public static boolean readValues(String filename) {
		try {
			File inputFile = new File(filename);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			UserHandler userhandler = new UserHandler();
			InitialValues.reset();
			saxParser.parse(inputFile, userhandler); 
			return true;	
		} 
		catch (ParserConfigurationException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
		catch (SAXException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
		catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
	}	
	
}
