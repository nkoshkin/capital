package org.qateam.capital.annotations;

import org.qateam.capital.enums.UserType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(SkipForUsersContainer.class)
public @interface SkipForUsers {
    UserType[] value();
    String reason() default "";
}
