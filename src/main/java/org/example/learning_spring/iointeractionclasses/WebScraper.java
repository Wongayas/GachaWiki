package org.example.learning_spring.iointeractionclasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.example.learning_spring.entity.BrownDust2.BrownDust2Char;
import org.example.learning_spring.entity.BrownDust2.Costumes.CharCostume;
import org.example.learning_spring.entity.BrownDust2.Costumes.CostumeBond;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;


public class WebScraper {


    private static final Logger log = LoggerFactory.getLogger(WebScraper.class);

    public static List<BrownDust2Char> scrape(String path) throws IOException {
        try{
            //MAIN SITE
            WebDriver characterDriver = new FirefoxDriver();
            characterDriver.get(path);
            //ANIMATION SITE
//            WebDriver animationDriver = getWebDriver();
//            List<WebElement> charCostumes = animationDriver.findElements(By.cssSelector("#app div.bg-gray-900 div.flex div.sidebar-scroll:nth-child(2) div"));


            characterDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));//USED TO BE 4 SECONDS
            //BYPASSING CONSENT SCREEN
            WebDriverWait wait = new WebDriverWait(characterDriver, Duration.ofSeconds(10));
            WebElement consentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fc-footer-buttons button:nth-child(1)")));
            consentBox.click();

            //SCRAPING CHARACTERS
            List<WebElement> webElementList = characterDriver.findElements(By.cssSelector("#app section.min-h-screen main.w-full section.w-full div.flex-col section.space-y-3 section.space-y-5 article.gap-2"));
            List<BrownDust2Char> chars = new ArrayList<>();
            webElementList.forEach(webElement -> {
                List<WebElement> articleElements = webElement.findElements(By.cssSelector("article.grid-container button"));
                WebDriverWait clickWait = new WebDriverWait(characterDriver, Duration.ofSeconds(2));//USED TO BE 5 SECONDS
                //RARITY
                char rarityNumber = (webElement.findElement(By.cssSelector("div.gap-4"))).getText().charAt(0);
                String rarity = rarityNumber+"-star";
                System.out.println(rarity);
                for(WebElement articleButton : articleElements){
                    JavascriptExecutor js = (JavascriptExecutor) characterDriver;
                    js.executeScript("arguments[0].click();", articleButton);
                    clickWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".outer")));
                    WebElement fullStats =  characterDriver.findElement(By.cssSelector("#app .outer .inner article:nth-child(2)"));
                    //HEADER
                    WebElement characterHeader = fullStats.findElement(By.cssSelector("section header.pl-3 .items-center"));
                    WebElement charNameCostume = characterHeader.findElement(By.cssSelector("span"));
                    WebElement charElement = characterHeader.findElement(By.cssSelector("img.w-8"));
                    //FIRST SECTION
                    WebElement charStats = fullStats.findElement(By.cssSelector("section section.text-sm div.shadow-inner div"));
                    //SECOND SECTION
                    WebElement charStats2 = fullStats.findElement(By.cssSelector("section section:nth-child(2)"));
                    WebElement atkType = charStats2.findElement(By.cssSelector("section.gap-2 div.p-2  div.px-2 div:nth-child(3) img"));


                    BrownDust2Char brownDust2Char = new BrownDust2Char();
                    String charName = charNameCostume.getText().split("-")[0].trim();
                    brownDust2Char.setName(charName);
                    int hp = Integer.parseInt((charStats.findElement(By.cssSelector("div:nth-child(1)")).getText()).split(" ")[1]);
                    brownDust2Char.setHp(hp);
                    String defString =charStats.findElement(By.cssSelector("div:nth-child(2)")).getText().split(" ")[1];
                    int def = Integer.parseInt(defString.replace("%",""));
                    brownDust2Char.setDef(def);
                    String mResString =charStats.findElement(By.cssSelector("div:nth-child(3)")).getText().split(" ")[1];
                    int mRes = Integer.parseInt((mResString.replace("%","")));
                    brownDust2Char.setmRes(mRes);
                    String atkString = charStats.findElement(By.cssSelector("div:nth-child(4)")).getText().split(" ")[1];
                    int atk = Integer.parseInt(atkString.replace("%",""));
                    brownDust2Char.setAtk(atk);
                    String critRateString =charStats.findElement(By.cssSelector("div:nth-child(5)")).getText().split(" ")[1];
                    int  critRate = Integer.parseInt(critRateString.replace("%",""));
                    brownDust2Char.setCritRate(critRate);
                    String critDmgString =charStats.findElement(By.cssSelector("div:nth-child(6)")).getText().split(" ")[1];
                    int  critDmg = Integer.parseInt(critDmgString.replace("%",""));
                    brownDust2Char.setCritDmg(critDmg);

