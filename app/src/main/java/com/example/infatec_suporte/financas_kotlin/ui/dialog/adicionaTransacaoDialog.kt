package com.example.infatec_suporte.financas_kotlin.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.infatec_suporte.financas_kotlin.R
import com.example.infatec_suporte.financas_kotlin.R.id.lista_transacoes_adiciona_menu
import com.example.infatec_suporte.financas_kotlin.extension.converteParaCalendar
import com.example.infatec_suporte.financas_kotlin.extension.formataParaBrasileiro
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.math.MathContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by infatec-suporte on 23/01/2018.
 */
class adicionaTransacaoDialog(private val viewGroup: ViewGroup,
                              private val context: Context) {

    private val viewCriada = criaLayout()

    private fun criaDialog() {


        configuraCampoData()

        configuraCampoCategoria()

        configuraFormulario()
    }

    private fun configuraFormulario() {
        AlertDialog.Builder(context)
                .setTitle(R.string.adiciona_receita)
                .setView(viewCriada)
                .setPositiveButton("Adicionar",
                        { _, _ ->
                            val valorEmTexto = viewCriada.form_transacao_valor.text.toString()
                            val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                            val categoriaEmTexto = viewCriada.
                                    form_transacao_categoria.selectedItem.toString()

                            val valor = converteCampoValor(valorEmTexto)

                            val data = dataEmTexto.converteParaCalendar()
                            val transacaoCriada = Transacao(tipo = Tipo.RECEITA,
                                    valor = valor,
                                    data = data,
                                    categoria = categoriaEmTexto)
                            AtualizaTransacoes(transacaoCriada)
                            lista_transacoes_adiciona_menu.close(true)


                        })
                .setNegativeButton("cancelar", null)
                .show()
    }

    fun converteCampoValor(valorEmTexto: String): BigDecimal{
        return try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversao de valor",
                    Toast.LENGTH_LONG)
            BigDecimal.ZERO
        }

    }

    private fun configuraCampoCategoria() {
        val adapter = ArrayAdapter.createFromResource(context,
                R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item)
        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configuraCampoData() {

        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)



        viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro());
        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        viewCriada.form_transacao_data.
                                setText(dataSelecionada.formataParaBrasileiro())
                    }
                    , ano, mes, dia)
                    .show()

        }
    }

    private fun criaLayout(): View {

        return LayoutInflater.from(context).
                inflate(R.layout.form_transacao,
                        viewGroup,
                        false)
    }
}