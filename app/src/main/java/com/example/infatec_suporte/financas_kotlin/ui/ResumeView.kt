package com.example.infatec_suporte.financas_kotlin.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.extension.formataParaBrasileiro
import com.example.infatec_suporte.financas_kotlin.model.Resumo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class ResumeView(private val context: Context, private val view: View, transacoes: List<Transacao>) {
    private val resumo: Resumo = Resumo(transacoes)

    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)




    fun atualiza(){
       adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }


    private fun adicionaReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }

    }

    private fun adicionaTotal() {
        val total = resumo.total
        var cor = corPor(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }


    }

    private fun corPor(valor: BigDecimal): Int {
        if (valor >= BigDecimal.ZERO) {
            return (corReceita)
        }
        return (corDespesa)

    }

}