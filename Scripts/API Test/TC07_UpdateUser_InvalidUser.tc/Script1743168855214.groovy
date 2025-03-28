import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import groovy.json.JsonOutput

// Data dinamis
def userId = "999"  // ID user yang tidak terdaftar (contoh)
def name = "John Doe"
def job = "Senior QA Engineer"

// Buat request body dalam format JSON
def requestData = [
	name: name,
	job : job
]
def requestBody = JsonOutput.toJson(requestData)

// Ambil test object dasar dan tambahkan ID pada URL
RequestObject request = findTestObject('API Request/UpdateUser')
request.setRestUrl("https://reqres.in/api/users/${userId}") // ID dinamis

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

// Verifikasi status code (harusnya 404 Not Found jika ID tidak ada)
WS.verifyResponseStatusCode(responsePut, 404)  // 404 jika ID tidak ditemukan

// Jika response berhasil (200 OK), verifikasi apakah job sudah sesuai
if (responsePut.getStatusCode() == 200) {
	WS.verifyElementPropertyValue(responsePut, 'job', job)
} else {
	println "User dengan ID ${userId} tidak ditemukan. Status code: ${responsePut.getStatusCode()}"
}




