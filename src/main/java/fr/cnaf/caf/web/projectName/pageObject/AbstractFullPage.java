package fr.cnaf.caf.web.projectName.pageObject;

import fr.cnaf.caf.web.projectName.pageObject.composants.AbstractBlocPage;
import fr.cnaf.caf.web.projectName.pageObject.composants.BurgerPage;
import fr.cnaf.caf.web.projectName.pageObject.composants.HeaderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.OutilsProjet;

/**
 * Page abstraite regroupant les éléments du burger, du header et fournissant le WebDriver par héritage.
 * C'est la classe mère de toutes les pages du pageObject.
 */
public abstract class AbstractFullPage extends AbstractBlocPage {

    private BurgerPage burger;
    private HeaderPage header;
    protected final OutilsProjet outilsProjet = new OutilsProjet();

    public BurgerPage getBurger() {
        return burger;
    }

    public HeaderPage getHeader() {
        return header;
    }

    public AbstractFullPage(WebDriver driver) {
        super(driver);
        header = new HeaderPage(driver);
        burger = new BurgerPage(driver);
        PageFactory.initElements(driver, this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////      ELEMENTS     //////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////      METHODES     //////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

}
