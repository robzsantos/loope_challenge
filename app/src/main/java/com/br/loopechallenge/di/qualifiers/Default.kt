package com.br.loopechallenge.di.qualifiers

import javax.inject.Qualifier

/**
 * Created by Robson on 2019-08-05.
 */
@Qualifier
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Default