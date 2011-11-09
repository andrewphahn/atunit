package atunit.mockito;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;

import atunit.Mock;
import atunit.Stub;
import atunit.core.MockFramework;


public class MockitoFramework implements MockFramework {

    private Map<Field, Object> mocksAndStubs;
    
    public MockitoFramework() {
        mocksAndStubs = new HashMap<Field, Object>();
    }
    
    @Override
    public Map<Field, Object> getValues(Field[] fields) throws Exception {
        for (Field field : fields) {
            if (isMockOrStub(field)) {
                addMockOrStub(field);
            }
        }
        return mocksAndStubs;
    }
    
    private boolean isMockOrStub(Field field) {
        return isMock(field) || isStub(field);
    }
    
    private boolean isMock(Field field) {
        return hasAnnotation(field, Mock.class);
    }
    
    private boolean isStub(Field field) {
        return hasAnnotation(field, Stub.class);
    }
    
    private boolean hasAnnotation(Field field, Class<? extends Annotation> annotation) {
        return field.getAnnotation(annotation) != null;
    }
    
    private void addMockOrStub(Field field) {
        mocksAndStubs.put(field, Mockito.mock(field.getType()));
    }

}
