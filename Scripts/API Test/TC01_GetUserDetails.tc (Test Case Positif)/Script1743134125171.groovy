import groovy.json.JsonSlurper
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty

// Test Case: GET User Details (Valid User)
WS.sendRequest(findTestObject('API Request/GetUserDetails'))
def responseGet = WS.sendRequest(findTestObject('API Request/GetUserDetails'))

WS.verifyResponseStatusCode(responseGet, 200)

WS.verifyElementPropertyValue(responseGet, 'data.id', 3)



