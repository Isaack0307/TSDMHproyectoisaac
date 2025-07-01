package com.example.TSDMHproyectoisaac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TemarioFragment : Fragment(), TemarioAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var temarioAdapter: TemarioAdapter
    private lateinit var listaTemas: List<Tema>
    private lateinit var btnIrExamen: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_temario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaTemas = generarDatosTemario()

        recyclerView = view.findViewById(R.id.recycler_view_temario)
        recyclerView.layoutManager = LinearLayoutManager(context)

        temarioAdapter = TemarioAdapter(listaTemas, this)
        recyclerView.adapter = temarioAdapter

        btnIrExamen = view.findViewById(R.id.btn_ir_examen)
        btnIrExamen.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExamenFragment())
                .addToBackStack(TemarioFragment::class.java.name)
                .commit()
        }
    }

    private fun generarDatosTemario(): List<Tema> {
        return listOf(
            Tema(
                "3.15",
                "📱 Creación de Emulador Virtual (AVD)",
                "Un AVD (Android Virtual Device) es una herramienta fundamental para desarrolladores Android.\n\nPermite simular un dispositivo móvil directamente desde tu computadora, evitando el uso de hardware físico durante las pruebas. Ideal para testear el comportamiento de tus apps en diferentes dispositivos, versiones de Android y condiciones de red o sistema.",
                true
            ),
            Tema(
                "3.16",
                "🧠 Subtema 1: ¿Qué es un AVD?",
                "🔹 Un AVD es un emulador que simula un teléfono o tablet real.\n\nEstá integrado en Android Studio y emula la interfaz, el comportamiento y los recursos del sistema operativo Android.\n\n✅ Ventajas clave:\n• No necesitas un celular físico para probar\n• Puedes simular dispositivos de distintos tamaños o gamas\n• Ahorra tiempo en la etapa de pruebas\n• Permite trabajar con múltiples configuraciones desde un solo entorno",
                false
            ),
            Tema(
                "3.17",
                "⚙️ Subtema 2: ¿Para qué se usa un AVD?",
                "🔧 Usos comunes:\n• Probar interfaces en varios tamaños de pantalla 📱📲\n• Ejecutar tu app en distintas versiones de Android 📶\n• Simular comportamientos como rotación, pérdida de señal, batería baja 🔋\n• Verificar compatibilidad sin depender de 10 dispositivos físicos\n\n🧠 Importancia: facilita testear múltiples escenarios reales con rapidez y sin invertir en más hardware.",
                false
            ),
            Tema(
                "3.18",
                "🧩 Subtema 3: Componentes que puedes configurar en un AVD",
                "🛠️ Opciones de personalización:\n• 🧬 Modelo de dispositivo (Pixel, Nexus, tablet...)\n• 🔄 Versión del sistema (Android 9, 10, 11...)\n• 📏 Tamaño y resolución de pantalla\n• 🧠 Memoria RAM y almacenamiento\n• 📡 Sensores como GPS, batería, red, cámara\n• 🔃 Orientación: vertical u horizontal\n\nAsí puedes verificar cómo tu app se comporta en distintos tipos de terminales y configuraciones.",
                false
            ),
            Tema(
                "3.19",
                "📤 Subtema 4: ¿Cómo se crea un AVD en Android Studio?",
                "📋 Pasos rápidos:\n1️⃣ Abre el AVD Manager desde Android Studio\n2️⃣ Elige un modelo base (Pixel 6, Nexus 7...)\n3️⃣ Selecciona una imagen del sistema Android\n4️⃣ Personaliza resolución, memoria, sensores, etc.\n5️⃣ Guarda y lanza el emulador 🚀\n\n💡 Tip: puedes crear varios AVDs para probar diferentes escenarios sin modificar tu código.",
                false
            ),
            Tema(
                "3.20",
                "⛔ Subtema 5: Limitaciones de un emulador",
                "⚠️ Consideraciones a tener en cuenta:\n• No siempre se comporta como el hardware real\n• Puede ser más lento que usar un celular físico 🐢\n• Algunos sensores como NFC o giroscopio no se emulan con precisión\n• Requiere bastantes recursos (RAM y CPU) del equipo 💻\n\nAun así, es una herramienta indispensable para desarrollo y testing profesional.",
                false
            )
        )
    }
    override fun onItemClick(tema: Tema) {
        // Navegación a DetalleTemaFragment para los temas
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetalleTemaFragment.newInstance(tema)) // <-- MODIFICADO AQUÍ
            .addToBackStack(null) // Esto asegura que al presionar "Atrás" desde un tema, vuelves al temario
            .commit()
    }
}