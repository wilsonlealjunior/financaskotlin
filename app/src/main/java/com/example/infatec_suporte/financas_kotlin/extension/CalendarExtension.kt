package com.example.infatec_suporte.financas_kotlin.extension

import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Created by infatec-suporte on 23/01/2018.
 */

fun Calendar.formataParaBrasileiro(): String {
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)


}