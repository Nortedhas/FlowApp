package com.example.ageone.SCAG

import com.example.ageone.Models.User.user
import org.json.JSONObject

fun Parser.userData(json: JSONObject) {
	json.optJSONObject("User").let { userJson ->
		user.hashId = userJson.optString("hashId", "")
		user.data.phone = userJson.optString("phone", "")
		user.data.name = userJson.optString("name", "")
		user.data.role = userJson.optString("role", "")
		user.data.email = userJson.optString("email", "")
		user.data.vipAccessTo = userJson.optInt("vipAccessTo", 0)
	}
}