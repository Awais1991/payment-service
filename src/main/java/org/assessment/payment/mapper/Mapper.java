package org.assessment.payment.mapper;

public interface Mapper<T, U> {

    U mapToEntity(T t);
    U updateEntity(T t, U u);
    T mapToDto(U u);

}
