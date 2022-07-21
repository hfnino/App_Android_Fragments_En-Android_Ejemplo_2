package co.com.henryto.ejemplo_de_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.com.henryto.ejemplo_de_fragment.databinding.ActivityMainBinding

//Bibliografia: https://cursokotlin.com/capitulo-22-fragments-en-kotlin/

//La idea de esta es que en nuestra activity principal añadiremos un fragment mediante
// código (por ejemplo al pulsar un botón) o incluso reemplazarlo y cambiarlo dependiendo
// de la selección del usuario.

class MainActivity : AppCompatActivity(), OnFragmentActionsListener {

    private lateinit var binding: ActivityMainBinding //Implementacion de view bindig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //Implementacion de view bindig

        binding.btnRed1.setOnClickListener { loadFragment(RedFragment()) }
        //A parte de añadir el listener estamos creando un objeto de tipo RedFragment
        binding.btnBlue1.setOnClickListener { loadFragment(BlueFragment()) }
        //A parte de añadir el listener estamos creando un objeto de tipo BlueFragment
        binding.btnRed2.setOnClickListener { replaceFragment(RedFragment()) }
        //A parte de añadir el listener estamos creando un objeto de tipo RedFragment
        binding.btnBlue2.setOnClickListener { replaceFragment(BlueFragment()) }
        //A parte de añadir el listener estamos creando un objeto de tipo BlueFragment
    }

    private fun loadFragment(fragment: Fragment) {
        //Esta función recibe un fragment que será el que queramos cargar en nuestro FrameLayout del
        // xml. Primero creamos el objeto fragmentTransaction a partir de la clase supportFragmentManager
        // que es el que se encarga de gestionar los fragments y posee la mayoría de los métodos que
        // necesitamos en la gestión.

        //Para cargar un fragment haremos siempre lo mismo, necesitamos avisar al sistema de que vamos
        // a hacer un cambio, añadirlo y guardar dicho cambio. Eso es exactamente lo que hace esta
        // función. Empezamos avisando del cambio con beginTransaction(), luego llamamos a la
        // función add() del fragmentTransaction para relacionar el fragment correspondiente y luego
        // cerramos con commit().

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment){
        //Con la función loadFragmet, podemos añadir (cargar) fragments, pero si nos fijamos, cada
        // vez que añadimos uno, el anterior muere. Ahora pensemos que estamos realizando un flujo
        // de trabajo y son dos pantallas, en la primera el usuario tiene que introducir sus datos
        // personales y en la segunda su dirección. Cuando trabajamos con fragments tenemos la
        // posibilidad de emular el funcionamiento de las activities, es decir, podemos pulsar atrás
        // (tanto en la toolbar cómo el botón del móvil) y que vuelva al anterior.

        //Teniendo en cuenta lo anterior, la función replaceFragment ya no añade fragments, sino que
        // los reemplaza. Esta vez el objeto fragmentTransaction no llama a la función add(), sino a
        // replace(). Además, antes de hacer el commit(), hemos añadido addToBackStack(null), cono lo
        // que le decimos al sistema que nos permita ir para atrás.

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClickFragmentButton() {
        Toast.makeText(this, "El botón ha sido pulsado", Toast.LENGTH_SHORT).show()
    }

}