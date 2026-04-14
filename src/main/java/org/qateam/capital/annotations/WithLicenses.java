package org.qateam.capital.annotations;

import org.qateam.capital.enums.LicenseType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(WithLicensesContainer.class)
public @interface WithLicenses {
    LicenseType[] value() default {};
    boolean all() default false;
}
