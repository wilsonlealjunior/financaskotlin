package com.example.infatec_suporte.financas_kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.delegate.TransacaoDelegate
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import com.example.infatec_suporte.financas_kotlin.ui.ResumeView
import com.example.infatec_suporte.financas_kotlin.ui.adapter.ListaTransacoesAdapter
import com.example.infatec_suporte.financas_kotlin.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class ListaTransacoesActivity : AppCompatActivity() {

    private var transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)


        configurarResumo()
        configuraLista()
        configuraFab()


    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogDeAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                .chama(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }

                })
    }


    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configurarResumo()
    }


    private fun configurarResumo() {
        val view = window.decorView
        val resumeView = ResumeView(this, view, transacoes)
        resumeView.atualiza()

    }


    private fun configuraLista() {
        lista_transacoes_listview.adapter = (ListaTransacoesAdapter(transacoes, this))
    }


}