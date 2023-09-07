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

// Obtener la instancia del controlador del navegador
WebDriver driver = DriverFactory.getWebDriver()

// Encontrar el elemento <table> en el documento HTML usando una expresión XPath
WebElement table = driver.findElement(By.xpath('//table/tbody'))

// Definir el valor esperado que estamos buscando en las celdas
String ValorEsperado = 'PROVEEDORES'

// Localizar todas las filas de la tabla
List<WebElement> totalFilas = table.findElements(By.xpath('.//tr'))
println('El total de filas visibles es: ' + totalFilas.size())

// Bucle para buscar ValorEsperado en la tabla y realizar la acción al conseguirlo
// Iterar sobre todas las filas de la tabla
table: for (int fila = 0; fila < totalFilas.size(); fila++) {
    // Localizar las celdas de la fila actual
    List<WebElement> totalColumnas = totalFilas.get(fila).findElements(By.tagName('td'))
    // Iterar en las celdas de cada fila
    for (int columna = 0; columna < totalColumnas.size(); columna++) {
        // Comprobar si el texto de la celda coincide con el valor esperado
        if (totalColumnas.get(columna).getText().equalsIgnoreCase(ValorEsperado)) {
            // Especificar columna donde se debe realizar la acción
            int colAccion = 10
            // Realizar una acción en la columna específicada de la fila donde coincidió el valor
			// Aqui indico directamente la columna donde relizar la acción
            totalColumnas.get(7).findElement(By.className('aprobar_chk')).click() 
			// Aqui indico la columna con lo definido en la variable colAccion
			totalColumnas.get(colAccion - 1).findElement(By.cssSelector("img[title='Detalle de programa']")).click() 
            // Salir del bucle interno cuando se encuentra el valor esperado en la tabla
            break table
        }
    }
}
