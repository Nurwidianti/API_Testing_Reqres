import groovy.json.JsonSlurper
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty

// Buat request object dan atur parameter userId
def request = findTestObject('API Request/GetUserDetails')
request.setRestUrl("https://reqres.in/api/users/999")  // Ubah ID sesuai kebutuhan

// Kirim request
def responseGetNotFound = WS.sendRequest(request)

// Verifikasi status code
WS.verifyResponseStatusCode(responseGetNotFound, 404)

// Parsing response body
def responseBody = new JsonSlurper().parseText(responseGetNotFound.getResponseBodyContent())

// Validasi apakah response kosong
if (responseBody == null || responseBody.isEmpty()) {
    println "❌ Error: Response kosong `{ }`, kemungkinan API tidak mengembalikan data yang diharapkan!"
} else if (!responseBody.containsKey('data') || responseBody.data == []) {
    println "⚠️ Peringatan: Response tidak mengandung data yang valid!"
} else {
    println "✅ Response valid tetapi user tidak ditemukan sebagaimana mestinya."
}
