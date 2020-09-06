package org.graduationdesign.gdmscommon.annotation;

import org.graduationdesign.gdmscommon.configure.GdmsServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GdmsServerProtectConfigure.class)
public @interface EnableGdmsServerProtect {

}
