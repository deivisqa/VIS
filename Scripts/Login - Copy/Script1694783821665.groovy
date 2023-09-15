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

WebUI.openBrowser(GlobalVariable.url)

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Login/inp_usuario'), GlobalVariable.user)

WebUI.setText(findTestObject('Login/in_password'), GlobalVariable.pass)

WebUI.click(findTestObject('Login/btn_login'))

// WebUI.verifyElementPresent(findTestObject('Login/div_inicio'), 1, FailureHandling.STOP_ON_FAILURE)
// WebUI.verifyElementText(findTestObject('Login/div_inicio'), 'Inicio', FailureHandling.STOP_ON_FAILURE)

def testData = findTestData('Assertions')
def rowIndex = 1
def expectedResult = testData.getValue('mensaje', rowIndex)
def actualResult = WebUI.getText(findTestObject('Login/div_inicio'))
WebUI.verifyEqual(actualResult, expectedResult, FailureHandling.STOP_ON_FAILURE)

def url = WebUI.getUrl()
def comment = 'URL: ' + url
WebUI.takeScreenshot(GlobalVariable.ssPath + 'Login.png', [('text') : comment])
//WebUI.takeFullPageScreenshot(GlobalVariable.ssPath + 'LoginFull.png')

