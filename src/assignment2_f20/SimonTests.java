package assignment2_f20;

public class SimonTests {
	public static void main (String [] args) {
		TreeMap map = new TreeMap_imp();
		Value av = new Value_imp(1, 100, 20);
		Value bv = new Value_imp(2, 100, 20);
		Value cv = new Value_imp(3, 100, 20);
		map.put("b", bv);
		map.put("a", av);
		//System.out.println(map.getRoot().getKey());
		//System.out.println(map.getRoot().getKey());
		map.put("c", cv);
		String [] keys = map.getKeys();
		System.out.println(map.size());
		for(int i = 0; i < 3; i++) {
			System.out.println(keys[i]);
		}
		System.out.println(map.getRoot().getKey());
		//System.out.println(map.getRoot().getLeft().getKey());
		//System.out.println(map.getRoot().getRight().getKey()); 
		//System.out.println(map.getRoot().getKey());
		//System.out.println(map.getRoot().getKey());
		
		//System.out.println(map.hasKey("b"));
		//System.out.println(map.maxKey());
		//System.out.println(map.minKey());
		//map.remove("b");
		//System.out.println(map.getRoot().getKey());
	}
}
