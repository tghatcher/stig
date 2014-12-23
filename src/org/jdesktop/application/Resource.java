/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Resource {
	public String key() default "";
}
