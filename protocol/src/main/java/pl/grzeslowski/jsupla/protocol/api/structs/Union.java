package pl.grzeslowski.jsupla.protocol.api.structs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
public @interface Union {
    int groupNumber();

    String discriminatorName() default "";
}