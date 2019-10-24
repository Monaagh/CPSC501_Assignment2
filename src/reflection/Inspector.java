package reflection;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	//System.out.println(c.getName());
    	int d = depth;
    	//Class superclassObject = c.getSuperclass();
    	int count = 0;
    	int dep = depth;
    	
    	//while (!c.equals(Object.class) && count == 0) {
    	//while (count == 0) {
    		if (c.isArray()) {
    			System.out.println(c.getName() + " is an array");
    			//TO DO
    		} else {
	    		if (c.equals(Object.class)) {
	    			count++;
	    		}
	    		//System.out.println("/////////////////// " + count + "///////////////");
	    		System.out.println();
	    		printIndent(d);
	    		System.out.println("***************Inspection for Class '" + c.getName()+ "***************\n");
	    		getClassName(c, d);
	    		
	    		//inspectSuperClass(c, d);    		
	    		if (count != 1) {
	    			
	    			printIndent(dep);
	    	    	System.out.println("--------------Inspecting Immediate Superclass--------------");
	    	    	
	    			printIndent(dep);
	    			System.out.println("Superclass name:" + c.getSuperclass().getName());
	    			dep = dep +1;
	    			inspectClass(c.getSuperclass(), obj, recursive, dep);	
	    		}
	    		
	    		//printIndent(d);
	    		//System.out.println("***************Continue Inspection for Class '" + c.getName()+ "***************\n");
	    		
	    		inspectInterface(c, obj, d);
	    		inspectConstructor(c, d);
	    		inspectMethod(c,d);
	    		inspectField(c, obj, d);
	    		//c = c.getSuperclass();
	    		d++;
    		}
    		   		
    	}
    	

    private void inspectConstructor(Class c, int depth) {
    	int index=0;
    	//get all constructors
    	Constructor[] allConstructors = c.getDeclaredConstructors();
    	printIndent(depth);
    	System.out.println("--------------Inspecting Declared Constructors--------------");
    	// print information for all constructors
    	for (Constructor cons: allConstructors) {
    		
    		printIndent(depth);
    		System.out.println("Constructor #" + index++);
    		
    		//constructor name
    		printIndent(depth);
    		System.out.println(" Constructor Name: " + cons.getName());
    		
    		// constructor parameter types
    		printIndent(depth);
    		System.out.print(" Constructor Parameter Types: ");
    		Class[] parameters = cons.getParameterTypes();
    		if (parameters.length != 0 ) {
    			for (Class parameter: parameters) {
    				System.out.print(parameter.getName() + " ");
    			}
    		} else {
    			System.out.print("No parameter");
    		}
    		
    		System.out.println();
    		
    		//constructor modifier
    		printIndent(depth);
    		System.out.println(" Modifier: " + getModifierString(cons.getModifiers()));
    	}
    	
    }
    
    private void inspectMethod(Class c, int depth) {
    	int index=0;
    	//get all declared methods
    	Method[] methods = c.getDeclaredMethods();
    	
    	printIndent(depth);
    	System.out.println("--------------Inspecting Declared Methods--------------");
    	
    	// get information for each method
    	for (Method method: methods) {
    		printIndent(depth);
    		System.out.println("Method #" + index++);
    		// method name
    		printIndent(depth);
    		System.out.println(" Method name: " + method.getName());
    		
    		//method exceptions
    		printIndent(depth);
    		System.out.print(" Method exceptions: ");
    		Class[] exceptions = method.getExceptionTypes();
    		if (exceptions.length != 0) {
    			for (Class exception: exceptions) {
        			System.out.print(exception.getName() + " ");
        		}
    		} else {
    			System.out.print("No Exception thrown");
    		}
    		System.out.println();
    		
    		//method parameter types
    		printIndent(depth);
    		System.out.print(" Method Parameter Type: ");
    		Class[] parameters = method.getParameterTypes();
    		if (parameters.length != 0 ) {
    			for (Class parameter: parameters) {
    				System.out.print(parameter.getName() + " ");
    			}
    		} else {
    			System.out.print("No parameter");
    		}
    		System.out.println();
    		
    		//method return type
    		printIndent(depth);
    		System.out.println(" Return Type: " + method.getReturnType().getName());
    		
    		//method modifier
    		printIndent(depth);
    		System.out.println(" Modifier: " + getModifierString(method.getModifiers()));
    		
    		
    	}
    }
    
    private void inspectField(Class c, Object obj, int depth) {
    	int index = 0;
    	//get all fields
    	Field[] fields = c.getDeclaredFields();
    	
    	printIndent(depth);
    	System.out.println("--------------Inspecting Declared Fields--------------");
    	
    	if (fields.length == 0) {
    		printIndent(depth);
    		System.out.println("No Field");
    	}
    	//check for field array
    	//get information for each field
    	for (Field field : fields) {
    		
    		printIndent(depth);
    		System.out.println("Field #" + index++);
    		
    		//field name
    		printIndent(depth);
    		System.out.println(" Field Name: " + field.getName());
    		
    		//field type
    		printIndent(depth);
    		System.out.println(" Field Type: " + field.getType().getName());
    		
    		//field modifier
    		printIndent(depth);
    		System.out.println(" Field modifer: " + getModifierString(field.getModifiers()));
    		
    		//field current value
    		if (!field.getType().isArray()) {
    			field.setAccessible(true);
    			Object value = null; 
    			try {
    				value = field.get(obj);
    			} catch (IllegalAccessException e) {
    				System.out.println(e);
    			}
    			printIndent(depth);
    			
    				
    				// if a field is primitive
    			if (field.getType().isPrimitive()) {
    				//Object value = field.get(obj);
    				System.out.println(" Filed Value: " + value);
    			} 
    				
    			// if field is a reference object
   				else {
   					
   					//Object value = field.get(obj);
   					int hashcode = System.identityHashCode(field);
   					String reference = Integer.toHexString(hashcode);
   					if (value != null) {
    					System.out.println(" Field Value: " + value.getClass().getName() + "@" + reference);
    				} else {
    					System.out.println(" Field value: null");
    				}
   				}
    		} else {
    			
    			//Class array = field.getType();
    			//Object arrayObject
    			//array name
    			printIndent(depth);
    			System.out.println("  Array Name: " + field.getType().getName());
    			
    			//array component type
    			printIndent(depth);
    			System.out.println("  Array Component Type: " +  field.getType().getComponentType().getName());
    			
    			try {
    				//array length
    				printIndent(depth);
    				field.setAccessible(true);
    				int length= Array.getLength(field.get(c.newInstance()));
    				System.out.println("  Array Length: " + length);
    				
    				//array content
    				printIndent(depth);
    				System.out.print("  Array Content: ");
    				for (int i = 0; i< length; i++) {
    					
    					System.out.print(Array.get(field.get(c.newInstance()), i) + " " );
    				}
    				
    				System.out.println();
    			} catch (IllegalAccessException e) {
    				System.out.println(e);
    			} catch (InstantiationException e) {
    				System.out.println(e);
    			}
    		}
    	}
    }
    
    
	private void inspectInterface(Class c, Object obj, int depth) {
		int d = depth;
		//Object obj = null;
		//Array allInterfaces;
		
		printIndent(depth);
    	System.out.println("--------------Inspecting Implemented Interfaces--------------");
    	
    	
		//printIndent(depth);
		//System.out.println("Interfaces: ");
		Class[] interfaces = c.getInterfaces();
		//allInterfaces.add(interfaces);
		if (interfaces.length != 0) {
			for (int i = 0; i < interfaces.length; i++ ) {
				printIndent(d);
				System.out.println("Interface #" + i);
				
				printIndent(d);
				System.out.println(" Interface name: " + interfaces[i].getName());
				
	    		d++;
	    		System.out.println();
				printIndent(d);
	    		System.out.println("***************Inspection for Interface '" + interfaces[i].getName()+ "'***************\n");
	    		
	    		inspectInterface(interfaces[i], obj, d);
	    		
	    		System.out.println();
	    		printIndent(d);
	    		System.out.println("***************Continue Inspection for Interface '" + interfaces[i].getName()+ "'***************\n");
	    		
	    		inspectMethod(interfaces[i], d);
	    		inspectField(interfaces[i], obj, d);
	    		
	    		System.out.println();
	    		printIndent(d);
	    		System.out.println("***************Finished Inspection for Interface '" + interfaces[i].getName()+ "'***************\n");
				
				
				
				//allInterfaces.add(interfaces[i]);	
			}
		} else {
			printIndent(depth);
			System.out.println("No Interface");
		}
		
		/*if (interfaces.length != 0) {
			for (int i = 0; i < interfaces.length; i++ ) {
				printIndent(depth);
				System.out.println(" Interface name: " + interfaces[i].getName());
				try {
					obj = interfaces[i].newInstance();
					inspectClass(interfaces[i], obj, recursive, depth);
				} catch (InstantiationException e) {
					System.out.println(e);
				} catch (IllegalAccessException e) {
					System.out.println(e);
				}
				
				
			}
		} else {
			printIndent(depth);
			System.out.println("No Interface");
		}*/
	}

	private void inspectSuperClass(Class c, int depth) {
		
		printIndent(depth);
    	System.out.println("--------------Inspecting Immediate Superclass--------------");
    	
		printIndent(depth);
		System.out.println("Superclass name:" );
		printIndent(depth);
		System.out.println(" " + c.getSuperclass().getName());
	}
    
    public void getClassName(Class c, int depth) {
    	
    	printIndent(depth);
    	System.out.println("--------------Inspecting Class Name--------------");
    	
    	printIndent(depth);
    	System.out.println("Class name: "  +c.getName());
    }
    
    public void printIndent(int depth) {
    	for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
    }
    
    
    public String getModifierString(int mod) {
    	String modifier = null; 
    	if (Modifier.isPublic(mod)) {
    		modifier = "Public";
    	} else if (Modifier.isPrivate(mod)) {
    		modifier = "Private";
    	} else if (Modifier.isPrivate(mod)) {
    		modifier = "Private";
    	} else if (Modifier.isProtected(mod)) {
    		modifier = "Protected";
    	} else if (Modifier.isStatic(mod)) {
    		modifier = "Static";
    	} else if (Modifier.isFinal(mod)) {
    		modifier = "Final";
    	} else if (Modifier.isSynchronized(mod)) {
    		modifier = "Synchronized";
    	} else if (Modifier.isVolatile(mod)) {
    		modifier = "Volatile";
    	} else if (Modifier.isNative(mod)) {
    		modifier = "Native";
    	} else if (Modifier.isInterface(mod)) {
    		modifier = "Interface";
    	} else if (Modifier.isTransient(mod)) {
    		modifier = "Transient";
    	} else if (Modifier.isStrict(mod)) {
    		modifier = "Strict";
    	}
    	
    	
    	return modifier;
    }

}
