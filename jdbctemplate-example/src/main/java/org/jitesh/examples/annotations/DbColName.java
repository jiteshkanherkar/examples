package org.jitesh.examples.annotations;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD})
public @interface DbColName {

	String name();
}
