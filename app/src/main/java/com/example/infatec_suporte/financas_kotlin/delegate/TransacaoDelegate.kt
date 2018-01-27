package com.example.infatec_suporte.financas_kotlin.delegate

import com.example.infatec_suporte.financas_kotlin.model.Transacao

/**
 * Created by infatec-suporte on 23/01/2018.
 */
interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}