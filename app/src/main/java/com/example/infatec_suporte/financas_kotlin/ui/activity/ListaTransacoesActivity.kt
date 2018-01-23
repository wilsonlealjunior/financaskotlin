package com.example.infatec_suporte.financas_kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import com.example.infatec_suporte.financas_kotlin.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        //primeiro obejto sem categoria, segundo com categoria
        //sobrecarga de maneira facil( Named Parameter)
        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraLista(transacoes);


    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = (ListaTransacoesAdapter(transacoes, this))
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(Transacao(valor = BigDecimal(20.5), tipo = Tipo.DESPESA),
                Transacao(BigDecimal(100.0), "economia", Tipo.RECEITA),
                Transacao(valor = BigDecimal(200.0), tipo = Tipo.DESPESA),
                Transacao(BigDecimal(500.0), "Premio", Tipo.RECEITA),
                Transacao(BigDecimal(500.0), "Despesa do final de semana", Tipo.RECEITA))
    }


}