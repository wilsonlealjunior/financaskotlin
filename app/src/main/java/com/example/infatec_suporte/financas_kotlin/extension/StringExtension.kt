package com.example.infatec_suporte.financas_kotlin.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by infatec-suporte on 23/01/2018.
 */
fun String.limitaEmAte(caracteres: Int): String {
    if (this.length > caracteres) {
        val primeiroCaracter = 0
        return "${this.substring(primeiroCaracter, caracteres)}..."
    }
    return this;
}

fun String.converteParaCalendar(): Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data

}