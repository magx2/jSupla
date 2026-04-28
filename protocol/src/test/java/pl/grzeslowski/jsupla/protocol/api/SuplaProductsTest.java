package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SuplaProductsTest {
    @Test
    void shouldResolveProductByManufacturerAndProductIds() {
        assertThat(SuplaProducts.findByIds(4, 9000))
                .hasValue(new SuplaProducts.ProductInfo("ZAMEL", "ZAMEL mSLW-01"));
        assertThat(SuplaProducts.describe(4, 9000)).isEqualTo("ZAMEL mSLW-01");
    }

    @Test
    void shouldUseManufacturerAndProductIdAsKey() {
        assertThat(SuplaProducts.findByIds(0, 9000)).isEmpty();
    }

    @Test
    void shouldNotResolveLegacyUnknownProductIds() {
        assertThat(SuplaProducts.findByIds(0, 0)).isEmpty();
    }

    @Test
    void shouldExposeCatalogBrandName() {
        assertThat(SuplaProducts.findByIds(7, 3))
                .hasValue(new SuplaProducts.ProductInfo("VARILIGHT", "VARILIGHT SMART SOCKET"));
    }

    @Test
    void shouldDescribeUnknownProduct() {
        assertThat(SuplaProducts.describe(4, 9001))
                .isEqualTo("Unknown product (manufacturerId=4, productId=9001)");
    }
}
