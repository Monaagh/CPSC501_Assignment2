package test;
import reflection.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;


public class TestInspector {
	ClassA a;
	ClassB[] bArray;
	ClassB[][] bArray2;
	ClassB b;
	ClassD d;
	String str;
	Boolean recursive;
	
	
	@Before 
	public void setUp() {
		
		recursive = false;
		
	}
	
	/*@Test
	public void TestClassA() throws Exception {
		recursive = true;
		a = new ClassA();
		
		bArray2 = new ClassB[12][12];
		
		new Inspector().inspect(a, recursive);
	}
	
	@Test
	public void TestClassAWithParameter() throws Exception {
		recursive = true;
		a = new ClassA(12);
		new Inspector().inspect(a, recursive);
	}
	
	@Test
	public void TestClassB() throws Exception {
		recursive = true;
		b = new ClassB();
		new Inspector().inspect(b, recursive);
	}
	
	@Test
	public void TestClassD() throws Exception {
		recursive = true;
		d = new ClassD();
		new Inspector().inspect(d, recursive);
	}
	
	@Test
	public void TestClassDWithParameter() throws Exception {
		recursive = true;
		d = new ClassD(32);
		new Inspector().inspect(d, recursive);
	}
	
	@Test
	public void TestClassDWith() throws Exception {
		recursive = true;
		d = new ClassD(32);
		new Inspector().inspect(d, recursive);
	}*/
	
	/*@Test
	public void TestClassString() throws Exception {
		recursive = true;
		str = "Test String";
		new Inspector().inspect(str, recursive);
	}*/
	
	/*@Test
	public void TestClass1DArray() throws Exception {
		recursive = true;
		bArray = new ClassB[12];
		new Inspector().inspect(bArray, recursive);
	}*/
	
	@Test
	public void TestClass2DArray() throws Exception {
		recursive = true;
		bArray2 = new ClassB[12][12];
		new Inspector().inspect(bArray2, recursive);
	}
	
	
	/*@Test
	public void TestArrayField() throws Exception {
		//recursive = true;
		class ClassA {
			private double[] arr = {2.3, 2.4};
			private String str;
			private int number; 
			
			public ClassA() {
				str = "Test";
			}
			
			public ClassA(int i) {
				number = i;
				str = "Test";
			}
			
		};
		
		ClassA a = new ClassA();
		new Inspector().inspect(a, recursive);
	}
	
	/*@Test
	public void TestArrayObject()	 {
		recursive = true;
		double[] arr = {2.3, 2.4};
		new Inspector().inspect(arr, recursive);
	}*/
	
	
}
