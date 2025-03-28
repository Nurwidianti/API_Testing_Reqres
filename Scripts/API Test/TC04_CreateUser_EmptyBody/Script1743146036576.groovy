// Import
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import groovy.json.JsonOutput
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

// Data dinamis
def name = ""
def job = ""

def requestData = [
    name: name,
    job : job
]

// Ambil Test Object dasar
RequestObject request = findTestObject('API Request/CreateUser')

// Ubah header jadi JSON
request.setHttpHeaderProperties([
    new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])

// Ubah body secara dinamis
def jsonBody = JsonOutput.toJson(requestData)
request.setBodyContent(new HttpTextBodyContent(jsonBody, 'UTF-8', 'application/json'))

// Kirim request
def response = WS.sendRequest(request)

// Output response
println "Status Code: " + response.getStatusCode()
println "Response Body:\n" + response.getResponseBodyContent()

// Verifikasi request gagal create user field required
WS.verifyResponseStatusCode(response, 400)
