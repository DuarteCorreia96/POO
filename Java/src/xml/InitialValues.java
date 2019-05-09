package xml;

import java.util.ArrayList;

/**
 * Class used to save the values taken from the XML file. Use the getters to get each value you want.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Xml
 */
public class InitialValues {
	private static float finalinst;
	private static int antcolsize;
	private static float plevel;
	private static int nbnodes;
	private static int nestnode;
	private static ArrayList<int[]> graphInfo = new ArrayList<int[]>();
	private static int actualNode;
	private static float alpha;
	private static float beta;
	private static float delta;
	private static float eta;
	private static float rho;
	
	/**
	 * 
	 * @return final instant
	 */
	public static float getFinalinst() {
		return finalinst;
	}

	/**
	 * 
	 * @param finalinst final instant to set
	 */
	static void setFinalinst(float finalinst) {
		InitialValues.finalinst = finalinst;
	}

	/**
	 * 
	 * @return ant colony size
	 */
	public static int getAntcolsize() {
		return antcolsize;
	}

	/**
	 * 
	 * @param antcolsize ant colony size to set
	 */
	static void setAntcolsize(int antcolsize) {
		InitialValues.antcolsize = antcolsize;
	}

	/**
	 * 
	 * @return plevel
	 */
	public static float getPlevel() {
		return plevel;
	}

	/**
	 * 
	 * @param plevel plevel to set
	 */
	static void setPlevel(float plevel) {
		InitialValues.plevel = plevel;
	}

	/**
	 * 
	 * @return nbnodes
	 */
	public static int getNbnodes() {
		return nbnodes;
	}

	/**
	 * 
	 * @param nbnodes nbnodes to set
	 */
	static void setNbnodes(int nbnodes) {
		InitialValues.nbnodes = nbnodes;
	}

	/**
	 * 
	 * @return nestnode
	 */
	public static int getNestnode() {
		return nestnode;
	}

	/**
	 * 
	 * @param nestnode nestnode to set
	 */
	static void setNestnode(int nestnode) {
		InitialValues.nestnode = nestnode;
	}

	/**
	 * 
	 * @return alpha
	 */
	public static float getAlpha() {
		return alpha;
	}
	
	/**
	 * 
	 * @return beta
	 */
	public static float getBeta() {
		return beta;
	}

	/**
	 * 
	 * @return delta
	 */
	public static float getDelta() {
		return delta;
	}

	/**
	 * 
	 * @return eta
	 */
	public static float getEta() {
		return eta;
	}

	/**
	 * 
	 * @return rho
	 */
	public static float getRho() {
		return rho;
	}

	/**
	 * 
	 * @param alpha alpha to set
	 */
	static void setAlpha(float alpha) {
		InitialValues.alpha = alpha;
	}

	/**
	 * 
	 * @param beta beta to set
	 */
	static void setBeta(float beta) {
		InitialValues.beta = beta;
	}

	/**
	 * 
	 * @param delta detla to set
	 */
	static void setDelta(float delta) {
		InitialValues.delta = delta;
	}

	/**
	 * 
	 * @param eta eta to set
	 */
	static void setEta(float eta) {
		InitialValues.eta = eta;
	}

	/**
	 * 
	 * @param rho rho to set 
	 */
	static void setRho(float rho) {
		InitialValues.rho = rho;
	}

	/**
	 * 
	 * @return graphInfo
	 */
	public static ArrayList<int[]> getGraphInfo() {
		return graphInfo;
	}

	/**
	 * Defines actual node to add relations and its weight
	 * @param nodeidx node number
	 */
	static void addNode(int nodeidx) {
		actualNode = nodeidx;
	}

	/**
	 * Adds relation between actualNode (@see addNode) and targetnode
	 * @param targetnode
	 */
	static void addRelation(int targetnode) {
		int[] a = new int[3];
		a[0] = actualNode;
		a[1] = targetnode;
		graphInfo.add(a);
	}

	/**
	 * Adds weight to graphInfo
	 * @param weight
	 */
	static void addWeight(int weight) {
		int[] a = graphInfo.get(graphInfo.size()-1);
		a[2] = weight;
		graphInfo.set(graphInfo.size()-1, a);
	}

	/**
	 * Resets all the variables with zero value
	 */
	static void reset() {
		graphInfo.clear();
		finalinst = 0;
		antcolsize = 0;
		plevel = 0;
		nbnodes = 0;
		nestnode = 0;
		actualNode = 0;
		alpha = 0;
		beta = 0;
		delta = 0;
		eta = 0;
		rho = 0;
		
	}
}
