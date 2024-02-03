package com.example.SkillTribe.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Class clazz, Long id){
        super(clazz.getSimpleName() + " instance with " + id +" id is not fount!");
    }
}
