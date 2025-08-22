package org.example.learning_spring.IOInteractionClasses;

import org.apache.commons.lang3.StringUtils;
import org.example.learning_spring.Services.BrownDust2CharService;
import org.example.learning_spring.TableClasses.BrownDust2.BrownDust2Char;
import org.example.learning_spring.TableClasses.BrownDust2.Costumes.CharCostume;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.time.Duration;
import java.util.*;


public class WebScraper {



    public static List<BrownDust2Char> scrape(String path) throws IOException {
        try{
            WebDriver driver = new FirefoxDriver();
            driver.get(path);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            //BYPASSING CONSENT SCREEN
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement consentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fc-footer-buttons button:nth-child(1)")));
            consentBox.click();

            //SCRAPING CHARACTERS
            List<WebElement> webElementList = driver.findElements(By.cssSelector("#app section.min-h-screen main.w-full section.w-full div.flex-col section.space-y-3 section.space-y-5 article.gap-2"));
            System.out.println(webElementList.size());
            List<BrownDust2Char> chars = new ArrayList<>();
            webElementList.forEach(webElement -> {
                List<WebElement> articleElements = webElement.findElements(By.cssSelector("article.grid-container button"));
                WebDriverWait clickWait = new WebDriverWait(driver, Duration.ofSeconds(1));
                //RARITY
                char rarityNumber = (webElement.findElement(By.cssSelector("div.gap-4"))).getText().charAt(0);
                String rarity = rarityNumber+"-star";
                System.out.println(rarity);
                articleElements.forEach(articleButton -> {
                    articleButton.click();
                    Set<CharCostume> charCostumeSet = new HashSet<>();
                    clickWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".outer")));
                    WebElement fullStats =  driver.findElement(By.cssSelector("#app .outer .inner article:nth-child(2)"));

                    //HEADER
                    WebElement characterHeader = fullStats.findElement(By.cssSelector("section header.pl-3 .items-center"));
                    WebElement charNameCostume = characterHeader.findElement(By.cssSelector("span"));
                    WebElement charElement = characterHeader.findElement(By.cssSelector("img.w-8"));
                    //FIRST SECTION
                    WebElement charStats = fullStats.findElement(By.cssSelector("section section.text-sm div.shadow-inner div"));
                    //SECOND SECTION
                    WebElement charStats2 = fullStats.findElement(By.cssSelector("section section:nth-child(2)"));
                    WebElement atkType = charStats2.findElement(By.cssSelector("section.gap-2 div.p-2  div.px-2 div:nth-child(3) img"));

                    String charName = charNameCostume.getText().split("-")[0].trim();
                    int hp = Integer.parseInt((charStats.findElement(By.cssSelector("div:nth-child(1)")).getText()).split(" ")[1]);
                    String defString =charStats.findElement(By.cssSelector("div:nth-child(2)")).getText().split(" ")[1];
                    int def = Integer.parseInt(defString.replace("%",""));
                    String mResString =charStats.findElement(By.cssSelector("div:nth-child(3)")).getText().split(" ")[1];
                    int mRes = Integer.parseInt((mResString.replace("%","")));
                    String atkString = charStats.findElement(By.cssSelector("div:nth-child(4)")).getText().split(" ")[1];
                    int atk = Integer.parseInt(atkString.replace("%",""));
                    String critRateString =charStats.findElement(By.cssSelector("div:nth-child(5)")).getText().split(" ")[1];
                    int  critRate = Integer.parseInt(critRateString.replace("%",""));
                    String critDmgString =charStats.findElement(By.cssSelector("div:nth-child(6)")).getText().split(" ")[1];
                    int  critDmg = Integer.parseInt(critDmgString.replace("%",""));

                    int elementN = Objects.requireNonNull(charElement.getAttribute("src")).split("/").length;
                    String elementPngName = Objects.requireNonNull(charElement.getAttribute("src")).split("/")[elementN-1];
                    String element = elementPngName.substring(0,elementPngName.indexOf(".")).trim();

                    int atkN = Objects.requireNonNull(atkType.getAttribute("src")).split("/").length;
                    String atkMethodPngName = (Objects.requireNonNull(atkType.getAttribute("src")).split("/")[atkN-1]).split("_")[1];
                    String damageType = atkMethodPngName.substring(0,atkMethodPngName.indexOf(".")).trim();

                    File newCharDirectory = createDirectory("src/main/resources/static/GachaGameImages/BrownDust2",charName);

                    System.out.println(charName);
                    System.out.println(hp);
                    System.out.println(def);
                    System.out.println(mRes);
                    System.out.println(atk);
                    System.out.println(critRate);
                    System.out.println(critDmg);
                    List<WebElement> costumeList = fullStats.findElements(By.cssSelector("div.max-w-full div.flex-row div"));
                    for(WebElement costume : costumeList){
                        costume.click();
                        String costumeName = charNameCostume.getText().split("-")[1].trim();
                        File costumeDir = createDirectory(String.valueOf(newCharDirectory), costumeName);

                        WebElement costumeAttr =charStats2.findElement(By.cssSelector("section.overflow-hidden div.py-2"));
                        int coolDown = Integer.parseInt(costumeAttr.getText().split("\n")[3]);
                        int chain = Integer.parseInt(costumeAttr.getText().split("\n")[5]);
                        System.out.println(costumeName+": cd " + coolDown + " chain " + chain);

                        WebElement tileRange = charStats2.findElement(By.cssSelector("section.gap-2 div.divide-gray-500\\/30 div.px-2 div:nth-child(1) img"));
                        String tileRangeImgLink = tileRange.getAttribute("src");
                        File tileRangeDir = createDirectory(String.valueOf(costumeDir), "TileRange");
                        downloadImg(tileRangeImgLink,tileRangeDir,costumeName+"TileRange");

                        WebElement atkPositionElement = charStats2.findElement(By.cssSelector("section.gap-2 div.divide-gray-500\\/30 div.px-2 div:nth-child(2)"));
                        String atkPosition = atkPositionElement.getText();

                        WebElement pushDirectionElement = charStats2.findElement(By.cssSelector("section.gap-2 div.divide-gray-500\\/30 div.px-2 div:nth-child(4) img"));
                        String pushDirectionLink = pushDirectionElement.getAttribute("src")
                                .split("/")[pushDirectionElement.getAttribute("src")
                                .split("/").length-1];
                        String pushDirection = pushDirectionLink.split("_")[1];
                        System.out.println(pushDirection);


                        WebElement costumeImg = charStats2.findElement(By.cssSelector("section.gap-2 div.bg-gradient-to-t"));
                        costumeImg.click();
                        clickWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bg-black\\/90")));
                        WebElement costumeFullImg = driver.findElement(By.cssSelector("#app div.top-0 section.w-full div div"));
                        String fullImgLink = costumeFullImg.getAttribute("style");
                        String link = StringUtils.substringBetween(fullImgLink,"(", ")");
                        assert link != null;
                        link = link.replace("\"", "");
                        File fullImgDir = createDirectory(String.valueOf(costumeDir), "FullImg");
                        downloadImg(link, fullImgDir,costumeName);
                        WebElement xButton =  clickWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app div.top-0 section.w-full section.justify-end div button:nth-child(2)")));
                        xButton.click();
                        clickWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".bg-black\\/90")));


                        WebElement chibiImage = costume.findElement(By.cssSelector("img"));
                        String imgSrc = chibiImage.getAttribute("src");
                        File chibiDir = createDirectory(String.valueOf(costumeDir), "chibi");
                        downloadImg(imgSrc, chibiDir,costumeName);
                    }
                    BrownDust2Char brownDust2Char = new BrownDust2Char(charName, atk, hp,def,mRes,critRate,critDmg, element,damageType, rarity);
                    chars.add(brownDust2Char);
                    WebElement xButton =  clickWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app .outer .inner article .px-2")));
                    xButton.click();
                    clickWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".outer")));
                });
            });
            return chars;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static File createDirectory(String path, String name) {
        File newCharDirectory = new File(path, name);
        if (!newCharDirectory.exists()) {
            boolean created = newCharDirectory.mkdirs();
            if (!created) {
                System.out.println("Error creating directory");
            }
        }
        return newCharDirectory;
    }

    public static void downloadImg(String link, File targetDir, String name){
        try{
            assert link != null;
            URL url = new URL(link);
            BufferedImage img = ImageIO.read(url);
            ImageIO.write(img, "png", new File(targetDir + File.separator + name+".png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        List<BrownDust2Char> chars = scrape("https://browndust2-wiki.souseha.com/en/characters");
    }
}
