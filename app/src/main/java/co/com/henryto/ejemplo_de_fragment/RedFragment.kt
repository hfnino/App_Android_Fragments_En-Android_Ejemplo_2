package co.com.henryto.ejemplo_de_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class RedFragment : Fragment() { // Un fragment, extiende de la clase superior Fragment() y debido a ello
    // es posible sobreescribir todos los metodos asociados al ciclo de vida de un Fragment, por lo que
    // podemos modificar su comportamiento para que haga lo que necesitemos.

    // En https://cursokotlin.com/capitulo-22-fragments-en-kotlin/ Se explica claramente como funciona
    // el ciclo de vida.

    private var listener: OnFragmentActionsListener? = null // creamos la variable listener, que es de
    // tipo OnFragmentActionsListener que es la inteface

    override fun onAttach(context: Context) { // La función onAttach hace parte del ciclo de vida de un
        //Fragment y se ejecuta automaticamente antes de la funcíón onCreateView. Esto nos ayuda a comprobar
        // de una manera obligada el contenido de la variable listener que creamos anterirmente antes de
        //ejecutar cualquier acción
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
        //La primera línea llama a la función super(), que no es más que la forma que le decimos al sistema
        // de que, aunque queramos que nuestra función onAttach haga más cosas, ejecute el código que
        // tenga que ejecutarse que esta asociado a la función onAttach, es decir, que vamos a sobreescribir
        // este emtodo para añadir cosas pero que no deje de hacer lo que haría ese método normalmente si no
        // lo hubieramos // sobreescrito.

        // el if(), comprobará si el contexto que llega a la función onAttach(), tiene implementada la interfaz
        // OnFragmentActionsListener que hemos creado. En este caso el contexto será de MainActivity, pues es
        // esta clase la que crea el fragment. Para finalizar igualamos el listener que hemos declarado en la parte
        // superior de la clase al contexto.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_red, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnPlus1: Button = view.findViewById(R.id.btnPlus1)
        btnPlus1.setOnClickListener { listener?.onClickFragmentButton() } //creamos un escucha cuando se
        //haga clic en el boton btnPlus1 que cuando sucede se ejecutara el metodo onClickFragmentButton()
        // de la variable listener. Como la variable listener es de tipo interfaz OnFragmentActionsListener
        // y su metodo abstracto onClickFragmentButton esta implementado en MainActivity, etonces se ejecutara
        // dicho codigo, que para este ejercicio es un Toast (vea el metodo override fun onClickFragmentButton()
        // que esta implementado en MainActivity)..... Hemos hecho que al hacer clic en el botón, este llame
        // a la función onClickFragmentButton() de la interfaz, que hará que nuestra MaiAnactivity lance el toast.
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        //La función onDetach hace parte del ciclo de vida de un Fragment y se ejecuta automaticamente
        // cuando se destruye el fragment y su objetvo es desligar la activity del fragment.
        // Cuando la función onDetach() sea llamada, volveremos a hacer null el listener para asegurarnos
        // de que no haya algún error por falta de comunicación. Ahora si tenemos funcionando nuestro listener.
    }
}