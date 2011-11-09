package atunit.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import atunit.AtUnit;
import atunit.Mock;
import atunit.MockFramework;
import atunit.MockFrameworkClass;
import atunit.Stub;
import atunit.Unit;
import atunit.MockFramework.Option;


public class MockitoFrameworkTests {

    JUnitCore junit;
    
    @Before
    public void setUp() {
        junit = new JUnitCore();
    }
    
    @Test
    public void tOptionMock() {
        Result result = junit.run(TestClasses.OptionMocks.class);
        assertTrue(result.wasSuccessful());
        assertEquals(1, result.getRunCount());
    }
    
    @Test
    public void tInheritance() {
        Result result = junit.run(TestClasses.Inheritance.class);
        assertTrue(result.wasSuccessful());
        assertEquals(1, result.getRunCount());
    }
    
    @Test
    public void tOptionStub() {
        Result result = junit.run(TestClasses.OptionStubs.class);
        assertTrue(result.wasSuccessful());
        assertEquals(1, result.getRunCount());
    }
    
    protected static class TestClasses {
        
        @RunWith(AtUnit.class)
        @MockFramework(Option.MOCKITO)
        public static class OptionMocks {
            @Unit protected String unit;
            @Mock protected StringFactory stringFactory;
            
            @Test
            public void tGetString() {
                Mockito.when(stringFactory.getString()).thenReturn("my string");
                
                assertEquals("my string", stringFactory.getString());
            }
            
            public static interface StringFactory {
                public String getString();
            }
        }
        
        public static class Inheritance extends OptionMocks {
            
        }
        
        @RunWith(AtUnit.class)
        @MockFrameworkClass(MockitoFramework.class)
        public static class OptionStubs {
            @Unit protected String unit;
            @Stub protected StringFactory stringFactory;
            
            @Test
            public void tGetString() {
                Mockito.when(stringFactory.getString()).thenReturn("my string");
                
                assertEquals("my string", stringFactory.getString());
            }
            
            public static interface StringFactory {
                public String getString();
            }
        }
        
    }
}
