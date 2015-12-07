package br.com.findzipcode.test.support;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;

/**
 * Classe de suporte para testes com FixtureFactory
 * Created by Vinicius on 04/12/15.
 */
public abstract class TestFixtureSupport extends TestSupport {

    /**
     * Before test class.
     */
    @BeforeClass
    public static void beforeTestClass() {
        FixtureFactoryLoader.loadTemplates("br.com.findzipcode.template.loader");
    }

}