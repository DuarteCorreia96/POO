package xml;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Implementation of the methods called by the SAX parser to store the values in the {@code InitialValues}.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see InitialValues
 */
class UserHandler extends DefaultHandler {

	boolean bWeight = false;
	boolean bMove = false;
	
	@Override
	public void startElement(String uri, 
		String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equalsIgnoreCase("simulation")) {
			float finalinst = Float.parseFloat(attributes.getValue("finalinst"));
			InitialValues.setFinalinst(finalinst);
			int antcolsize = Integer.parseInt(attributes.getValue("antcolsize"));
			InitialValues.setAntcolsize(antcolsize);
			float plevel = Float.parseFloat(attributes.getValue("plevel"));
			InitialValues.setPlevel(plevel);
			
		} else if (qName.equalsIgnoreCase("graph")) {
			int nbnodes = Integer.parseInt(attributes.getValue("nbnodes"));
			InitialValues.setNbnodes(nbnodes);
			int nestnode = Integer.parseInt(attributes.getValue("nestnode"));
			InitialValues.setNestnode(nestnode);
			
		} else if (qName.equalsIgnoreCase("node")) {
			int nodeidx = Integer.parseInt(attributes.getValue("nodeidx"));
			InitialValues.addNode(nodeidx);
			
		} else if (qName.equalsIgnoreCase("weight")) {
			int targetnode = Integer.parseInt(attributes.getValue("targetnode"));
			bWeight = true;
            InitialValues.addRelation(targetnode);
            
		} else if (qName.equalsIgnoreCase("move")) {

			float alpha = Float.parseFloat(attributes.getValue("alpha"));
			InitialValues.setAlpha(alpha);
			float beta = Float.parseFloat(attributes.getValue("beta"));
			InitialValues.setBeta(beta);
			float delta = Float.parseFloat(attributes.getValue("delta"));
            InitialValues.setDelta(delta);
            
		} else if (qName.equalsIgnoreCase("evaporation")) {
            
			float eta = Float.parseFloat(attributes.getValue("eta"));
			InitialValues.setEta(eta);
			float rho = Float.parseFloat(attributes.getValue("rho"));
			InitialValues.setRho(rho);
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (bWeight) {
			int weight = Integer.parseInt(new String(ch, start, length));
			InitialValues.addWeight(weight);
			bWeight = false;
		}
	}
}
