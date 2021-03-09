package fr.cnaf.caf.web.projectName.pageObject.composants;

import org.openqa.selenium.WebDriver;
import utils.OutilsProjet;
import utils.QAToolsInstantiation;

/**
 * Classe mère du pageObject. Instancie en particulier le WebDriver.
 */
public abstract class AbstractBlocPage extends QAToolsInstantiation {

    protected final WebDriver driver;
    protected OutilsProjet outilsProjet = new OutilsProjet();


    public AbstractBlocPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
