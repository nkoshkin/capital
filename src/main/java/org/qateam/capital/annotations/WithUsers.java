package org.qateam.capital.annotations;

import org.qateam.capital.enums.UserType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(WithUsersContainer.class)
public @interface WithUsers {
    UserType[] value() default {};
    boolean all() default false;
}
