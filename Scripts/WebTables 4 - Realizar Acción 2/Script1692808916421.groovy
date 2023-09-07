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
WebElement table = driver.findElement(By.id('programaList'))

// Definir el valor esperado que estamos buscando en las celdas de la tabla 1 y 2
String ValorEsperado = '24'
String ValorEsperado2 = 'Auditoria al sector de sólidos según guia dinavisa'

// Localizar todas las filas de la tabla
List<WebElement> totalFilas = table.findElements(By.xpath('.//tbody/tr'))
println('El total de filas visibles es: ' + totalFilas.size())

// Bucle para buscar ValorEsperado en la tabla y realizar la acción al conseguirlo
// Iterar sobre todas las filas de la tabla
table1: for (int fila = 0; fila < totalFilas.size(); fila++) {
    // Localizar las celdas de la fila actual
    List<WebElement> totalColumnas = totalFilas.get(fila).findElements(By.tagName('td'))

    // Iterar en las celdas de cada fila
    for (int columna = 0; columna < totalColumnas.size(); columna++) {
        // Comprobar si el texto de la celda coincide con el valor esperado
        if (totalColumnas.get(columna).getText().equalsIgnoreCase(ValorEsperado)) {
            // Especificar columna donde se debe realizar la acción
            int colAccion = 1

            // Realizar una acción en la columna específicada de la fila donde coincidió el valor
            totalColumnas.get(colAccion - 1).findElement(By.tagName('img')).click()

            // Localizar segunda tabla
            WebElement table2 = driver.findElement(By.id('programaDetalle'))

            // Localizar las celdas de la fila actual en la segunda tabla
            List<WebElement> totalFilas2 = table2.findElements(By.xpath('.//tbody/tr'))
            println('El total de filas visibles en la segunda tabla es: ' + totalFilas2.size())

            // Bucle para interactuar con la segunda tabla
            table2: for (int fila2 = 0; fila2 < totalFilas2.size(); fila2++) {
                // Localizar las celdas de la fila actual en la segunda tabla
                List<WebElement> totalColumnas2 = totalFilas2.get(fila2).findElements(By.tagName('td'))

                // Iterar en las celdas de cada fila en la segunda tabla
                for (int columna2 = 0; columna2 < totalColumnas2.size(); columna2++) {
                    // Realizar las interacciones necesarias en la segunda tabla
                    if (totalColumnas2.get(columna2).getText().equalsIgnoreCase(ValorEsperado2)) {
                        // Especificar columna donde se debe realizar la acción
                        int colAccion2 = 8

                        // Realizar una acción en la columna específicada de la fila donde coincidió el valor
                        totalColumnas2.get(colAccion2 - 1).findElement(By.cssSelector("img[title='Ver tarea']")).click()
						
						// Obtener datos para aserción
						def testData = findTestData('Assertions')
						def rowIndex = 2
						def expectedResult = testData.getValue('mensaje', rowIndex)
						def actualResult = WebUI.getText(findTestObject('AdmProgram/span_detalleTarea'))
						
						// Realizar aserción
						assert actualResult.startsWith(expectedResult) : "El texto no empieza con '${expectedResult}': '${actualResult}'"
						
						// Tomar una captura de pantalla para documentar el resultado
						def url = WebUI.getUrl()
						def comment = 'URL: ' + url
						WebUI.takeScreenshot(GlobalVariable.ssPath + 'DetalleTareaPrograma.png', [('text') : comment])
						
                        // Salir del bucle interno cuando se encuentra el valor esperado en la segunda tabla
						return
                    }
                }
            }
            // Salir del bucle externo cuando se encuentra el valor esperado en la primera tabla
          break table1
        }
    }
}