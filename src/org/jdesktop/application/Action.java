/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import org.jdesktop.application.Task;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Action {
	public String name() default "";

	public String enabledProperty() default "";

	public String selectedProperty() default "";

	public Task.BlockingScope block() default Task.BlockingScope.NONE;

	@Retention(value = RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.PARAMETER })
	public static @interface Parameter {
		public String value() default "";
	}

}
