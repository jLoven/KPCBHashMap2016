//  30 September 2016
//  Jackie Loven
//  jackieloven@gmail.com

package customHashMap;

import static org.junit.Assert.*;
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
	public void testIllegalSizeHashmapConstructor() {
		new HashMapJLoven(0);
	}
	
	@Test
	public void testLegalSizeHashmapConstructor() {
		new HashMapJLoven(1);
	}
	
	@Test
	public void testDeleteSingleKey() {
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
		assertNull(hashed.get("Aa"));
		assertNull(hashed.delete("Aa"));
	}
	
	@Test
	public void testDeleteMultipleHashesToSameKeyInOrder() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("Aa", newObject1);
		KeyValuePair keyValue1 = new KeyValuePair();
		Object newObject2 = new Object();
		keyValue1.setKeyAndValue("BB", newObject2);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertTrue(hashed.set(keyValue1.getKey(), keyValue1.getValue()));
		
		assertEquals(newObject1, hashed.delete("Aa"));
		assertNull(hashed.get("Aa"));
		assertNull(hashed.delete("Aa"));
		assertEquals(newObject2, hashed.delete("BB"));
		assertNull(hashed.get("BB"));
		assertNull(hashed.delete("BB"));
	}
	
	@Test
	public void testDeleteMultipleHashesToSameKeyOutOfOrder() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("Aa", newObject1);
		KeyValuePair keyValue1 = new KeyValuePair();
		Object newObject2 = new Object();
		keyValue1.setKeyAndValue("BB", newObject2);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertTrue(hashed.set(keyValue1.getKey(), keyValue1.getValue()));
		
		assertEquals(newObject2, hashed.delete("BB"));
		assertNull(hashed.get("BB"));
		assertNull(hashed.delete("BB"));
		assertEquals(newObject1, hashed.get("Aa"));
		assertEquals(newObject1, hashed.delete("Aa"));
		assertNull(hashed.get("Aa"));
		assertNull(hashed.delete("Aa"));
		assertNull(hashed.get("BB"));
		assertNull(hashed.delete("BB"));
	}
	
	@Test
	public void testLoadFactor() {
		KeyValuePair keyValue = new KeyValuePair();
		Object newObject1 = new Object();
		keyValue.setKeyAndValue("Aa", newObject1);
		KeyValuePair keyValue1 = new KeyValuePair();
		Object newObject2 = new Object();
		keyValue1.setKeyAndValue("BB", newObject2);
		KeyValuePair keyValue2 = new KeyValuePair();
		Object newObject3 = new Object();
		keyValue2.setKeyAndValue("BB", newObject3);
		KeyValuePair keyValue3 = new KeyValuePair();
		Object newObject4 = new Object();
		keyValue3.setKeyAndValue("AA", newObject4);
		
		HashMapJLoven hashed = new HashMapJLoven(20);
		assertTrue(hashed.set(keyValue.getKey(), keyValue.getValue()));
		assertTrue(hashed.set(keyValue1.getKey(), keyValue1.getValue()));
		assertEquals((float) 1/20, hashed.load(), 0.00000001);
		
		assertTrue(hashed.set(keyValue2.getKey(), keyValue2.getValue()));
		assertEquals((float) 1/20, hashed.load(), 0.00000001);
		
		assertTrue(hashed.set(keyValue3.getKey(), keyValue3.getValue()));
		assertEquals((float) 2/20, hashed.load(), 0.00000001);
	}
}
