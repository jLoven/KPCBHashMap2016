//  29 September 2016
//  Jackie Loven
//  jackieloven@gmail.com

package customHashMap;

public class HashMapJLoven {

	private KeyValuePair[] hashArray;

	//  Constructor. hashMapSize must be at least 1.
	public HashMapJLoven(int hashMapSize) {
		assert hashMapSize > 0;
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
		int hashCodeForPlacement = hashCode % this.hashArray.length;
		if (this.hashArray[hashCodeForPlacement] == null) {
			//  No collisions in this index of the hashmap yet.
			this.hashArray[hashCodeForPlacement] = keyAndValue;
		} else {
			//  There is a collision: make a singly linked list.
			KeyValuePair existingKeyAndValue = this.hashArray[hashCodeForPlacement];
			//  Need to check whether any key in its linked list is equal to the one being added:
				while (existingKeyAndValue.getNextNode() != null) {
					if (existingKeyAndValue.getKey() == keyAndValue.getKey()) {
						//  Already have the exact same key with a value, so replace it:
						existingKeyAndValue.setValue(keyAndValue.getValue());
						break;
					} else {
						existingKeyAndValue = existingKeyAndValue.getNextNode();
					}
				}
				//  Now if you're at the last element of the linked list:
				if (existingKeyAndValue.getNextNode() == null) {
					if (existingKeyAndValue.getKey() == keyAndValue.getKey()) {
						existingKeyAndValue.setValue(keyAndValue.getValue());
					} else {
						existingKeyAndValue.setNextNode(keyAndValue);
					}
				}
		}
		//  If get returns the value added, then the add was successful.
		if (this.get(aKey) == aValue) {
			return true;
		}
		return false;
	}
	
	public Object get(String aKey) {
		int hashCode = aKey.hashCode();
		int hashCodeForPlacement = hashCode % this.hashArray.length;
		if (this.hashArray[hashCodeForPlacement] == null) {
			return null;
		} else {
			KeyValuePair existingKeyAndValue = this.hashArray[hashCodeForPlacement];
			while (existingKeyAndValue != null) {
				if (existingKeyAndValue.getKey() == aKey) {
					return existingKeyAndValue.getValue();
				} else {
					existingKeyAndValue = existingKeyAndValue.getNextNode();
				}
			}
		}
		return null;
	}
}

class KeyValuePair {

	private String key;
	private Object value;
	private KeyValuePair nextNode = null;

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
	
	public void setNextNode(KeyValuePair aNextNode) {
		this.nextNode = aNextNode;
	}

	public String getKey() {
		return this.key;
	}

	public Object getValue() {
		return this.value;
	}
	
	public KeyValuePair getNextNode() {
		return this.nextNode;
	}
}