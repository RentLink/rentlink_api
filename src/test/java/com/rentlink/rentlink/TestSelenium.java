package com.rentlink.rentlink;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


public class TestSelenium {

//    private WebDriver driver;
//
//    @BeforeAll
//    public static void init() {
//        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
//    }
//
//    @BeforeEach
//    public void setup() {
//        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--headless");
//        Map<String, Object> chromePrefs = new HashMap<String, Object>();
//        chromePrefs.put("download.default_directory", "/tmp/rentlink/");
//        chromePrefs.put("download.prompt_for_download", false);
//        chromePrefs.put("download.directory_upgrade", true);
//        chromePrefs.put("plugins.always_open_pdf_externally", true);
//        options.setExperimentalOption("prefs", chromePrefs);
//        driver = new ChromeDriver(options);
//    }
//
//    @AfterEach
//    public void teardown() throws InterruptedException {
//        if (driver != null) {
//            Thread.sleep(1000);
//            driver.quit();
//        }
//    }
//
//    @Test
//    public void verifyGitHubTitle() throws InterruptedException, TesseractException, IOException {
//        driver.get("https://www.orange.pl/twojekonto/zaloguj");
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//
//        driver.manage().window().maximize();
//
//        // Enter your login email id
//        driver.findElement(By.id("customer_login"))
//                .sendKeys("konradszatan11@gmail.com");
//
//        driver.findElement(By.id("verifyLoginButton"))
//                .click();
//
//        new WebDriverWait(driver, Duration.of(30, ChronoUnit.SECONDS))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
//
//        driver.findElement(By.id("password"))
//                .sendKeys("");
//
//        driver.findElement(By.id("loginButton"))
//                .click();
//
//        driver.findElement(By.xpath("//*[@id=\"menu-header\"]/div[2]")).click();
//
//        new WebDriverWait(driver, Duration.of(30, ChronoUnit.SECONDS))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("prepareInvoiceDownload_F009097600101123_2")));
//
//        driver.findElement(By.id("prepareInvoiceDownload_F009097600101123_2")).click();
//
//        new WebDriverWait(driver, Duration.of(30, ChronoUnit.SECONDS))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainAttachments\"]/label")));
//
//        driver.findElement(By.xpath("//*[@id=\"mainAttachments\"]/label")).click();
//
//        driver.findElement(By.id("downloadSettingsAction")).click();
//
//        new WebDriverWait(driver, Duration.of(30, ChronoUnit.SECONDS))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"billing_selectAttachmentsV2\"]/div/div/div/button")));
//
//
//        driver.findElement(By.xpath("//*[@id=\"billing_selectAttachmentsV2\"]/div/div/div/button")).click();
//
//        System.setProperty("jna.library.path", "/opt/homebrew/lib/");
//
//        Thread.sleep(3000);
//
//        PDDocument document = Loader.loadPDF(new File("/tmp/rentlink/F0090976001_011_23.pdf"));
//        PDFRenderer pdfRenderer = new PDFRenderer(document);
//        for (int page = 0; page < document.getNumberOfPages(); ++page) {
//            BufferedImage bim = pdfRenderer.renderImageWithDPI(
//                    page, 300, ImageType.RGB);
//            String png = String.format("/tmp/rentlink/pdf-%d.%s", page + 1, "png");
//            ImageIOUtil.writeImage(
//                    bim, png, 300);
//
//
//            File image = new File(png);
//            Tesseract tesseract = new Tesseract();
//            tesseract.setDatapath("/opt/homebrew/share/tessdata");
//            tesseract.setLanguage("pol");
//            tesseract.setPageSegMode(1);
//            tesseract.setOcrEngineMode(1);
//            String result = tesseract.doOCR(image);
//
//            System.out.println("STRONA " + page + 1);
//            System.out.println(result);
//
//
//        }
//        document.close();
//
//    }
}
