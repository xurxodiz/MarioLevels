package jorgedizpico.auto;

import java.util.HashMap;

public class Repository {
	
	private HashMap<String,Dummy> dummies
		= new HashMap<String,Dummy>();

	public Gene getGene(String s) {
		return Enum.valueOf(Gene.class, s);
	}

	public Dummy getDummy(String s) {
		Dummy dummy = dummies.get(s);
		if (null != dummy) return dummy;
		dummy = new Dummy();
		dummies.put(s, dummy);
		return dummy;
			
	}
}
