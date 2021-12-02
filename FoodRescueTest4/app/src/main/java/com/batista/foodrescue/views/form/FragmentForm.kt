package com.batista.foodrescue.views

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.batista.foodrescue.R
import com.batista.foodrescue.data.model.Produto
import com.batista.foodrescue.databinding.FragmentFormBinding
import com.example.cardview.fragment.DatePickerFragment
import com.example.cardview.fragment.TimePickerListener
import java.text.SimpleDateFormat
import java.util.*

class FragmentForm : Fragment(), TimePickerListener, AdapterView.OnItemSelectedListener {

    private val orderViewModel: OrderViewModel by activityViewModels()
    private var _produtoSelecionado: Produto? = null
    private val produtoSelecionado get() = _produtoSelecionado!!

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFormBinding.inflate(inflater, container, false)

        carregarDados()

        orderViewModel.selectedDateLiveData.observe(viewLifecycleOwner, { selectedDate ->
            binding.editData.setText(selectedDate.toString())
        })

        binding.editData.setOnClickListener {
            DatePickerFragment(this).show(parentFragmentManager, "DatePicker")
        }

        binding.buttonAdd.setOnClickListener {
            inserirNoBanco()
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun inputCheck(
        nome: String, desc: String, qtd: String,
        data: String, status: String
    ): Boolean {
        return !(TextUtils.isEmpty(nome) &&
                TextUtils.isEmpty(desc) &&
                TextUtils.isEmpty(qtd) &&
                TextUtils.isEmpty(data) &&
                TextUtils.isEmpty(status)
                )
    }

    fun inserirNoBanco() {
        val nome = binding.editNome.text.toString()
        val desc = binding.editDesc.text.toString()
        val qtd = binding.editQtd.text.toString()
        val data = binding.editData.text.toString()
        val status = binding.editStatus.text.toString()

        if (inputCheck(nome, desc, qtd, data, status)) {
            _produtoSelecionado = orderViewModel.productSelected
            var atualizarCriar = ""
            if (_produtoSelecionado != null) {
                val produto = Produto(produtoSelecionado.id, nome, desc, qtd,
                    orderViewModel.selectedDateLiveData.value!!, status)
                orderViewModel.addProduto(produto)
                atualizarCriar = "Produto Atualizado!"
            } else {
                val produto = Produto(0, nome, desc, qtd,
                    orderViewModel.selectedDateLiveData.value!!,
                    status)
                orderViewModel.updateProduto(produto)
                "Produto Adicionado!"
            }
            Toast.makeText(
                context, atualizarCriar,
                Toast.LENGTH_LONG
            ).show()

            findNavController().navigate(R.id.action_fragmentTelaForm_to_fragmentTelaListaProdutos)
        } else {
            Toast.makeText(
                context, "Preencha todos os campos!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun carregarDados() {
        _produtoSelecionado = orderViewModel.productSelected
        if (_produtoSelecionado != null) {
            binding.editNome.setText(produtoSelecionado.name)
            binding.editDesc.setText(produtoSelecionado.description)
            binding.editQtd.setText(produtoSelecionado.assignetTo)
            binding.editData.setText(produtoSelecionado.dueDate)
            binding.editStatus.setText(produtoSelecionado.status)
        } else {
            binding.editNome.text = null
            binding.editDesc.text = null
            binding.editQtd.text = null
            binding.editData.text = null
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTimeSelected(date: Date) {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formatedDate = formatter.format(date).toString()
        orderViewModel.selectedDateLiveData.postValue(formatedDate)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val itemAtPosition: String = p0?.getItemAtPosition(p2) as String
        Log.d("Developer", "itemAtPosition: $itemAtPosition")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}
