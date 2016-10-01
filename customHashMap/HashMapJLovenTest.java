//  30 September 2016
//  Jackie Loven
//  jackieloven@gmail.com

package customHashMap;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class HashMapJLovenTest {

	@Test
	public void testSetOneValue() {
	
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("Hi KPCB!", newObject1);
		HashMapJLoven hashed = new HashMapJLoven(20);
		
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertEquals(newObject1, hashed.get("Hi KPCB!"));
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
		
		assertEquals(newObject2, hashed.get("hola"));
	}
	
	@Test
	public void testSameHashKey() {
		//  Strings "Aa" and "BB" have the same hashkey, found in https://goo.gl/GH2qN8
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("Aa", newObject1);
		KeyValuePair keyValue1 = new KeyValuePair();
		Object newObject2 = new Object();
		keyValue1.setKeyAndValue("BB", newObject2);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertTrue(hashed.set(keyValue1.getKey(), keyValue1.getValue()));
		
		assertEquals(newObject1, hashed.get("Aa"));
		assertEquals(newObject2, hashed.get("BB"));
	}
	
	@Test
	public void testMultipleHashKeys() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("Aa", newObject1);
		KeyValuePair keyValue1 = new KeyValuePair();
		Object newObject2 = new Object();
		keyValue1.setKeyAndValue("AA", newObject2);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertTrue(hashed.set(keyValue1.getKey(), keyValue1.getValue()));
		
		assertEquals(newObject1, hashed.get("Aa"));
		assertEquals(newObject2, hashed.get("AA"));
	}
	
	@Test
	public void testNullGet() {
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertEquals(null, hashed.get("BB"));
	}
	
	@Test
	public void testNullKeyAndValueValueSet() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = null;
		keyValue.setKeyAndValue("Aa", newObject1);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertEquals(null, hashed.get("Aa"));
	}
	
	@Test
	public void testNullValueSet() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = null;
		keyValue.setKey("Aa");
		keyValue.setValue(newObject1);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertEquals(null, hashed.get("Aa"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullKeyAndValueKeySet() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue(null, newObject1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullKeySet() {
		KeyValuePair keyValue = new KeyValuePair();
		keyValue.setKey(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalSizeHashmapConstructor() {
		HashMapJLoven hashed = new HashMapJLoven(0);
	}
	
	@Test
	public void testLegalSizeHashmapConstructor() {
		HashMapJLoven hashed = new HashMapJLoven(1);
	}
	
	@Test
	public void testDelete() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("Aa", newObject1);
		KeyValuePair keyValue1 = new KeyValuePair();
		Object newObject2 = new Object();
		keyValue1.setKeyAndValue("AA", newObject2);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertTrue(hashed.set(keyValue1.getKey(), keyValue1.getValue()));
		
		assertNull(hashed.delete("Z"));
		assertEquals(newObject1, hashed.delete("Aa"));
	}
}
