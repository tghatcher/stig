/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface ProxyActions {
	public String[] value() default {};
}
