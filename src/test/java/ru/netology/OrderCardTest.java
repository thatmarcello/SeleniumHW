package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardTest {

    //1. сначала создаем переменную с вебдрайвером
    private WebDriver driver;


    //2. пропишем в setProperty путь до нашего хромдрайвера. с @BeforeAll чтобы запускался перед выполнением всех тестов
    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    //3. создадим  метод c @Beforeach, чтобы перед каждым тестом создавать новый объект с типом WebDriver
    @BeforeEach
    void setUp() {
        // ниже Включение headless режима при использовании selenium
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
//        driver = new ChromeDriver(options);

    // 4. проинициализируем с помощью конструктора переменную driver
        driver = new ChromeDriver();
        driver.get("http://localhost:9999/"); // запустили браузер на странице localhost:9999
    }

    //5. еще один метод, в котором будем закрывать браузер и обнулять наш драйвер по сле каждого теста
    @AfterEach
    void tearsDown() {
        driver.quit(); // закрыли браузер
        driver = null; // обнулили драйвер
    }

    // ЗАДАНИЕ №1
    @Test
    void happyPathTest() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__control")).sendKeys("Марк Яранцев");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79115552575");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

}

