package com.eleonoralion.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner scanner = new Scanner(System.in);

        Class aClass = Class.forName("com.eleonoralion.classes.Human");
        Field[] fields = aClass.getFields();
        Field[] allFields = aClass.getDeclaredFields();
        Method[] methods = aClass.getMethods();
        Method[] allMethods = aClass.getDeclaredMethods();
        Constructor[] constructors = aClass.getDeclaredConstructors();

        Object object = aClass.newInstance();

        Field ageField = allFields[0];
        ageField.setAccessible(true);
        ageField.set(object, 666);

        Field nameField = allFields[1];
        nameField.setAccessible(true);
        nameField.set(object, "HACK");

        System.out.println(object);

        Object[] objects = new Object[constructors.length];
        int currentConstructor = 0;

        for(Constructor constructor : constructors){
            Class[] parameterTypesOfConstructor = constructor.getParameterTypes();

            Object[] arguments = new Object[parameterTypesOfConstructor.length];
            int currentArg = 0;

            System.out.println("Constructor: " + constructor.getName());
            for (Class parameterType : parameterTypesOfConstructor){
                System.out.println("\t\tParameter: " + parameterType.getName());

                switch (parameterType.getName()){
                    case "int":
                        arguments[currentArg] = scanner.nextInt();
                        break;
                    case "float":
                        arguments[currentArg] = scanner.nextFloat();
                        break;
                    case "java.lang.String":
                        arguments[currentArg] = scanner.next();
                        break;
                    case "boolean":
                        arguments[currentArg] = scanner.nextBoolean();
                        break;
                    default:
                        System.out.println("ERROR!!!!!");
                        arguments[currentArg] = scanner.next();
                        break;
                }

                currentArg++;
            }

            objects[currentConstructor] = constructor.newInstance(arguments);
            currentConstructor++;
        }


        for(Object ob : objects){
            System.out.println(ob);
        }


    }
}
