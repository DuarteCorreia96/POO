package xml;
import java.util.ArrayList;

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
	
	
	public static float getFinalinst() {
		return finalinst;
	}

	static void setFinalinst(float finalinst) {
		InitialValues.finalinst = finalinst;
	}

	public static int getAntcolsize() {
		return antcolsize;
	}

	static void setAntcolsize(int antcolsize) {
		InitialValues.antcolsize = antcolsize;
	}

	public static float getPlevel() {
		return plevel;
	}

	static void setPlevel(float plevel) {
		InitialValues.plevel = plevel;
	}

	public static int getNbnodes() {
		return nbnodes;
	}

	static void setNbnodes(int nbnodes) {
		InitialValues.nbnodes = nbnodes;
	}

	public static int getNestnode() {
		return nestnode;
	}

	static void setNestnode(int nestnode) {
		InitialValues.nestnode = nestnode;
	}

	public static float getAlpha() {
		return alpha;
	}

	public static float getBeta() {
		return beta;
	}

	public static float getDelta() {
		return delta;
	}

	public static float getEta() {
		return eta;
	}

	public static float getRho() {
		return rho;
	}

	static void setAlpha(float alpha) {
		InitialValues.alpha = alpha;
	}

	static void setBeta(float beta) {
		InitialValues.beta = beta;
	}

	static void setDelta(float delta) {
		InitialValues.delta = delta;
	}

	static void setEta(float eta) {
		InitialValues.eta = eta;
	}

	static void setRho(float rho) {
		InitialValues.rho = rho;
	}

	public static ArrayList<int[]> getGraphInfo() {
		return graphInfo;
	}

	static void addNode(int nodeidx) {
		actualNode = nodeidx;
	}

	static void addRelation(int targetnode) {
		int[] a = new int[3];
		a[0] = actualNode;
		a[1] = targetnode;
		graphInfo.add(a);
	}

	static void addWeight(int weight) {
		int[] a = graphInfo.get(graphInfo.size()-1);
		a[2] = weight;
		graphInfo.set(graphInfo.size()-1, a);
	}
}
