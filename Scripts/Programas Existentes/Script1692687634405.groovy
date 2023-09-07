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

// Instancia del controlador de WebDriver
WebDriver driver = DriverFactory.getWebDriver()

// Localizar tabla
WebElement Table = driver.findElement(By.xpath("//table/tbody"))

// Localizar todas las filas de la tabla
List<WebElement> rows_table = Table.findElements(By.tagName('tr'))

String ExpectedValue = 'Auditoría Área Sólidos'

// Calcular numero total de filas
def rows_count = rows_table.size()
println(rows_count)

// Iterar sobre todas las filas de la tabla
Loop:
for (int row = 0; row < rows_count; row++) {
    // Localizar las celdas de la fila
    List<WebElement> columns = rows_table.get(row).findElements(By.tagName('td'))
    // Calcular el número de celdas por fila
    int columns_count = columns.size()
    println((('El Numero de celdas en la fila ' + row) + ' es ') + columns_count)

    // Iterar en las celdas de cada fila
    for (int column = 0; column < columns_count; column++) {
        // Recuperará el texto de cada celda
        String celltext = columns.get(column).getText()
        println((((('El valor de la celda en la fila ' + row) + ' y la columna ') + column) + ' es: ') + celltext)

        // Comprobar si el texto de la celda coincide con el valor esperado
        if (celltext == ExpectedValue) {
            // Obtiene el valor de la celda, si el texto coincide con el valor esperado
            println(((('El Texto en la fila ' + row) + ' y la columna ') + column) + ' es: ') + columns.get(2).getText()
            // Después de obtener el valor esperado de la tabla terminaremos el bucle
            break Loop;
        }
    }
}