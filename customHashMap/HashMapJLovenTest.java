package customHashMap;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashMapJLovenTest {

	@Test
	public void testSetOneValue() {
	
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("hola", newObject1);
		HashMapJLoven hashed = new HashMapJLoven(20);
		
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertEquals(hashed.get("hola"), newObject1);
	}
	
	@Test
	public void testSetReplaceValue() {
	
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("hola", newObject1);
		KeyValuePair keyValue1 = new KeyValuePair();
		Object newObject2 = new Object();
		keyValue1.setKeyAndValue("hola", newObject2);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertTrue(hashed.set(keyValue1.getKey(), keyValue1.getValue()));
		
		assertEquals(hashed.get("hola"), newObject2);
	}
	
	//  Test for same hashkey 
	//  test for multiple hash keys 
	//  test for hashmap size of 1
	//  do a for loop and test that each new addition has a valid get

}
