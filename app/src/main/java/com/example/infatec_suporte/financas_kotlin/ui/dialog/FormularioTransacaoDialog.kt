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
import com.example.infatec_suporte.financas_kotlin.delegate.TransacaoDelegate
import com.example.infatec_suporte.financas_kotlin.extension.converteParaCalendar
import com.example.infatec_suporte.financas_kotlin.extension.formataParaBrasileiro
import com.example.infatec_suporte.financas_kotlin.model.Tipo
import com.example.infatec_suporte.financas_kotlin.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by wilso on 06/02/2018.
 */
abstract class FormularioTransacaoDialog(
        private val context: Context,
        private val viewGroup: ViewGroup) {

    private val viewCriada = criaLayout()
    protected val campoValor = viewCriada.form_transacao_valor
    protected val campoData = viewCriada.form_transacao_data
    protected val campoCategoria = viewCriada.form_transacao_categoria
    abstract val tituloBotaoPositivo: String

    fun chama(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {
        configuraCampoData()
        configuraCampoCategoria(tipo)
        configuraFormulario(tipo, transacaoDelegate)
    }


    private fun configuraFormulario(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {
        val titulo = tituloPor(tipo)

        AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewCriada)
                .setPositiveButton(tituloBotaoPositivo,
                        { _, _ ->
                            val valorEmTexto = campoValor.text.toString()
                            val dataEmTexto = campoData.text.toString()
                            val categoriaEmTexto = campoCategoria.selectedItem.toString()
                            val valor = converteCampoValor(valorEmTexto)
                            val data = dataEmTexto.converteParaCalendar()
                            val transacaoCriada = Transacao(tipo = tipo,
                                    valor = valor,
                                    data = data,
                                    categoria = categoriaEmTexto)

                            transacaoDelegate.delegate(transacaoCriada);

                        })
                .setNegativeButton("cancelar", null)
                .show()
    }

    abstract fun tituloPor(tipo: Tipo): Int

    fun converteCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversao de valor", Toast.LENGTH_LONG)
            BigDecimal.ZERO
        }

    }

    private fun configuraCampoCategoria(tipo: Tipo) {
        val categorias = categoriasPor(tipo)
        val adapter = ArrayAdapter.createFromResource(context,
                categorias,
                android.R.layout.simple_spinner_dropdown_item)
        campoCategoria.adapter = adapter
    }

    protected fun categoriasPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.array.categorias_de_receita
        }
        return R.array.categorias_de_despesa

    }

    private fun configuraCampoData() {

        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)



        campoData.setText(hoje.formataParaBrasileiro());
        campoData.setOnClickListener {
            DatePickerDialog(context,
                    { _, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        campoData.
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