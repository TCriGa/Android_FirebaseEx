package br.com.zup.listacompras.produto

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.zup.listacompras.ERROR_MESSAGE_DETAIL
import br.com.zup.listacompras.ERROR_MESSAGE_NAME
import br.com.zup.listacompras.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductFragmentTest {

    private lateinit var stringToName: String
    private lateinit var stringToDetail: String

    @Before
    fun initValidString() {
        stringToName = "Product"
        stringToDetail = "Detail Product"
    }

    @Test
    fun checkValidationProdutoFragment_errorMessageName() {
        launchFragmentInContainer<ProdutoFragment>()
        onView(withId(R.id.etNomeProduto))
            .perform(typeText(stringToName))
        closeSoftKeyboard()
        onView(withId(R.id.bvAdicionar))
            .perform(click())
        onView(hasErrorText(ERROR_MESSAGE_DETAIL)).check(
            matches(withId(R.id.etDetalheProduto))
        )

    }


    @Test
    fun checkValidationProdutoFragmento_errorMessageDetalhe() {
        launchFragmentInContainer<ProdutoFragment>()

        onView(withId(R.id.etDetalheProduto))
            .perform(typeText(stringToDetail))
        closeSoftKeyboard()
        onView(withId(R.id.bvAdicionar))
            .perform(click())
        onView(hasErrorText(ERROR_MESSAGE_NAME)).check(
            matches(withId(R.id.etNomeProduto))
        )

    }

    @Test
    fun checkValidationProdutoFragment_errorMessageCampos() {
        launchFragmentInContainer<ProdutoFragment>()
        onView(withId(R.id.etDetalheProduto))
            .perform(clearText())
        onView(withId(R.id.etNomeProduto))
            .perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.bvAdicionar))
            .perform(click())
        onView(hasErrorText(ERROR_MESSAGE_NAME)).check(
            matches(withId(R.id.etNomeProduto))
        )
        onView(hasErrorText(ERROR_MESSAGE_DETAIL)).check(
            matches(withId(R.id.etDetalheProduto))
        )

    }
}