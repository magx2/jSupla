package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SuplaProductsTest {
    @Test
    void shouldResolveProductByManufacturerAndProductIds() {
        assertThat(SuplaProducts.findByIds(4, 9000))
                .hasValueSatisfying(
                        productInfo -> {
                            assertThat(productInfo.manufacturer()).isEqualTo("ZAMEL");
                            assertThat(productInfo.name()).isEqualTo("ZAMEL mSLW-01");
                        });
        assertThat(SuplaProducts.describe(4, 9000)).isEqualTo("ZAMEL mSLW-01");
    }

    @Test
    void shouldExposeFirmwareUpdateInfo() {
        assertThat(SuplaProducts.findByIds(4, 9000))
                .hasValueSatisfying(
                        productInfo -> {
                            assertThat(productInfo.updatesCount()).isPositive();
                            assertThat(productInfo.latestReleaseAt()).isNotBlank();
                            assertThat(productInfo.latestVersion()).isNotBlank();
                        });
        assertThat(
                        SuplaProducts.SUPLA_PRODUCTS.values().stream()
                                .anyMatch(
                                        productInfo ->
                                                productInfo.latestDescription() != null
                                                        && !productInfo
                                                                .latestDescription()
                                                                .isBlank()))
                .isTrue();
        assertThat(
                        SuplaProducts.SUPLA_PRODUCTS.values().stream()
                                .anyMatch(
                                        productInfo ->
                                                productInfo.productUrl() != null
                                                        && !productInfo.productUrl().isBlank()))
                .isTrue();
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
                .hasValueSatisfying(
                        productInfo -> {
                            assertThat(productInfo.manufacturer()).isEqualTo("VARILIGHT");
                            assertThat(productInfo.name()).isEqualTo("VARILIGHT SMART SOCKET");
                        });
    }

    @Test
    void shouldDescribeUnknownProduct() {
        assertThat(SuplaProducts.describe(4, 9001))
                .isEqualTo("Unknown product (manufacturerId=4, productId=9001)");
    }
}
