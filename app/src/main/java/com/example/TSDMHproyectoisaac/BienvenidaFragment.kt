package com.example.TSDMHproyectoisaac

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BienvenidaFragment : Fragment() {

    private val handler = Handler(Looper.getMainLooper())
    private val NAV_DELAY_MS = 5000L // 5 segundos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_bienvenida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Establecer el título de la toolbar para este fragmento
        (activity as? MainActivity)?.supportActionBar?.title = "Bienvenidos"

        // Programar la navegación automática después de NAV_DELAY_MS
        handler.postDelayed({
            // Aseguramos que el fragmento sigue adjunto a la actividad antes de navegar
            if (isAdded && activity != null && !isDetached) {
                (activity as? MainActivity)?.let { mainActivity ->
                    mainActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, TemarioFragment())
                        // Si no quieres volver a BienvenidaFragment desde Temario, no uses addToBackStack
                        .commitAllowingStateLoss()
                    // Cambiar el título de la toolbar al navegar al Temario
                    mainActivity.supportActionBar?.title = "Temario"
                }
            }
        }, NAV_DELAY_MS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Importante: Eliminar cualquier callback pendiente del handler para evitar fugas de memoria
        handler.removeCallbacksAndMessages(null)
    }
}