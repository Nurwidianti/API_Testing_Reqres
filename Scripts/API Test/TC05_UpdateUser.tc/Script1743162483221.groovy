import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import groovy.json.JsonOutput

// Data dinamis
def name = "John Doe"
def job = "Senior QA Engineer"

// Buat request body dalam format JSON
def requestData = [
    name: name,
    job : job
]
def requestBody = JsonOutput.toJson(requestData)

// Ambil test object dasar
RequestObject request = findTestObject('API Request/UpdateUser')

// Set header
request.setHttpHeaderProperties([
	new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])

// Set body content
request.setBodyContent(new HttpTextBodyContent(requestBody, "UTF-8", "application/json"))

// Kirim PUT request
def responsePut = WS.sendRequest(request)

// Debug response
println "Response: " + responsePut.getResponseText()

// Verifikasi status code 200
WS.verifyResponseStatusCode(responsePut, 200)

// Verifikasi apakah job sudah sesuai
WS.verifyElementPropertyValue(responsePut, 'job', job)
