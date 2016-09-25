//  25 September 2016
//  Jackie Loven
//  jackieloven@gmail.com

package customHashMap;

public class HashMapJLoven {
	
	private KeyValuePair[] hashArray;
	
	/** Constructor: an instance for a HashMapJLoven 
	 * with size hashMapSize. 
	 * Precondition: n is at least 1. */
	public HashMapJLoven(int hashMapSize) {
		KeyValuePair[] hash = new KeyValuePair[hashMapSize];
		hashArray = hash;
	}
}

class KeyValuePair {
	
	private String name;
	private Object value;
	
	public void setName(String aName) {
		this.name = aName;
	}
	
	public void setValue(Object aValue) {
		this.value = aValue;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Object getValue() {
		return this.value;
	}
	
}