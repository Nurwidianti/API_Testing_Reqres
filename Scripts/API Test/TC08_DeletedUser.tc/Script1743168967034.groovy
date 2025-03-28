import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType

// ID user yang ingin dihapus (bisa diganti dengan ID dinamis)
def userId = "3"  // Ganti dengan ID user yang sesuai

// Ambil test object untuk DELETE request dengan parameter ID
RequestObject request = findTestObject('API Request/DeletedUser', [('id') : userId])


// Set header jika diperlukan (opsional)
request.setHttpHeaderProperties([
	new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])

// Kirim DELETE request
def responseDelete = WS.sendRequest(request)

// Debug response
println "Response: " + responseDelete.getResponseText()

// Verifikasi status code, biasanya API akan mengembalikan 204 No Content atau 200 OK
WS.verifyResponseStatusCode(responseDelete, 204)  // Sesuaikan dengan respons API yang diharapkan
