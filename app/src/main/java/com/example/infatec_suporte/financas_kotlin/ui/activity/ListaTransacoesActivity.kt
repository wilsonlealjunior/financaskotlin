package com.example.infatec_suporte.financas_kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.delegate.TransacaoDelegate
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import com.example.infatec_suporte.financas_kotlin.ui.ResumeView
import com.example.infatec_suporte.financas_kotlin.ui.adapter.ListaTransacoesAdapter
import com.example.infatec_suporte.financas_kotlin.ui.dialog.AdicionaTransacaoDialog
import com.example.infatec_suporte.financas_kotlin.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class ListaTransacoesActivity : AppCompatActivity() {

    private var transacoes: MutableList<Transacao> = mutableListOf()
    private val viewDaActivity: View by lazy {
        window.decorView

    }
    private val viewGroupDaActivity by lazy{
        viewDaActivity as ViewGroup
    }

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
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
                .chama(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        adiciona(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }

                })
    }

    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }


    private fun atualizaTransacoes() {
        configuraLista()
        configurarResumo()
    }


    private fun configurarResumo() {
        val resumeView = ResumeView(this, viewDaActivity, transacoes)
        resumeView.atualiza()

    }


    private fun configuraLista() {
        with(lista_transacoes_listview){
            adapter = (ListaTransacoesAdapter(transacoes, this@ListaTransacoesActivity))
            setOnItemClickListener { _, _, posicao, _ ->
                val transacao = transacoes[posicao]
                chamaDialogDeAlteracao(transacao, posicao)

            }

        }

    }

    private fun chamaDialogDeAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
                .chama(transacao, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        altera(transacao, posicao)
                    }

                })
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        transacoes[posicao] = transacao
        atualizaTransacoes()
    }


}