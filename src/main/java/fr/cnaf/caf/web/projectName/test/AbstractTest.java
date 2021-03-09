package fr.cnaf.caf.web.projectName.test;


import cnaf.autom.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.squashtest.ta.galaxia.tf.param.service.TFParamService;
import utils.OutilsProjet;
import utils.QAToolsInstantiation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * La classe abstractTest regroupe les éléments communs à chaque test :
 * ---> [before] l'initilisation des paramètres de test (env., URL départ, navigateur, timeout), du webdriver et des logs
 * ---> [after] la fermeture du webdriver et la sauvegarde des logs
 */
public abstract class AbstractTest extends QAToolsInstantiation {

    // Objets communs à tous les tests (ne pouvant être factorisés dans QAToolsInstantiation)
    protected static WebDriver driver;
    protected WebDriverWait wait;
    protected Assertions assertion;
    protected final OutilsProjet outilsProjet = new OutilsProjet();

    // Durée en secondes du timeout : implicite (implicitlyWait) ou explicite (Webdriver wait)
    protected int implicitWaitingTime = 20;
    protected int explicitWaitingTime = 20;

    // Paramètres des tests issus du connect.properties
    protected String environnement;
    protected static String navigateur;
    protected static String urlStart;


    public AbstractTest() {

        LOGGER.info(" ---------- Récupération des parametres du test via le connect.properties : ---------- ");
        Properties prop = new Properties();
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("connect.properties");
        try {
            prop.load(input);
            // Récupération de l'environnement
            environnement = prop.getProperty("environnement");
            environnement = TFParamService.getInstance().getParam("IT_CUF_environnement", environnement);
            LOGGER.info("-----> Environnement utilisé pour ce test : " + environnement);
            // Récupération de l'url
            urlStart = prop.getProperty("url" + environnement);
            LOGGER.info("-----> URL de départ pour cet environnement : " + urlStart);
            // Récupération du navigateur
            navigateur = prop.getProperty("navigateur");
            navigateur = TFParamService.getInstance().getParam("IT_CUF_navigateur", navigateur);
            LOGGER.info("-----> Navigateur utilisé pour ce test : " + navigateur);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("---------- Configuration et initialisation du Webdriver ----------");
        File file = null;
        if (navigateur.equalsIgnoreCase("Chrome")) {
            try {
                file = resourcesHandler.getFileAsResource("driver/chromedriver.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            ChromeOptions options = new ChromeOptions();
            options.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
            driver = new ChromeDriver(options);
        } else if (navigateur.equalsIgnoreCase("Firefox")) {
            try {
                file = resourcesHandler.getFileAsResource("driver/geckodriver.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
            ProfilesIni profileIni = new ProfilesIni();
            FirefoxProfile profile = profileIni.getProfile("Selenium");
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWaitingTime, TimeUnit.SECONDS);
        driver.get(urlStart);

        // Instanciation du webdriver wait et des assertions QA TOOLS :
        wait = new WebDriverWait(driver, explicitWaitingTime);
        assertion = new Assertions(driver, this.className, LOGGER, snapshotsDirectory);
    }

    @AfterEach
    public void close() {
        seleniumTools.safeCloseDriver(driver);
        saveAndCleanLogFiles();
    }
}
