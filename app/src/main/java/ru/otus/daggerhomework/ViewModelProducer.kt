package ru.otus.daggerhomework

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.otus.daggerhomework.di.ActivityContext
import javax.inject.Inject


class ViewModelProducer @Inject constructor(
    private val colorGenerator: ColorGenerator,
    @ActivityContext private val context: Context,
    private val colorsDataStore: ColorsDataStoreMutable,
) {

    suspend fun generateColor() {
        if (context !is FragmentActivity) throw RuntimeException("Здесь нужен контекст активити")
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
        colorsDataStore.sent(colorGenerator.generateColor())
        println("ViewModelProducer generateColor colorsDataStore $colorsDataStore")
    }
}


open class ViewModelProducerFactory @Inject constructor(
    private val colorGenerator: ColorGenerator,
    @ActivityContext private val context: Context,
    private val colorsDataStore: ColorsDataStoreMutable,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelProducer::class.java))
            return ViewModelProducer(
                colorGenerator,
                context,
                colorsDataStore,
            ) as T
        else throw IllegalArgumentException()
    }
}