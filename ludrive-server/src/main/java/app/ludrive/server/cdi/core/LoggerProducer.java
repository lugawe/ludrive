package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import app.ludrive.core.logging.Logger;
import app.ludrive.server.cdi.util.ClassNamed;
import app.ludrive.server.logging.Slf4jLoggerFactory;

@Dependent
public class LoggerProducer {

    public LoggerProducer() {}

    private Logger createLogger(Class<?> tClass) {
        return Slf4jLoggerFactory.getLogger(tClass);
    }

    @Produces
    public Logger produce(InjectionPoint injectionPoint) {

        ClassNamed classNamed = injectionPoint.getAnnotated().getAnnotation(ClassNamed.class);
        if (classNamed != null) {
            return createLogger(classNamed.value());
        }

        Class<?> declaringClass = injectionPoint.getMember().getDeclaringClass();

        return createLogger(declaringClass);
    }
}
