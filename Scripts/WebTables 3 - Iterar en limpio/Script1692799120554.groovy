import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement

WebUI.callTestCase(findTestCase('Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Dashboard/btn_QaManager'))

WebUI.click(findTestObject('AdmProgram/AdmProgram'))

WebUI.click(findTestObject('AdmProgram/ProgramExist'))

WebUI.waitForElementPresent(findTestObject('AdmProgram/tbl_programaList'), 2)

WebDriver driver = DriverFactory.getWebDriver()
WebElement table = driver.findElement(By.xpath("//table"))
List<WebElement> filas = table.findElements(By.xpath('.//tbody/tr'))

String ValorEsperado = 'Auditoría Área Sólidos'
def totalFilas = filas.size()

Loop:
for (int fila = 0; fila < totalFilas; fila++) {
    List<WebElement> columnas = filas.get(fila).findElements(By.xpath('//tbody/tr/td'))
    int totalColumnas = columnas.size()

    for (int columna = 0; columna < totalColumnas; columna++) {
        String valorCelda = columnas.get(columna).getText()
        if (valorCelda == ValorEsperado) {
			println(((('El Texto en la fila ' + fila) + ' y la columna ') + columna) + ' es el esperado: ' + columnas.get(columna).getText())
            break Loop
        }
    }
}