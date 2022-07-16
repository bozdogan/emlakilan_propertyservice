package org.bozdgn.propertyservice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class PropertyserviceApplicationTests {

    @Test
    void itMultiplies2by2() {
        int result = 2 * 2;
        int expected = 4;

        assertThat(result).isEqualTo(expected);
    }

}
