package com.seiko.greenqrscanner.data.model.licenses

import kotlinx.serialization.Serializable

@Serializable
data class LicenseItem(
    val groupId: String,
    val artifactId: String,
    val version: String,
    val spdxLicenses: List<SpdxLicense>? = null,
    val name: String? = null,
    val scm: Scm? = null,
)

@Serializable
data class SpdxLicense(
    val identifier: String,
    val name: String,
    val url: String,
)

@Serializable
data class Scm(
    val url: String,
)
