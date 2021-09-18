package one.valuelogic.moshi.adapter

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Test

class SkipBadListItemJsonAdapterTest {

    private val adapter: JsonAdapter<Store> = Moshi
        .Builder()
        .add(SkipBadListItemJsonAdapter.INSTANCE)
        .build()
        .adapter(Store::class.java)


    @Test
    fun `SHOULD return store with all products WHEN all items are valid`() {
        val json: String = loadFromResources("all_good_products.json", javaClass.classLoader)
        val store: Store? = adapter.fromJson(json)
        assertThat(store).isEqualTo(
            Store(
                name = "My Store",
                products = listOf(
                    Product(
                        name = "Apple",
                        price = 12.34
                    ),
                    Product(
                        name = "Orange",
                        price = 56.78
                    )
                )
            )
        )
    }

    @Test
    fun `SHOULD return store with some products WHEN some items are invalid`() {
        val json = loadFromResources("some_bad_products.json", javaClass.classLoader)
        val store: Store? = adapter.fromJson(json)
        assertThat(store).isEqualTo(
            Store(
                name = "My Store",
                products = listOf(
                    Product(
                        name = "Apple",
                        price = 12.34
                    )
                )
            )
        )
    }

    @Test
    fun `SHOULD return store with no products WHEN all items are invalid`() {
        val json = loadFromResources("all_bad_products.json", javaClass.classLoader)
        val store: Store? = adapter.fromJson(json)
        assertThat(store).isEqualTo(
            Store(
                name = "My Store",
                products = emptyList()
            )
        )
    }
}