package test;
import reflection.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;


public class TestInspector {
	ClassA a;
	ClassB[] bArray;
	ClassB b;
	String str;
	Boolean recursive;
	
	
	@Before 
	public void setUp() {
		
		recursive = false;
		
	}
	
	@Test
	public void TestClassName() throws Exception {
		a = new ClassA();
		b = new ClassB();
		bArray = new ClassB[12];
		str = "Test String";
		new Inspector().inspect(b, recursive);
	}
	
}
