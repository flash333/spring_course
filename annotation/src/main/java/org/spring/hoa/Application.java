package org.spring.hoa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.time.Month;

import static org.spring.hoa.Application.FieldInformation.*;

public class Application {
    @FieldInformation(
            creationTime = @CreationDate(year = 2021, month = Month.NOVEMBER, day = 5),
            createdBy = "Hoa Tran",
            documentation = "Available Java versions",
            version = 2)
    private Integer numberOfJavaVersions = null;

    public static void main(String[] args) throws NoSuchFieldException {
        Application application = new Application();
        final Field numberOfJavaVers = application.getClass().getDeclaredField("numberOfJavaVersions");
        final FieldInformation fieldInfo = numberOfJavaVers.getAnnotation(FieldInformation.class);
        final CreationDate cd = fieldInfo.creationTime();
        String dateStr = "%d-%s-%d".formatted(cd.year(), cd.month(), cd.day());
        System.out.println("Created time: " + dateStr);
        System.out.println("Created by: " + fieldInfo.createdBy());
        System.out.println("Documentation: " + fieldInfo.documentation());
        System.out.println("Version: " + fieldInfo.version());
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface FieldInformation {
        CreationDate creationTime();

        String createdBy();

        String documentation();

        int version() default 1;

        @interface CreationDate {
            int year();

            Month month();

            int day();
        }
    }
}
