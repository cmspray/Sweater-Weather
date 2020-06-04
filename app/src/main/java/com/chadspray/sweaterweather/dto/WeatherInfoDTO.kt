import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class WeatherInfoDTO (

	@SerializedName("daylight") val daylight : String,
	@SerializedName("description") val description : String,
	@SerializedName("skyInfo") val skyInfo : Int,
	@SerializedName("skyDescription") val skyDescription : String,
	@SerializedName("temperature") val temperature : Double,
	@SerializedName("temperatureDesc") val temperatureDesc : String,
	@SerializedName("comfort") val comfort : Double,
	@SerializedName("highTemperature") val highTemperature : Double,
	@SerializedName("lowTemperature") val lowTemperature : Double,
	@SerializedName("humidity") val humidity : Int,
	@SerializedName("dewPoint") val dewPoint : Double,
	@SerializedName("precipitation1H") val precipitation1H : String,
	@SerializedName("precipitation3H") val precipitation3H : String,
	@SerializedName("precipitation6H") val precipitation6H : String,
	@SerializedName("precipitation12H") val precipitation12H : String,
	@SerializedName("precipitation24H") val precipitation24H : String,
	@SerializedName("precipitationDesc") val precipitationDesc : String,
	@SerializedName("airInfo") val airInfo : String,
	@SerializedName("airDescription") val airDescription : String,
	@SerializedName("windSpeed") val windSpeed : Double,
	@SerializedName("windDirection") val windDirection : Int,
	@SerializedName("windDesc") val windDesc : String,
	@SerializedName("windDescShort") val windDescShort : String,
	@SerializedName("barometerPressure") val barometerPressure : Double,
	@SerializedName("barometerTrend") val barometerTrend : String,
	@SerializedName("visibility") val visibility : Double,
	@SerializedName("snowCover") val snowCover : String,
	@SerializedName("icon") val icon : Int,
	@SerializedName("iconName") val iconName : String,
	@SerializedName("iconLink") val iconLink : String,
	@SerializedName("ageMinutes") val ageMinutes : Int,
	@SerializedName("activeAlerts") val activeAlerts : Int,
	@SerializedName("country") val country : String,
	@SerializedName("state") val state : String,
	@SerializedName("city") val city : String,
	@SerializedName("latitude") val latitude : Double,
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("distance") val distance : Double,
	@SerializedName("elevation") val elevation : Int,
	@SerializedName("utcTime") val utcTime : String,
	val shouldWearSweater: Boolean = temperature <= 55
)