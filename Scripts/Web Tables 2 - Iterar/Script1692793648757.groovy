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
WebElement table = driver.findElement(By.xpath("//table"))

// Localizar todas las filas de la tabla
List<WebElement> filas = table.findElements(By.xpath('//tbody/tr'))

// Definir el valor esperado que estamos buscando en las celdas
String ValorEsperado = 'Auditoría Área Sólidos'

// Calcular el número total de filas en la tabla
def totalFilas = filas.size()
println('El total de filas visibles es: ' + totalFilas)


// Iterar sobre todas las filas de la tabla
Loop:
for (int fila = 0; fila < totalFilas; fila++) {
    // Localizar las celdas de la fila actual
    List<WebElement> columnas = filas.get(fila).findElements(By.tagName('td'))
    
    // Calcular el número total de celdas en la fila actual
    int totalColumnas = columnas.size()
	// Imprimir el Numero total de celdas en la fila
    println((('El Numero de celdas en la fila ' + fila) + ' es ') + totalColumnas)

    // Iterar en las celdas cada fila
    for (int columna = 0; columna < totalColumnas; columna++) {
        // Recuperar el texto de cada celda
        String valorCelda = columnas.get(columna).getText()
		// Imprimir el valor de la celda indicando numero de fila y columna
        println((((('El valor de la celda en la fila ' + fila) + ' y la columna ') + columna) + ' es: ') + valorCelda)

        // Comprobar si el texto de la celda coincide con el valor esperado
        if (valorCelda == ValorEsperado) {
            // Imprimir el valor encontrado si coincide con el valor esperado indicando numero de fila y columna
			println(((('El Texto en la fila ' + fila) + ' y la columna ') + columna) + ' es el esperado: ' + columnas.get(columna).getText())
            
            // Salir del bucle completo una vez se encuentra el valor esperado
            break Loop;
        }
    }
}