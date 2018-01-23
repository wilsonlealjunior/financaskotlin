package com.example.infatec_suporte.financas_kotlin.extension

/**
 * Created by infatec-suporte on 23/01/2018.
 */
fun String.limitaEmAte(caracteres: Int): String{
    if(this.length>caracteres){
        val primeiroCaracter = 0
        return "${this.substring(primeiroCaracter,caracteres)}..."
    }
    return this;
}