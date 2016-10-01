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

	public Object delete(String aKey) {
		int hashCode = makeHashCode(aKey);
		if (this.hashArray[hashCode] == null) {
			//  Key doesn't exist in hashmap:
			return null;
		}
		Object savedValue = null;
		//  The key may be in the hashmap:
		KeyValuePair currentKeyAndValue = this.hashArray[hashCode];
		while (currentKeyAndValue != null) {
			if (currentKeyAndValue.getKey() == aKey) {
				savedValue = currentKeyAndValue.getValue();
				//  Then replace it with the next element in the linked list.
				while (currentKeyAndValue.getNextNode() != null) {
					currentKeyAndValue.setKeyAndValue(currentKeyAndValue.getNextNode().getKey(), currentKeyAndValue.getNextNode().getValue());
					currentKeyAndValue.setNextNode(currentKeyAndValue.getNextNode());
				}
			} else {
				currentKeyAndValue = currentKeyAndValue.getNextNode();
			}
		}
		return savedValue;

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

	public void setKeyAndValue(String aKey, Object aValue) throws IllegalArgumentException {
		if (aKey == null) {
			throw new IllegalArgumentException("Key cannot be null.");
		}
		this.key = aKey;
		this.value = aValue;
	}

	public void setKey(String aKey) throws IllegalArgumentException {
		if (aKey == null) {
			throw new IllegalArgumentException("Key cannot be null.");
		}
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