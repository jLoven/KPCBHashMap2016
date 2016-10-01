//  30 September 2016
//  Jackie Loven
//  jackieloven@gmail.com

package customHashMap;

public class HashMapJLoven {

	private KeyValuePair[] hashArray;

	//  Constructor. hashMapSize must be at least 1.
	//  Returns an instance of the class with pre-allocated space for the given number of objects.
	public HashMapJLoven(int hashMapSize) throws IllegalArgumentException{
		if (hashMapSize < 1) {
			throw new IllegalArgumentException("Hashmap size must be at least 1.");
		}
		KeyValuePair[] hash = new KeyValuePair[hashMapSize];
		hashArray = hash;
	}

	//  Stores the given key/value pair in the hash map. 
	//  Returns a boolean value indicating success / failure of the operation.
	public boolean set(String aKey, Object aValue) {
		KeyValuePair keyAndValue = new KeyValuePair();
		keyAndValue.setKeyAndValue(aKey, aValue);

		int hashCode = makeHashCode(aKey);
		if (this.hashArray[hashCode] == null) {
			//  No collisions in this index of the hashmap yet.
			this.hashArray[hashCode] = keyAndValue;
		} else {
			//  There is a collision: make a singly linked list.
			KeyValuePair existingKeyAndValue = this.hashArray[hashCode];
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
			//  Now at the last element of the linked list:
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

	//  Returns the value associated with the given key, or null if no value is set.
	public Object get(String aKey) {
		int hashCode = makeHashCode(aKey);
		if (this.hashArray[hashCode] == null) {
			return null;
		}
		KeyValuePair existingKeyAndValue = this.hashArray[hashCode];
		while (existingKeyAndValue != null) {
			if (existingKeyAndValue.getKey() == aKey) {
				return existingKeyAndValue.getValue();
			} else {
				existingKeyAndValue = existingKeyAndValue.getNextNode();
			}
		}
		return null;
	}

	//  Deletes the value associated with the given key.
	//  Returns the value on success or null if the key has no value.
	public Object delete(String aKey) {
		int hashCode = makeHashCode(aKey);
		if (this.hashArray[hashCode] == null) {
			//  Key doesn't exist in hashmap:
			return null;
		}
		Object savedValue = null;
		//  The key may be in the hashmap:
		KeyValuePair currentKeyAndValue = this.hashArray[hashCode];
		KeyValuePair nextKeyAndValue = currentKeyAndValue.getNextNode();
		if (currentKeyAndValue.getKey() == aKey && nextKeyAndValue == null) {
			//  The only node hashed to this index needs to be deleted:
			savedValue = currentKeyAndValue.getValue();
			this.hashArray[hashCode] = null;
			return savedValue;
		} else if (currentKeyAndValue.getKey() == aKey && nextKeyAndValue != null) {
			//  First node is the node to be deleted:
			savedValue = currentKeyAndValue.getValue();
			this.hashArray[hashCode] = nextKeyAndValue;
			return savedValue;
		} else if (currentKeyAndValue.getKey() != aKey) {
			//  The node to be deleted may be somewhere inside the linked list:
			boolean found = false;
			while (!found && nextKeyAndValue != null) {
				if (nextKeyAndValue.getKey() == aKey) {
					//  Delete this node:
					savedValue = nextKeyAndValue.getValue();
					currentKeyAndValue.setNextNode(nextKeyAndValue.getNextNode());
					found = true;
				} else {
					currentKeyAndValue = nextKeyAndValue;
					nextKeyAndValue = currentKeyAndValue.getNextNode();
				}
			}
		}
		return savedValue;
	}
	
	//  Returns a float value representing the load factor 
	//  (`(items in hash map)/(size of hash map)`) of the data structure.
	//  Since the size of the data structure is fixed, this should never be greater than 1.
	public float load() {
		int totalItemsInMap = 0;
		int lengthOfMap = this.hashArray.length;
		for (int i = 0; i < lengthOfMap; i++) {
			KeyValuePair currentNode = this.hashArray[i];
			if (currentNode != null) {
				totalItemsInMap++;
				//  The following is for including the collided hashes:
				/*while (currentNode.getNextNode() != null) {
					totalItemsInMap++;
					currentNode = currentNode.getNextNode();
				}*/
			}
		}
		float loadFactor = ((float) totalItemsInMap) / ((float) lengthOfMap);
		return loadFactor;
	}

	private int makeHashCode(String aString) {
		int hashCode = aString.hashCode();
		int hashCodeForPlacement = hashCode % this.hashArray.length;
		return hashCodeForPlacement;
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

	public String getKey() {
		return this.key;
	}

	public void setValue(Object aValue) {
		this.value = aValue;
	}

	public Object getValue() {
		return this.value;
	}

	public void setNextNode(KeyValuePair aNextNode) {
		this.nextNode = aNextNode;
	}

	public KeyValuePair getNextNode() {
		return this.nextNode;
	}
}