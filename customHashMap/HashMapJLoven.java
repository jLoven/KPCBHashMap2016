//  25 September 2016
//  Jackie Loven
//  jackieloven@gmail.com

package customHashMap;

public class HashMapJLoven {
	
	private KeyValuePair[] hashArray;
	
	//  Constructor. hashMapSize must be at least 1.
	public HashMapJLoven(int hashMapSize) {
		KeyValuePair[] hash = new KeyValuePair[hashMapSize];
		hashArray = hash;
	}
	
	//  Places given key value pair in hashmap.
	//  If value already exists for a key in hashmap, the new value provided will replace it.
	//  If hashes for different keys collide, the keys will be stored as a linked list inside the same index.
	public boolean set(String aKey, Object aValue) {
		KeyValuePair keyAndValue = new KeyValuePair();
		keyAndValue.setKeyAndValue(aKey, aValue);
		
		int hashCode = aKey.hashCode();
		int simpleHashCodeForPlacement = hashCode % this.hashArray.length;
		if (this.hashArray[simpleHashCodeForPlacement] == null) {
			this.hashArray[simpleHashCodeForPlacement] = keyAndValue;
		} else {
			//  continue here
			KeyValuePair existingKeyAndValue = this.hashArray[simpleHashCodeForPlacement];
		}
		return false;
	}
	
	public static void main(String[] args) {
		HashMapJLoven hashed = new HashMapJLoven(20);
		
		KeyValuePair keyValue = new KeyValuePair();
		Object rando = new Object();
		keyValue.setKeyAndValue("hola", rando);
		hashed.set(keyValue.getKey(), keyValue.getValue());
	}
	
}

class KeyValuePair {
	
	private String key;
	private Object value;
	
	public void setKeyAndValue(String aKey, Object aValue) {
		this.key = aKey;
		this.value = aValue;
	}
	
	public void setKey(String aKey) {
		this.key = aKey;
	}
	
	public void setValue(Object aValue) {
		this.value = aValue;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public Object getValue() {
		return this.value;
}
	
}