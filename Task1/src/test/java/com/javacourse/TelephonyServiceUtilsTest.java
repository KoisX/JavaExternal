package com.javacourse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TelephonyServiceUtilsTest {

    TelephonyServiceUtils tsc;

    @Before
    public void setUp() throws Exception {
        tsc = new TelephonyServiceUtils();
    }

    @Test
    public void getTotalNumberOfClients() {
        tsc.clearClientList();
        assertEquals(tsc.getTotalNumberOfClients(), 0);

        tsc.addClient(new Client("name", BusinessTariff.INSTANCE));
        assertEquals(tsc.getTotalNumberOfClients(), 1);
    }

    @Test
    public void getSortedTariffListByPrice() {
        List<Tariff> expected = Arrays.asList(
          FeedInTariff.INSTANCE,
          GeneralTariff.INSTANCE,
          BusinessTariff.INSTANCE
        );
        List actual = tsc.getSortedTariffListByPrice();
        assertThat(expected,  is(actual));
    }

    @Test
    public void getTariffsByPriceLimits() {
        List<Tariff> actual = tsc.getTariffsByPriceLimits(10,1000);
        assertThat(actual, containsInAnyOrder(BusinessTariff.INSTANCE,
                GeneralTariff.INSTANCE,
                FeedInTariff.INSTANCE));
    }
}