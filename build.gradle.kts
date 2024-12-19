// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false // Assuming you've defined hilt plugin in version catalog
}
configurations.all {
    resolutionStrategy {
        force("com.squareup:javapoet:1.12.0")// Ensure the correct JavaPoet version
    }
}