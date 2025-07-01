package com.example.TSDMHproyectoisaac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager // Importar FragmentManager

class CalificacionFragment : Fragment() {

    companion object {
        private const val ARG_CALIFICACION = "calificacion"

        fun newInstance(calificacion: Float): CalificacionFragment {
            val fragment = CalificacionFragment()
            val args = Bundle()
            args.putFloat(ARG_CALIFICACION, calificacion)
            fragment.arguments = args
            return fragment
        }
    }

    private var calificacion: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            calificacion = it.getFloat(ARG_CALIFICACION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calificacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCalificacion: TextView = view.findViewById(R.id.text_view_calificacion)
        val btnRegresarTemario: Button = view.findViewById(R.id.btn_regresar_temario)

        tvCalificacion.text = "Tu calificación: ${"%.1f".format(calificacion)}%"

        btnRegresarTemario.setOnClickListener {
            // Esto vacía la pila de retroceso hasta el TemarioFragment
            // y luego asegura que una nueva instancia de TemarioFragment se muestre.
            parentFragmentManager.popBackStack(TemarioFragment::class.java.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TemarioFragment())
                .commit()
        }
    }
}