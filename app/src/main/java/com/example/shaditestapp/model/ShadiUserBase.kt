package  com.example.shaditestapp.model

import com.google.gson.annotations.SerializedName


data class ShadiUserBase (

	@SerializedName("results") var results : List<Results>,
	@SerializedName("info") var info : Info
)