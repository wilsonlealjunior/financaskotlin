package com.example.infatec_suporte.financas_kotlin.model

import java.math.BigDecimal

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class Resumo(private val transacoes: List<Transacao>) {


    val receita get() = somaPor(Tipo.RECEITA)
    val despesa get() = somaPor(Tipo.DESPESA)
    val total get() = receita.subtract(despesa)

    private fun somaPor(tipo: Tipo): BigDecimal {
        val somaDeTransacoes = transacoes
                .filter { it.tipo == tipo }
                .sumByDouble { it.valor.toDouble() }
        return BigDecimal(somaDeTransacoes)
    }


}