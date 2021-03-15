package fr.cnaf.caf.web.projectName.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.squashtest.ta.galaxia.squash.tf.galaxia.annotations.TFMetadata;


public class TestExample extends AbstractTest {

    // Variables

    // Pages

    @BeforeEach
    public void BeforeEachMethod() {
    }


    @Test
//    @TFMetadata(key = "linked-TC", value = {"TO-BE-COMPLETED"})
    public void run() throws Throwable {
        LOGGER.info("---------- Debut du test ----------");
        LOGGER.info("---------- Etape 1 : démonstration ----------");
        assertion.verifyEquals("Bienvenue sur Caf.fr | caf.fr",driver.getTitle(),"Le titre de la page ne correspond pas.");
        LOGGER.info("---------- Fin du test ----------");
    }
}