package rdc.ericwangi.banking.validators;

import org.springframework.stereotype.Component;
import rdc.ericwangi.banking.exceptions.ObjectsValidationException;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Component
public class ObjectsValidator<T> {

    private final ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate){
       Set<ConstraintViolation<T>> violations= validator.validate(objectToValidate);
       if(!violations.isEmpty()){
            Set<String>erreurMessage=violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            //leve une exception
           throw new ObjectsValidationException(erreurMessage,objectToValidate.getClass().getName());
       }
    }

}
