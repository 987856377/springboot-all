package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by daier on 2018/2/26.
 */
public class SelfValidatorInfo implements ConstraintValidator<SelfValildator,Object>{
    private String values;

    public SelfValidatorInfo() {
        super();
    }

    @Override
    public void initialize(SelfValildator selfValildator) {
        this.values = selfValildator.value();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String[] value_array = values.split(",");
        boolean flag = false;
        for (int i = 0; i < value_array.length; i++) {
            if (value_array[i].equals(o)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
