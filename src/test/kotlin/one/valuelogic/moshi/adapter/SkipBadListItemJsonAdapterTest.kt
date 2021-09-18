package one.valuelogic.moshi.adapter

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
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
                ),
                employees = emptyList()
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
                ),
                employees = emptyList()
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
                products = emptyList(),
                employees = emptyList()
            )
        )
    }

    @Test
    fun `SHOULD return store with all employees WHEN all items are valid`() {
        val json: String = loadFromResources("all_good_employees.json", javaClass.classLoader)
        val store: Store? = adapter.fromJson(json)
        assertThat(store).isEqualTo(
            Store(
                name = "My Store",
                products = emptyList(),
                employees = listOf(
                    Employee(
                        name = "John",
                        id = 123
                    ),
                    Employee(
                        name = "Jane",
                        id = 456
                    )
                )
            )
        )
    }

    @Test(expected = JsonDataException::class)
    fun `SHOULD throw exception WHEN some employee items are invalid`() {
        val json = loadFromResources("some_bad_employees.json", javaClass.classLoader)
        adapter.fromJson(json)
    }

    @Test(expected = JsonDataException::class)
    fun `SHOULD throw exception WHEN all employee items are invalid`() {
        val json = loadFromResources("all_bad_employees.json", javaClass.classLoader)
        adapter.fromJson(json)
    }
}