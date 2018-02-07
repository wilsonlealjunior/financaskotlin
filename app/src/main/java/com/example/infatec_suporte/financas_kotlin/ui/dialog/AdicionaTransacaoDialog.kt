package com.example.infatec_suporte.financas_kotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.model.Tipo

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class AdicionaTransacaoDialog(viewGroup: ViewGroup,
                              context: Context) : FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa

    }


}