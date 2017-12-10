package context;

import annotations.Bean;
import annotations.ComponentScan;
import annotations.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationConfigContext implements Context {

    private Class configClass;
    private Object configObject;

    public AnnotationConfigContext(Class configClass) {
        ComponentScan componentScan = null;
        Annotation[] annotations = configClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Configuration.class) {
                this.configClass = configClass;
                try {
                    this.configObject = configClass.newInstance();
                } catch (Exception e) {
                    throw new IllegalArgumentException("This is not a configuration class");
                }
            }
            if (annotation.annotationType() == ComponentScan.class) {
                componentScan = (ComponentScan) annotation;
            }
        }
        if (this.configClass != null) {
            if (componentScan != null) {
                instantiateComponents(componentScan.packag());
            }
            return;
        }
        throw new IllegalArgumentException("This is not a configuration class");
    }

    private void instantiateComponents(String packag) {
    }

    public Object getBean(String name) {

        Method[] methods = configClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(name) &&
                    method.getAnnotation(Bean.class) != null) {
                try {
                    return method.invoke(configObject);
                } catch (Exception e) {
                    System.out.println("Bean instantiation exception");
                }
            }
        }
        return null;
    }
}
