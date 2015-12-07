package br.com.findzipcode.service;

import br.com.findzipcode.test.support.TestFixtureSupport;
import br.com.findzipcode.test.support.TestSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vinicius on 03/12/15.
 */
@RunWith(JUnit4.class)
public class NearestAddressServiceTest extends  TestFixtureSupport {

    private List<String> expectedNearestZipcodes;

    @Override
    public void setUp() {
        expectedNearestZipcodes = Arrays.asList("08556330", "08556300", "08556000", "08550000", "08500000", "08000000", "00000000");
    }

    @Test
    public void testFindZipcodeInDepth() {
        NearestAddressServiceImpl.NearestZipcodes nearestZipcodes = new NearestAddressServiceImpl().new NearestZipcodes("08556333");

        assertThat(nearestZipcodes.getZipcodes(), equalTo(expectedNearestZipcodes));
    }

}
