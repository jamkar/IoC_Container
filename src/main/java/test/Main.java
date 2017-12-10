package test;

import context.AnnotationConfigContext;
import context.Context;

public class Main {

    public static void main(String[] args) {

        Context context = new AnnotationConfigContext(MyConfig.class);
        String appName = (String) context.getBean("applicationName");
        System.out.println(appName);

    }

}