                    int elementN = Objects.requireNonNull(charElement.getAttribute("src")).split("/").length;
                    String elementPngName = Objects.requireNonNull(charElement.getAttribute("src")).split("/")[elementN-1];
                    String element = elementPngName.substring(0,elementPngName.indexOf(".")).trim();
                    brownDust2Char.setElement(element);

                    int atkN = Objects.requireNonNull(atkType.getAttribute("src")).split("/").length;
                    String atkMethodPngName = (Objects.requireNonNull(atkType.getAttribute("src")).split("/")[atkN-1]).split("_")[1];
                    String damageType = atkMethodPngName.substring(0,atkMethodPngName.indexOf(".")).trim();
                    brownDust2Char.setDamageType(damageType);
                    brownDust2Char.setRarity(rarity);

                    File newCharDirectory = createDirectory("src/main/resources/static/GachaGameImages/BrownDust2/Characters",charName);

                    System.out.println(charName);
                    System.out.println(hp);
                    System.out.println(def);
                    System.out.println(mRes);
                    System.out.println(atk);
                    System.out.println(critRate);
                    System.out.println(critDmg);
                    List<WebElement> costumeList = fullStats.findElements(By.cssSelector("div.max-w-full div.flex-row div"));
                    List<CharCostume> charCostumeSet = new ArrayList<>();
                    for(WebElement costume : costumeList){
                        costume.click();
                        String costumeName = charNameCostume.getText().split("-")[1].trim();
                        File costumeDir = createDirectory(String.valueOf(newCharDirectory), costumeName);

//                        assignAnimations(animationDriver,charCostumes,charName,costumeName);
                        File animationDir = createDirectory(String.valueOf(costumeDir), "animation");
                        File idleDir = createDirectory(String.valueOf(animationDir), "idle");
                        File motionDir = createDirectory(String.valueOf(animationDir), "motion");
                        if (!motionDir.exists()) {
                            throw new RuntimeException("Motion directory creation failed: " + motionDir.getAbsolutePath());
                        }
                        String idleAnimationFile = null;
                        String motionAnimationFile = null;
                        try {
                            //idleAnimationFile= String.valueOf(getDownloadedAnimationFile(animationDriver,idleDir,costumeName,"idle"));
                            idleAnimationFile= getDownloadedAnimationFile(idleDir,costumeName,"idle");
                            //motionAnimationFile = String.valueOf(getDownloadedAnimationFile(animationDriver,motionDir,costumeName,"motion"));
                            motionAnimationFile = getDownloadedAnimationFile(motionDir,costumeName,"motion");
                        } catch (Exception e){
                            log.error(e.getMessage());
                        }

                        WebElement costumeAttr =charStats2.findElement(By.cssSelector("section.overflow-hidden div.py-2"));
                        int coolDown = Integer.parseInt(costumeAttr.getText().split("\n")[3]);
                        int chain = Integer.parseInt(costumeAttr.getText().split("\n")[5]);
                        System.out.println(costumeName+": cd " + coolDown + " chain " + chain);

                        WebElement tileRange = charStats2.findElement(By.cssSelector("section.gap-2 div.divide-gray-500\\/30 div.px-2 div:nth-child(1) img"));
                        String tileRangeImgLink = tileRange.getAttribute("src");
                        File tileRangeDir = createDirectory(String.valueOf(costumeDir), "TileRange");
                        File tileRangeImgFile= getDownloadedImg(tileRangeImgLink,tileRangeDir,costumeName+"TileRange");

                        WebElement atkPositionElement = charStats2.findElement(By.cssSelector("section.gap-2 div.divide-gray-500\\/30 div.px-2 div:nth-child(2)"));
                        String atkPosition = atkPositionElement.getText();

                        WebElement pushDirectionElement = charStats2.findElement(By.cssSelector("section.gap-2 div.divide-gray-500\\/30 div.px-2 div:nth-child(4) img"));
                        String pushDirectionLink = pushDirectionElement.getAttribute("src")
                                .split("/")[pushDirectionElement.getAttribute("src")
                                .split("/").length-1];
                        String pushDirection = pushDirectionLink.split("_")[1].substring(0,pushDirectionLink.split("_")[1].indexOf("."));
                        System.out.println(pushDirection);


                        WebElement costumeImg = charStats2.findElement(By.cssSelector("section.gap-2 div.bg-gradient-to-t"));
                        costumeImg.click();
                        clickWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bg-black\\/90")));
                        WebElement costumeFullImg = characterDriver.findElement(By.cssSelector("#app div.top-0 section.w-full div div"));
                        String fullImgLink = costumeFullImg.getAttribute("style");
                        String link = StringUtils.substringBetween(fullImgLink,"(", ")");
                        assert link != null;
                        link = link.replace("\"", "");
                        File fullImgDir = createDirectory(String.valueOf(costumeDir), "FullImg");
                        File fullBodyImg = getDownloadedImg(link, fullImgDir,costumeName);
                        WebElement xButton =  clickWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app div.top-0 section.w-full section.justify-end div button:nth-child(2)")));
                        xButton.click();
                        clickWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".bg-black\\/90")));

                        Map<String, Double> stats = new HashMap<>();
                        List<WebElement> bondStats = charStats2.findElements(By.cssSelector("section.gap-2 div.divide-gray-500\\/30 div.space-y-1 div.gap-2:nth-child(2) ul li"));
                        for (WebElement bondStat : bondStats) {
                            String statName = bondStat.getText().split(":")[0].trim().replace(":","");
                            String statValue = bondStat.getText().split(":")[1].trim().replace("%","");
                            double truncatedStatValue = Math.floor(Double.parseDouble(statValue)*100)/100;
                            System.out.println(statName+": " + truncatedStatValue);
                            stats.put(statName, truncatedStatValue);
                        }

                        WebElement chibiImage = costume.findElement(By.cssSelector("img"));
                        String imgSrc = chibiImage.getAttribute("src");
                        File chibiDir = createDirectory(String.valueOf(costumeDir), "chibi");
                        File chibiImgFile = getDownloadedImg(imgSrc, chibiDir,costumeName);
                        CharCostume charCostume = new CharCostume(costumeName,coolDown,chain,pushDirection,String.valueOf(tileRangeImgFile)
                                        ,atkPosition,String.valueOf(chibiImgFile),String.valueOf(fullBodyImg),idleAnimationFile, motionAnimationFile, brownDust2Char);

                        CostumeBond costumeBond = new CostumeBond(charCostume, stats);
                        charCostume.setCostumeBond(costumeBond);

                        charCostumeSet.add(charCostume);
                    }
                    //BrownDust2Char brownDust2Char = new BrownDust2Char(charName, atk, hp,def,mRes,critRate,critDmg, element,damageType, rarity);
                    brownDust2Char.setCharCostumes(charCostumeSet);
                    chars.add(brownDust2Char);
                    WebElement xButton =  clickWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app .outer .inner article .px-2")));
                    xButton.click();
                    clickWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".outer")));
                }
            });
            return chars;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    private static WebDriver getWebDriver() {
        String downloadPath = "D:\\School\\Java\\Learning_Spring\\src\\main\\resources\\static\\GachaGameImages\\BrownDust2\\Animations";
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", downloadPath);
        profile.setPreference("browser.download.folderList", 2);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        WebDriver animationDriver = new FirefoxDriver(options);
        animationDriver.get("https://jelosus2.github.io/BD2-L2D-Viewer/?char=100101&anim=idle&skin=default&type=character");
        return animationDriver;
    }

    public static void assignAnimations(WebDriver driver, List<WebElement> charAnimationButton, String charName, String costumeName) {
        for (WebElement charCostume : charAnimationButton) {
            if(charCostume.getText().toLowerCase().trim().contains(charName.toLowerCase().trim()) && charCostume.getText().toLowerCase().trim().contains(costumeName.toLowerCase().trim())) {
                System.out.println("Webelement: " + charCostume.getText());
                System.out.println(charName + ": " + costumeName);
                charCostume.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(driver1 -> {
                    String buttonText = charCostume.getText();
                    return  buttonText.toLowerCase().trim().contains(costumeName.toLowerCase().trim());
                });
            }
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

    public static File getDownloadedImg(String link, File targetDir, String name){
        try{
            assert link != null;
            URL url = new URL(link);
            BufferedImage img = ImageIO.read(url);
            File imgFile = new File(targetDir, name+".png");
            ImageIO.write(img, "png", imgFile);
            return imgFile;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void pressingButtons(WebDriver webDriver, WebDriverWait wait){
        WebElement downloadButton = webDriver.findElement(By.cssSelector("#app div.bg-gray-900 div.flex div.lg\\:order-none div.md\\:flex button"));
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        downloadButton.click();
        WebElement exportButton = webDriver.findElement( By.cssSelector("#app div.bg-gray-900 div.flex div.lg\\:order-none div.md\\:flex div.absolute button:nth-child(1)"));
        wait.until(ExpectedConditions.elementToBeClickable(exportButton));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", exportButton);
    }


    public static File getDownloadedAnimationFile(WebDriver driver, File targetDir, String costumeName, String fieldName) throws InterruptedException {
        File downloadDir = new File("src/main/resources/static/GachaGameImages/BrownDust2/Animations");
        clearDirectory(downloadDir);
        int maxTries = 2;
        int tries = 0;
        boolean downloaded = false;
        while (!downloaded && tries < maxTries) {
            tries++;
            try{
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement buttonField =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '"+fieldName+"')]")));
                buttonField.click();
                pressingButtons(driver, wait);
                FluentWait<File> waitDownload = new FluentWait<>(downloadDir)
                        .withTimeout(Duration.ofSeconds(30))
                        .pollingEvery(Duration.ofSeconds(5))
                        .ignoring(NoSuchElementException.class)
                        .withMessage("Couldn't download");

                File animationFile = waitDownload.until(f-> {
                    File[] files = f.listFiles(((dir, name) -> name.endsWith(".webm")));
                    if(files!= null && files.length > 0){
                        return files[0];
                    }
                    return null;
                });
                File newAnimationFile = new File(targetDir, costumeName + "_"+fieldName+".webm");
                boolean success = animationFile.renameTo(newAnimationFile);
                if(newAnimationFile.exists()){
                    System.out.println("File already exists");
                } else{
                    if(success){
                        System.out.println("File downloaded");
                        clearDirectory(downloadDir);
                        return newAnimationFile;
                    }else {
                        System.out.println("couldnt");
                    }
                }
                downloaded = true;
            }catch(Exception e){
                log.error(e.getMessage());
            }
        }
        if(!downloaded){
            System.out.println("Error downloading");
        }
        return null;
    }

    public static String getDownloadedAnimationFile(File targetDir, String costumeName, String fieldName) throws InterruptedException {
        return String.valueOf(targetDir) + File.separator + costumeName + "_"+fieldName+".webm";
    }

    public static void clearDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                boolean isDeleted = file.delete();
                if (isDeleted) {
                    System.out.println("deleted: " + file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<BrownDust2Char> chars = scrape("https://browndust2-wiki.souseha.com/en/characters");
        String brownDustJsonString = "src/main/resources/JSONFolder/BrownDust2_Characters.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            mapper.writeValue(new File(brownDustJsonString), chars);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
