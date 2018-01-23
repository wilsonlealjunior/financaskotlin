package com.example.infatec_suporte.financas_kotlin.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.extension.formataParaBrasileiro
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import com.example.infatec_suporte.financas_kotlin.ui.ResumeView
import com.example.infatec_suporte.financas_kotlin.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

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

        lista_transacoes_adiciona_receita.setOnClickListener {
            configuraDialog()

        }


    }


    private fun AtualizaTransacoes(transacao: Transacao) {
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