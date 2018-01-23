package com.example.infatec_suporte.financas_kotlin.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.extension.formataParaBrasileiro
import com.example.infatec_suporte.financas_kotlin.extension.limitaEmAte
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 14;


    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]
        adicionaValorEIcone(viewCriada, transacao)
        adicionaCategoria(viewCriada, transacao)
        adicionaData(viewCriada, transacao)
        return viewCriada;


    }

    private fun adicionaData(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionaCategoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaValorEIcone(viewCriada: View, transacao: Transacao) {


        val cor: Int = corPor(transacao.tipo)
        val icone: Int = iconePor(transacao.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()


    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {

            return (R.drawable.icone_transacao_item_receita)
        }
        return (R.drawable.icone_transacao_item_despesa)

    }

    private fun corPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return (ContextCompat.getColor(context, R.color.receita))

        }
        return (ContextCompat.getColor(context, R.color.despesa))


    }


    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]

    }

    override fun getItemId(p0: Int): Long {
        return 0;

    }

    override fun getCount(): Int {
        return transacoes.size;
    }


}