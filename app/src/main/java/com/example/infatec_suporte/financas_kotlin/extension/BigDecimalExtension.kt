package com.example.infatec_suporte.financas_kotlin.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

/**
 * Created by infatec-suporte on 23/01/2018.
 */
fun BigDecimal.formataParaBrasileiro(): String {
    val formatoBrasileiro = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasileiro.format(this).
            replace("R$", "R$ ").
            replace("-R$", "R$ -")
}