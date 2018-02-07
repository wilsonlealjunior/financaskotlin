package com.example.infatec_suporte.financas_kotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.delegate.TransacaoDelegate
import com.example.infatec_suporte.financas_kotlin.extension.formataParaBrasileiro
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class AlteraTransacaoDialog(viewGroup: ViewGroup,
                            private val context: Context) : FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }


    fun chama(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {

        val tipo = transacao.tipo
        super.chama(tipo, transacaoDelegate)

        inicializaCampos(transacao)


    }

    private fun inicializaCampos(transacao: Transacao) {
        val tipo = transacao.tipo
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCatergoria(tipo, transacao)
    }

    private fun inicializaCampoCatergoria(tipo: Tipo, transacao: Transacao) {
        val categoriasRetornadas = context.resources.getStringArray(categoriasPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataParaBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }


}