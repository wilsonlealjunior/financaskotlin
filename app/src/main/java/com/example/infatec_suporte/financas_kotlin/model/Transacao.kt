package com.example.infatec_suporte.financas_kotlin.model

import java.math.BigDecimal
import java.util.Calendar

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class Transacao(val valor: BigDecimal,
                val categoria: String = "Indefinida",
                val tipo: Tipo,
                val data: Calendar = Calendar.getInstance()) {


}