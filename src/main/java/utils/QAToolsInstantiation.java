package utils;

import cnaf.autom.*;

/**
 * La classe QAToolsInstanciation est la classe mère de AbstractTest.java, AbstractBlockPage.java, OutilsProjet.java
 */
public class QAToolsInstantiation extends Logging {


    // Constantes projet pour QA Tools  communes à AbstractBlockPage, AbstractTest, OutilsProjet
    protected final static String snapshotsDirectory = "snapshots/";

    // Instanciation des classes de QA TOOLS communes à AbstractBlockPage, AbstractTest, OutilsProjet.
    // ATTENTION : l'objet WebDriver et les classes Assertions et OutilsProjets doivent être instanciés dans les classes filles.
    protected final SeleniumTools seleniumTools = new SeleniumTools(LOGGER);
    protected final StringHandler stringHandler = new StringHandler(LOGGER);
    protected final ResourcesHandler resourcesHandler = new ResourcesHandler(LOGGER);
    protected final DateHandler dateHandler = new DateHandler(LOGGER);
    protected final NumericHandler numericHandler = new NumericHandler(LOGGER);

    public QAToolsInstantiation(){
    }
}
