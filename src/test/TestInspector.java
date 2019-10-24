package test;
import reflection.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;


public class TestInspector {
	ClassA a;
	ClassB b;
	Boolean recursive;
	
	
	@Before 
	public void setUp() {
		
		recursive = false;
		
	}
	
	@Test
	public void TestClassName() throws Exception {
		a = new ClassA();
		b = new ClassB();
		new Inspector().inspect(b, recursive);
	}
	
	
	
}
