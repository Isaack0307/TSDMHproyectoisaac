package com.example.TSDMHproyectoisaac

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class DetalleTemaFragment : Fragment() {

    companion object {
        private const val ARG_TEMA = "tema"
        // Eliminamos ARG_LISTA_TEMAS ya que no necesitamos la lista completa aquí

        // newInstance ahora solo necesita el tema seleccionado
        fun newInstance(tema: Tema): DetalleTemaFragment {
            val fragment = DetalleTemaFragment()
            val args = Bundle()
            args.putSerializable(ARG_TEMA, tema)
            // No pasamos la lista completa de temas aquí
            fragment.arguments = args
            return fragment
        }
    }

    private var temaActual: Tema? = null
    // Eliminamos listaTemasCompleta ya que no la usaremos aquí

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            temaActual = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable(ARG_TEMA, Tema::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getSerializable(ARG_TEMA) as Tema?
            }
            // No intentamos recuperar listaTemasCompleta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_tema, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tituloTextView: TextView = view.findViewById(R.id.text_view_detalle_titulo)
        val contenidoTextView: TextView = view.findViewById(R.id.text_view_detalle_contenido)
        val btnVolverTemario: Button = view.findViewById(R.id.btn_volver_temario)
        // Eliminamos la referencia a btnSiguienteTema

        // Mostrar el contenido del tema actual
        temaActual?.let {
            tituloTextView.text = it.titulo
            contenidoTextView.text = it.contenido
        }

        // Lógica para el botón "Temario" - Ahora simplemente hace popBackStack() una vez.
        // Para ir al temario principal desde cualquier subtema,
        // el usuario usará el Navigation Drawer o el botón "Atrás" del sistema,
        // que ahora se comportará como deseamos gracias al `addToBackStack` bien configurado.
        btnVolverTemario.setOnClickListener {
            parentFragmentManager.popBackStack() // Simplemente retrocede a la pantalla anterior (que será el TemarioFragment)
        }

        // Eliminamos toda la lógica y llamada a actualizarEstadoBotonSiguiente
    }
    // Eliminamos la función actualizarEstadoBotonSiguiente
}